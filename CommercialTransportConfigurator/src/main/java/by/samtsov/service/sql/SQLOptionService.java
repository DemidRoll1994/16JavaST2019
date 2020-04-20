package by.samtsov.service.sql;

import by.samtsov.bean.entity.Option;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.OptionDao;
import by.samtsov.dao.OptionValueDao;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.OptionService;
import by.samtsov.service.ServiceException;
import by.samtsov.service.validator.OptionValidator;
import by.samtsov.service.validator.Validator;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SQLOptionService extends SQLService implements OptionService {

    private static final Logger logger = LogManager.getLogger(
            SQLUserService.class);
    private static final String ROLLBACK_GET_ERR_MSG
            = "can't rollback while try to get option with id";
    private static final String CAN_T_GET_ERROR_MSG
            = "can't get option with id {}";
    private static final String ROLLBACK_GET_ALL_ERR_MSG
            = "can't rollback while try to get all options";
    private static final String CAN_T_GET_ALL_ERROR_MSG
            = "can't get all options";
    private static final String ROLLBACK_SAVE_ERR_MSG
            = "can't rollback while try to save option with id";
    private static final String CAN_T_SAVE_ERROR_MSG
            = "can't save option with id";
    private static final String ROLLBACK_CREATE_ERR_MSG
            = "can't rollback while try to delete option with id";
    private static final String CAN_T_DELETE_ERROR_MSG
            = "can't delete option with id";
    private static final String GET_OPERATION_NAME = "Get option by identity";
    private static final String GET_ALL_OPERATION_NAME = "Get all options";
    private static final String CREATE_OPERATION_NAME = "Create option";
    private static final String DELETE_OPERATION_NAME = "Delete option";
    private static final EntityType OPTION_ENTITY_TYPE = EntityType.OPTION;
    public static final EntityType OPTION_VALUE_ENTITY_TYPE
            = EntityType.OPTION_VALUE;
    private static final String NULL_POINTER_ERR = "option cannot be null";
    private static final String OPTION_IS_INVALID_ERR = "option is invalid";

    private OptionDao optionDao = null;
    private OptionValueDao optionValueDao = null;
    private Validator optionValidator;

    public SQLOptionService(Transaction transaction) throws InternalServerException {

        logger.debug("transaction is null:{}", transaction == null);
        this.transaction = transaction;
        optionDao = transaction.createDao(OPTION_ENTITY_TYPE);
        optionValueDao = transaction.createDao(OPTION_VALUE_ENTITY_TYPE);
        optionValidator = ValidatorFactory.createValidator(OPTION_ENTITY_TYPE);
    }

    @Override
    public Option get(int id) throws ServiceException {
        try {
            Option option = optionDao.get(id);
            option.setOptionValues(optionValueDao.getByOptionId(option
                    .getId()));
            transaction.commit();
            return option;
        } catch (PersistenceException e) {
            rollbackTransaction(GET_OPERATION_NAME);
            throw generateException(GET_OPERATION_NAME, e);
        }
    }


    @Override
    public List<Option> getAll() throws ServiceException {
        try {
            List<Option> options = optionDao.getAll();
            for (Option option : options) {
                option.setOptionValues(optionValueDao.getByOptionId(option
                        .getId()));
            }
            transaction.commit();
            return options;
        } catch (PersistenceException e) {
            rollbackTransaction(GET_ALL_OPERATION_NAME);
            throw generateException(GET_ALL_OPERATION_NAME, e);
        }
    }

    @Override
    public int create(Option option) throws ServiceException {
        try {

            checkArguments(option);
            int id = optionDao.add(option);
            transaction.commit();
            return id;

        } catch (PersistenceException e) {
            rollbackTransaction(CREATE_OPERATION_NAME);
            throw generateException(CREATE_OPERATION_NAME, e);
        }
    }

    private void checkArguments(Option option) throws IncorrectDataException {
        if (option == null) {
            throw new IncorrectDataException(NULL_POINTER_ERR);
        }
        if (!optionValidator.isValid(option)) {
            throw new IncorrectDataException(OPTION_IS_INVALID_ERR);
        }
    }

    @Override
    public void update(Option option) throws ServiceException {
/// TODO: 20.04.2020 do update function
        throw new UnsupportedOperationException(ROLLBACK_CREATE_ERR_MSG + option.getId());

    }


    @Override
    public void delete(int id) throws ServiceException {
        try {
            optionDao.delete(id);
            transaction.commit();
        } catch (PersistenceException e) {
            rollbackTransaction(DELETE_OPERATION_NAME);
            throw generateException(DELETE_OPERATION_NAME, e);
        }
    }

}
