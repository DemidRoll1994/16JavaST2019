package by.samtsov.service.parser;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserFactory {

    private static Logger logger = LogManager.getRootLogger();

    public static <Type extends Parser<?>> Type createParser(EntityType entityType) throws InternalServerException {
        try {
            switch (entityType) {
                case USER:
                    UserParserImpl userParser = new UserParserImpl();
                    return (Type) userParser;
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
            logger.error("error during creating parser for " + entityType + " EntityType");
            throw new InternalServerException("can't create Parser", e);
        }

        throw new InternalServerException("can't create Parser of " + entityType + " EntityType. check case-block is exist.");
}
}
