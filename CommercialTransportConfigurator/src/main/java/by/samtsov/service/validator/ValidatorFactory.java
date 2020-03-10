package by.samtsov.service.validator;

import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ValidatorFactory {

    private static Logger logger = LogManager.getRootLogger();

    public static <Type extends Validator<?>> Type createValidator(EntityType entityType) throws InternalServerException {
    try {
        switch (entityType) {
            case USER:
                UserValidator userValidator = new UserValidatorImpl();
                return (Type) userValidator;
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
                return (Type) configurationService; */
        }

    } catch (Exception e) {
        logger.error("error during creating validator for " + entityType
                + " EntityType");
        throw new InternalServerException("can't create validator", e);
    }

    throw new InternalServerException("can't create validator of " + entityType
            + " EntityType. check case-block is exist.");
}
}