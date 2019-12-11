package by.samtsov.dao.factory;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.dao.Dao;
import by.samtsov.dao.impl.ModelDaoImpl;
import by.samtsov.dao.impl.OptionDaoImpl;
import by.samtsov.dao.impl.OptionValueDaoImpl;
import by.samtsov.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static <Type extends Dao<?>> Type createDao(EntityType entityType) {
            /*switch (entityType) { todo create daos
                case USER:
                    UserDaoImpl userDao = new UserDaoImpl();
                    return (Type) userDao;
                case MODEL:
                    ModelDaoImpl modelDao = new ModelDaoImpl();
                    return (Type) modelDao;
                case ORDER:
                    OrderDaoImpl orderDao = new OrderDaoImpl();
                    return (Type) orderDao;
                case OPTION:
                    OptionDaoImpl optionDao = new OptionDaoImpl();
                    return (Type) optionDao;
                case OPTION_VALUE:
                    OptionValueDaoImpl optionValueDao = new OptionValueDaoImpl();
                    return (Type) optionValueDao;
                case CONFIGURATION:
                    ConfigurationDaoImpl configurationDao = new ConfigurationDaoImpl();
                    return (Type) configurationDao;
            }*/
            return null;
        }
    }
