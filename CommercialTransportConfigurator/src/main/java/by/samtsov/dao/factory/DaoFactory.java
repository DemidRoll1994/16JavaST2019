package by.samtsov.dao.factory;

import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.Dao;
import by.samtsov.dao.mysqlimpl.*;
import by.samtsov.service.InternalServerException;

public class DaoFactory {
    public static <Type extends Dao<?>> Type createDao(EntityType entityType) throws InternalServerException {

        switch (entityType) {
            case USER:
                return (Type) new SQLUserDao();
            case MODEL:
                return (Type) new SQLModelDao();
            case ORDER://todo create dao
                throw new InternalServerException("creating unsupported dao");
                //return (Type) new SQLOrderDao();
            case OPTION:
                return (Type) new SQLOptionDao();
            case OPTION_VALUE:
                return (Type) new SQLOptionValueDao();
            case CONFIGURATION://todo create dao
                return (Type) new SQLConfigurationDao();
            default:
                throw new InternalServerException("creating unsupported dao");
        }
    }
}
