package by.samtsov.dao.transaction;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.Dao;
import by.samtsov.dao.factory.DaoFactory;
import by.samtsov.dao.impl.BaseDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionImpl implements Transaction {

    private static Logger logger = LogManager.getRootLogger();

    private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(EntityType entityType) throws PersistentException {
        if(entityType != null) {
            try {
                BaseDaoImpl dao = DaoFactory.createDao(entityType);
                dao.setConnection(connection);
                return (Type)dao;
            } catch(Exception e) {
                logger.error("It's impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch(SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new PersistentException(e);
        }
    }
}
