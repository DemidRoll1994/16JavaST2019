package by.samtsov.dao.pool;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    InitialContext initContext;
    DataSource ds;

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws NamingException {
        initContext = new InitialContext();
        ds = (DataSource) initContext.lookup("java:comp/env/jdbc/dbconnect");
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
