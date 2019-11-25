package by.samtsov.dao.factory;

import by.samtsov.dao.Dao;
import by.samtsov.dao.impl.ModelDaoImpl;
import by.samtsov.dao.impl.OptionDaoImpl;
import by.samtsov.dao.impl.OptionValueDaoImpl;
import by.samtsov.dao.impl.UserDaoImpl;
import by.samtsov.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

import static com.mysql.cj.conf.PropertyKey.logger;

public class DaoFactory {
    public static <Type extends Dao<?>> Type createDao(DaoType daoType) {
        /*Connection conn;
        try {
            conn = ConnectionPool.getInstance().getConnection(); // todo choose one
            Connection conn2 = ConnectionPool.getInstance().getConnection();

            switch (daoType) {
                case USER:
                    UserDaoImpl userDao = new UserDaoImpl();
                    userDao.setConnection(conn);
                    return (Type) userDao;
                case MODEL:
                    ModelDaoImpl modelDao = new ModelDaoImpl();
                    modelDao.setConnection(conn);
                    return (Type) modelDao;
                case ORDER:
                    OrderDaoImpl orderDao = new OrderDaoImpl();
                    orderDao.setConnection(conn);
                    return (Type) orderDao;
                case OPTION:
                    OptionDaoImpl optionDao = new OptionDaoImpl();
                    optionDao.setConnection(conn);
                    return (Type) optionDao;
                case OPTION_VALUE:
                    OptionValueDaoImpl optionValueDao = new OptionValueDaoImpl();
                    optionValueDao.setConnection(conn);
                    return (Type) optionValueDao;
                case CONFIGURATION:
                    ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl();
                    configurationDao.setConnection(conn);
                    return (Type) configurationDao;
            }

        } catch (SQLException sqle) {
            logger.error("error during creating connection for " + daoType + " daotype");
            throw new PersistantException(sqle.getMessage());
        }
*/return null;
    }
}
