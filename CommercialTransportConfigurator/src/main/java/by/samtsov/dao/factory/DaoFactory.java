package by.samtsov.dao.factory;

import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.Dao;
import by.samtsov.dao.mysqlimpl.*;
import by.samtsov.service.InternalServerException;

public class DaoFactory {
    public static <T extends Dao<?>> T createDao(EntityType entityType) throws InternalServerException {

        switch (entityType) {
            case USER:
                return (T) new SQLUserDao();
            case MODEL:
                return (T) new SQLModelDao();
            case ORDER://todo create dao
                throw new InternalServerException("creating unsupported dao");
                //return (Type) new SQLOrderDao();
            case OPTION:
                return (T) new SQLOptionDao();
            case OPTION_VALUE:
                return (T) new SQLOptionValueDao();
            case CONFIGURATION://todo create dao
                return (T) new SQLConfigurationDao();
            default:
                throw new InternalServerException("creating unsupported dao");
        }
    }
}
