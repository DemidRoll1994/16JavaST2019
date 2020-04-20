package by.samtsov.service.sql;

import by.samtsov.bean.entity.Configuration;
import by.samtsov.bean.entity.OptionValue;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.ConfigurationDao;
import by.samtsov.dao.ModelDao;
import by.samtsov.dao.OptionValueDao;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.ConfigurationService;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SQLConfigurationService extends SQLService
        implements ConfigurationService {

    public static final EntityType CONFIGURATION = EntityType.CONFIGURATION;

    private static final String ROLLBACK_GET_ERR_MSG
            = "can't rollback a transaction while try to get user with login";
    private static final String CAN_T_GET_ERROR_MSG
            = "can't get user with login";
    private static final String ROLLBACK_GET_ALL_ERR_MSG
            = "can't rollback a transaction while try to get all users";
    private static final String CAN_T_GET_ALL_ERROR_MSG
            = "can't get all users";
    private static final String ROLLBACK_SAVE_ERR_MSG
            = "can't rollback a transaction while try to save user with id";
    private static final String CAN_T_SAVE_ERROR_MSG
            = "can't save user with id";
    private static final String ROLLBACK_UPDATE_ERR_MSG
            = "can't rollback a transaction while try to update user with id";
    private static final String CAN_T_UPDATE_ERROR_MSG
            = "can't update user with id";
    private static final String CAN_T_CREATE_ERR_MSG
            = "can't create user with login";
    private static final String ROLLBACK_CREATE_ERR_MSG
            = "can't rollback a transaction while try to delete user with id";
    private static final String CAN_T_DELETE_ERROR_MSG
            = "can't delete user with id";
    private static final String CAN_T_FIND_MSG = "can't find user with id";
    private static final String ROLLBACK_FIND_ERR_MSG
            = "can't rollback a transaction while try to find user with id";
    private static final String UPDATE_PASSWORD_ERR_MSG
            = "can't update password for user with login";
    private static final String ROLLBACK_PASS_UPD_ERR_MSG = "can't rollback a" +
            " transaction while try to update password for user with login";
    private static final Logger logger = LogManager.getLogger(
            SQLConfigurationService.class);
    ConfigurationDao configDao = null;
    OptionValueDao optionValuesDao = null;
    ModelDao modelDao = null;

    // todo create ConfigurationValidator configValidator = null;


    public SQLConfigurationService(Transaction transaction) throws InternalServerException {
        logger.debug("transaction is null:" + (transaction == null));
        this.transaction = transaction;
        configDao = transaction.createDao(EntityType.CONFIGURATION);
        optionValuesDao = transaction.createDao(EntityType.OPTION_VALUE);
        modelDao = transaction.createDao(EntityType.MODEL);
        // todo init configValidator = ValidatorFactory.createValidator
        // (CONFIGURATION);
    }

    @Override
    public Configuration get(int id) throws ServiceException,
            InternalServerException {
        /*ry {
            Configuration config = configDao.get(id);
            config.setModel(modelDao.get(config.getModel().getId()));
            List optionValues = new ArrayList();

            for (OptionValue optionValue : config.getOptionValues()) {
                optionValues.add(optionValuesDao.get(optionValue.getId()));
            }
            config.setOptionValues(optionValuesDao.get(config.getOptionValues()));
            transaction.commit();
            config.setPrice(calculatePrice(config));
            return config;
        } catch (PersistenceException e) {
            String operation;
            rollbackTransaction(operation);
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_GET_ERR_MSG, e);
            }
            throw new ServiceException(CAN_T_GET_ERROR_MSG, e);
        }*/throw new ServiceException();
    }


    @Override
    public List<Configuration> getAll() {
        return null;
    }

    @Override
    public int create(Configuration configuration) {
        return 0;
    }

    @Override
    public void update(Configuration configuration) {

    }

    @Override
    public void delete(int id) {

    }


    private double calculatePrice(Configuration config) {
        double price = config.getModel().getPrice();
        for (OptionValue value : config.getSelectedOptionValues()) {
            price += value.getPrice();
        }
        return price;
    }


/*

    @Override
    public List<Author> findAll() throws PersistentException {
        return authorDao.read();
    }

    @Override
    public Author findByIdentity(Integer identity) throws PersistentException {
        return authorDao.read(identity);
    }

    @Override
    public void save(Author author) throws PersistentException {
        if (author.getIdentity() != null) {
            authorDao.update(author);
        } else {
            author.setIdentity(authorDao.create(author));
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        authorDao.delete(identity);
    }

    @Override
    public Map<Author, Integer> findAllWithNumberOfBooks() throws PersistentException {
        return authorDao.readWithNumberOfBooks();
    }

    private UserDao getDao() {
        UserDao userDao = transaction.createDao(AuthorDao.class);

    }


    @Override
    public Configuration get(int id) {
        ConfigurationDao configurationDao = DaoFactory.createDao(DaoType.CONFIGURATION);
        ModelService modelService =
        Configuration configuration = configurationDao.get(id);
        configuration.setModel();
        return null;
    }

    @Override
    public List<Configuration> getAll() {


        return null;
    }

    @Override
    public int add(Configuration configuration) {
        return 0;
    }

    @Override
    public void update(Configuration configuration) {

    }

    @Override
    public void delete(int id) {

    }*/
}
