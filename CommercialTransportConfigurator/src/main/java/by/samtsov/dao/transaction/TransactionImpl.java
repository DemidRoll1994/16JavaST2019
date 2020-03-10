package by.samtsov.dao.transaction;

import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.Dao;
import by.samtsov.dao.factory.DaoFactory;
import by.samtsov.dao.mysqlimpl.SQLBaseDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionImpl implements Transaction {

    private static Logger logger = LogManager.getRootLogger();

    private static Map<Class<? extends Dao<?>>, Class<? extends SQLBaseDao>> classes = new ConcurrentHashMap<>();

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(EntityType entityType) throws InternalServerException {
        if(entityType != null) {
                SQLBaseDao dao = DaoFactory.createDao(entityType);
                dao.setConnection(connection);
                return (Type)dao;
        }
        return null;
    }

    @Override
    public void commit() throws PersistenceException {
        try {
            connection.commit();
        } catch(SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void rollback() throws PersistenceException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new PersistenceException(e);
        }
    }
}
