package by.samtsov.dao.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3poDataSource {

    private static Logger logger = LogManager.getRootLogger();

    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static final String URL = "jdbc:mysql://localhost:3306/vehiclesSales?useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public static final String USERNAME = "vehiclesSalesUser1";

    public static final String PASSWORD = "vehiclesSalesUser1Password";

    static {
        try {
            cpds.setDriverClass(DRIVER_CLASS);
            cpds.setJdbcUrl(URL);
            cpds.setUser(USERNAME);
            cpds.setPassword(PASSWORD);
        } catch (PropertyVetoException e) {
            logger.error("It is impossible to establish connection", e);

        }
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    private C3poDataSource(){}
}
