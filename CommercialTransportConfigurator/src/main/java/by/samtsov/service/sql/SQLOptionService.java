package by.samtsov.service.sql;

import by.samtsov.bean.entity.Option;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.OptionDao;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.OptionService;
import by.samtsov.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SQLOptionService extends SQLService implements OptionService {


    public static final EntityType OPTION_ENTITY_TYPE = EntityType.OPTION;
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
    OptionDao optionDao = null;

    public SQLOptionService(Transaction transaction) throws InternalServerException {

        logger.debug("transaction is null:" + (transaction == null));
        this.transaction = transaction;
        optionDao = transaction.createDao(OPTION_ENTITY_TYPE);
    }

    @Override
    public Option get(int id) throws ServiceException {
        try {
            Option option = optionDao.get(id);
            transaction.commit();
            return option;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_GET_ERR_MSG + id, e);
            }
            throw new ServiceException(CAN_T_GET_ERROR_MSG + id, e);
        }
    }

    @Override
    public List<Option> getAll() throws ServiceException {
        try {
            List<Option> options = optionDao.getAll();
            transaction.commit();
            return options;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_GET_ALL_ERR_MSG, e);
            }
            throw new ServiceException(CAN_T_GET_ALL_ERROR_MSG, e);
        }
    }

    @Override
    public int save(Option option) throws ServiceException {
        try {
            int id = optionDao.add(option);
            transaction.commit();
            return id;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_SAVE_ERR_MSG + option.getId(), e);
            }
            throw new ServiceException(CAN_T_SAVE_ERROR_MSG + option.getId(), e);
        }
    }

    @Override
    public Option update(Option option) throws ServiceException {

        throw new UnsupportedOperationException(ROLLBACK_CREATE_ERR_MSG + option.getId());

    }


    @Override
    public void delete(int id) throws ServiceException {
        try {
            optionDao.delete(id);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + id, e);
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + id, e);
        }
    }
}
