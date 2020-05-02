package by.samtsov.service.validator;

import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.validator.impl.ModelValidatorImpl;
import by.samtsov.service.validator.impl.OptionValidatorImpl;
import by.samtsov.service.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ValidatorFactory {

    private static Logger logger = LogManager.getLogger(ValidatorFactory.class);

    public static <Type extends Validator<?>> Type createValidator(EntityType entityType) throws InternalServerException {
        logger.trace("trying to create validator {}", entityType);
        try {
            switch (entityType) {
                case USER:
                    UserValidator userValidator = new UserValidatorImpl();
                    return (Type) userValidator;
                case MODEL:
                    ModelValidator modelValidator = new ModelValidatorImpl();
                    return (Type) modelValidator;
            /*case ORDER:
                OrderServiceImpl orderService = new OrderServiceImpl();
                return (Type) orderService;*/
            case OPTION:
                OptionValidator optionValidator = new OptionValidatorImpl();
                return (Type) optionValidator;
            /*case OPTION_VALUE:
                OptionValueServiceImpl optionValueService = new OptionValueServiceImpl();
                return (Type) optionValueService;
            case CONFIGURATION:
                ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl();
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