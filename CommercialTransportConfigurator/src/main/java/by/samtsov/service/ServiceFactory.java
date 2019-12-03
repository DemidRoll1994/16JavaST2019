package by.samtsov.service;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.service.impl.UserServiceImpl;

public class ServiceFactory {

    public static <Type extends Service<?>> Type createService(EntityType entityType) throws PersistentException {
        //try {
        switch (entityType) {
            case USER:
                UserServiceImpl userService = new UserServiceImpl();
                return (Type) userService;
            /*case MODEL:
                ModelServiceImpl modelService = new ModelServiceImpl();
                modelService.setConnection(conn);
                return (Type) modelService;
            case ORDER:
                OrderServiceImpl orderService = new OrderServiceImpl();
                orderService.setConnection(conn);
                return (Type) orderService;
            case OPTION:
                OptionServiceImpl optionService = new OptionServiceImpl();
                optionService.setConnection(conn);
                return (Type) optionService;
            case OPTION_VALUE:
                OptionValueServiceImpl optionValueService = new OptionValueServiceImpl();
                optionValueService.setConnection(conn);
                return (Type) optionValueService;
            case CONFIGURATION:
                ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl();
                configurationService.setConnection(conn);
                return (Type) configurationService;
        }

    } catch (SQLException sqle) {
        logger.error("error during creating connection for " + entityType + " EntityType");
        throw new PersistentException(sqle.getMessage());
    }   */
        }
        throw new PersistentException("can't create Service");
    }
}