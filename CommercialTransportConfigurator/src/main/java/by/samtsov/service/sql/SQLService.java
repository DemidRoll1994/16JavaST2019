package by.samtsov.service.sql;

import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.ServiceException;

public abstract class SQLService {
    private final String ROLLBACK_ERR_MSG = "Can't rollback transaction while" +
            " try to do operation \"%s\" for";
    private final String ACT_ERR_MSG = "Can't do operation \"%s\" because " +
            "persistence exception was thrown";


    protected Transaction transaction = null;

    void rollbackTransaction(String operationName) throws ServiceException{
        try {
            transaction.rollback();
        } catch (PersistenceException ex) {
            throw new ServiceException(String.format(ROLLBACK_ERR_MSG,
                    operationName), ex);
        }
    }
    ServiceException generateException(String operationName,
                                       Throwable exception) {
        return new ServiceException(String.format(ACT_ERR_MSG,
                operationName), exception);
    }

}
