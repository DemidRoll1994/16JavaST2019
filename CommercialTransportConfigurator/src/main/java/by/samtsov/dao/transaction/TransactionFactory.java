package by.samtsov.dao.transaction;

import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.pool.C3poDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactory {

    private static Logger logger = LogManager.getRootLogger();
    private Connection connection;

    public TransactionFactory() throws PersistentException {
        try {
            connection = C3poDataSource.getConnection();
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    public Transaction createTransaction() throws PersistentException {
        return new TransactionImpl(connection);
    }

    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }
}
