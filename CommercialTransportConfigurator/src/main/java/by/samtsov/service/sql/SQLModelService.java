package by.samtsov.service.sql;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.Option;
import by.samtsov.bean.entity.OptionValue;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.ModelDao;
import by.samtsov.dao.OptionDao;
import by.samtsov.dao.OptionValueDao;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ModelService;
import by.samtsov.service.ServiceException;
import by.samtsov.service.validator.Validator;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLModelService extends SQLService implements ModelService {

    public static final String FIND_ACTIVE_VALUES_FOR_OPTION_OPERATION_NAME
            = "add Available Option";
    public static final String ADD_AVAILABLE_OPTION_OPERATION_NAME = "add Available Option";
    private static final String NULL_POINTER_ERR = "model cannot be null";
    private static final String MODEL_IS_INVALID_ERR = "model is invalid";
    private static final EntityType MODEL_ENTITY_TYPE = EntityType.MODEL;
    private static final EntityType OPTION_VALUE_ENTITY_TYPE = EntityType
            .OPTION_VALUE;
    private static final EntityType OPTION_ENTITY_TYPE = EntityType
            .OPTION;
    private static final Logger logger = LogManager.getLogger(
            SQLModelService.class);
    private static final String GET_OPERATION_NAME = "Get model by identity";
    private static final String GET_ALL_OPERATION_NAME = "Get all models";
    private static final String CREATE_OPERATION_NAME = "Create model";
    private static final String COPY_MODEL_OPERATION_NAME = "Copy model";
    private static final String DELETE_OPERATION_NAME = "Delete Model";
    private static final String UPDATE_OPERATION_NAME = "Update model";
    OptionValueDao optionValueDao = null;
    ModelDao modelDao = null;
    OptionDao optionDao = null;

    Validator modelValidator = null;


    public SQLModelService(Transaction transaction) throws InternalServerException {
        logger.debug("transaction is null: {}", transaction == null);
        this.transaction = transaction;
        optionValueDao = transaction.createDao(OPTION_VALUE_ENTITY_TYPE);
        modelDao = transaction.createDao(MODEL_ENTITY_TYPE);
        optionDao = transaction.createDao(OPTION_ENTITY_TYPE);
        modelValidator = ValidatorFactory.createValidator(MODEL_ENTITY_TYPE);
    }

    @Override
    public Model get(int id) throws ServiceException {
        try {
            Model model = modelDao.get(id);
            if (model != null) {
                List<OptionValue> optionValues = optionValueDao
                        .findByModelId(model.getId());
                for (int i = 0; i < optionValues.size(); i++) {
                    optionValues.set(i, optionValueDao.get(optionValues
                            .get(i).getId()));
                }
                model.setAvailableOptions(createOptionsFromOptionValues(
                        optionValues));
            }
            transaction.commit();
            return model;
        } catch (PersistenceException e) {
            rollbackTransaction(GET_OPERATION_NAME);
            throw generateException(GET_OPERATION_NAME, e);
        }
    }

    @Override
    public List<Model> getAll() throws ServiceException {
        try {
            List<Model> models = modelDao.getAll();
            if (models != null) {
                for (Model model : models) {
                    List<OptionValue> optionValues = optionValueDao
                            .findByModelId(model.getId());
                    for (int i = 0; i < optionValues.size(); i++) {
                        optionValues.set(i, optionValueDao.get(optionValues
                                .get(i).getId()));
                    }
                    model.setAvailableOptions(createOptionsFromOptionValues(
                            optionValues));
                }
            }
            transaction.commit();
            return models;
        } catch (PersistenceException e) {
            rollbackTransaction(GET_ALL_OPERATION_NAME);
            throw generateException(GET_ALL_OPERATION_NAME, e);
        }
    }


    @Override
    public int create(Model model) throws ServiceException {
        try {
            checkArguments(model);
            int id = modelDao.add(model);
            optionValueDao.updateOptionValuesForModel(model.getId(),
                    simplifyToOptionValuesList(model.getAvailableOptions()));
            transaction.commit();
            return id;
        } catch (PersistenceException e) {
            rollbackTransaction(CREATE_OPERATION_NAME);
            throw generateException(CREATE_OPERATION_NAME, e);
        }
    }

    @Override
    public void update(Model newModel) throws ServiceException {
        try {
            checkArguments(newModel);
            modelDao.update(newModel);
            optionValueDao.updateOptionValuesForModel(newModel.getId(),
                    simplifyToOptionValuesList(newModel.getAvailableOptions()));
            transaction.commit();
        } catch (PersistenceException e) {
            rollbackTransaction(UPDATE_OPERATION_NAME);
            throw generateException(UPDATE_OPERATION_NAME, e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            modelDao.delete(id);
            optionValueDao.deleteAllByModelId(id);
            transaction.commit();
        } catch (PersistenceException e) {
            rollbackTransaction(DELETE_OPERATION_NAME);
            throw generateException(DELETE_OPERATION_NAME, e);
        }
    }

    @Override
    public Model copyModel(Model oldModel) throws ServiceException {
        try {
            Model newModel = modelDao.get(oldModel.getId());
            List<OptionValue> newOptionValues =
                    optionValueDao.findByModelId(oldModel.getId());
            if (newModel != null && newOptionValues != null) {
                newModel.setId(modelDao.add(newModel));
                optionValueDao.addOptionValuesByModelId(newModel.getId()
                        , newOptionValues);
                newModel.setAvailableOptions(createOptionsFromOptionValues(
                        optionValueDao.findByModelId(newModel.getId())));
            }
            transaction.commit();
        } catch (PersistenceException e) {
            rollbackTransaction(COPY_MODEL_OPERATION_NAME);
            throw generateException(COPY_MODEL_OPERATION_NAME, e);
        }
        return null;
    }

    @Override
    public void addAvailableOption(int modelId, Option option) throws ServiceException {
        try {
            if (option != null && option.getOptionValues() != null
                    && !option.getOptionValues().isEmpty()) {
                optionValueDao.addOptionValuesByModelId(modelId,
                        option.getOptionValues());
            }
        } catch (PersistenceException e) {
            rollbackTransaction(ADD_AVAILABLE_OPTION_OPERATION_NAME);
            throw generateException(ADD_AVAILABLE_OPTION_OPERATION_NAME, e);
        }
    }

    @Override
    public void deleteAvailableOption(int modelId, int optionId) throws ServiceException {

        try {
            for (OptionValue optionValue :
                    optionValueDao.getByOptionId(optionId)) {
                optionValueDao.deleteByModelAndOptionId(modelId,
                        optionValue.getId());
            }
        } catch (PersistenceException e) {
            rollbackTransaction(ADD_AVAILABLE_OPTION_OPERATION_NAME);
            throw generateException(ADD_AVAILABLE_OPTION_OPERATION_NAME, e);
        }
    }

    @Override
    public void editAvailableOption(int modelId, Option option) throws ServiceException {
        try {
            optionValueDao.updateOptionValuesForModel(modelId,
                    option.getOptionValues());
        } catch (PersistenceException e) {
            rollbackTransaction(ADD_AVAILABLE_OPTION_OPERATION_NAME);
            throw generateException(ADD_AVAILABLE_OPTION_OPERATION_NAME, e);
        }
    }

    /**
     * look for all OptionValues for option with id=optionId and mark as true
     * that is available for model with id = modelId.
     * @param modelId  id of a selected model
     * @param optionId id of a selected option
     * @return HashMap <OptionValue, Boolean> that contains all options values
     * of option with id = optionId as a key and marker true as a value if this
     * OptionValue is active for model with id = modelId.
     * @throws ServiceException if
     */
    public HashMap<OptionValue, Boolean> findActiveValuesForOption(int modelId
            , int optionId) throws ServiceException {
        try {
            HashMap<OptionValue, Boolean> activeOptionValues
                    = new HashMap<>();
            if (optionDao.get(optionId) == null) {
                throw new ServiceException("no such option can be found");
            }
            for (OptionValue optionValue
                    : optionValueDao.getByOptionId(optionId)) {
                activeOptionValues.put(optionValue, false);
            }
            List<OptionValue> allOptionValuesOfAModel = optionValueDao
                    .findByModelId(modelId);
            if (allOptionValuesOfAModel == null) {
                throw new ServiceException("no such model can be found");
            }
            for (OptionValue optionValue : allOptionValuesOfAModel) {
                if (optionValue.getOptionId() == optionId) {
                    for (OptionValue optionValue2
                            : activeOptionValues.keySet()) {
                        if (optionValue2.getId() == optionValue.getId()) {
                            activeOptionValues.put(optionValue2, true);
                            break;
                        }
                    }
                }
            }
            return activeOptionValues;
        } catch (PersistenceException e) {
            rollbackTransaction(FIND_ACTIVE_VALUES_FOR_OPTION_OPERATION_NAME);
            throw generateException(FIND_ACTIVE_VALUES_FOR_OPTION_OPERATION_NAME
                    , e);
        }
    }

    private void checkArguments(Model model) throws IncorrectDataException {
        if (model == null) {
            throw new IncorrectDataException(NULL_POINTER_ERR);
        }
        if (!modelValidator.isValid(model)) {
            throw new IncorrectDataException(MODEL_IS_INVALID_ERR);
        }
    }

    private Map<Integer, Option> createOptionsFromOptionValues(List<OptionValue> optionValues) throws PersistenceException {
        if (optionValues == null) return null;
        Map<Integer, Option> options = new HashMap<>();
        for (OptionValue optionValue : optionValues) {
            int optionId = optionValue.getOptionId();
            options.putIfAbsent(optionId, optionDao.get(optionId));
            options.get(optionId).getOptionValues().add(optionValue);
        }

        return options;
    }

    private List<OptionValue> simplifyToOptionValuesList(Map<Integer, Option> options) {
        if (options == null) return null;
        List<OptionValue> optionValues = new ArrayList<>();
        for (Option option : options.values()) {
            optionValues.addAll(option.getOptionValues());
        }
        return optionValues;
    }

}
