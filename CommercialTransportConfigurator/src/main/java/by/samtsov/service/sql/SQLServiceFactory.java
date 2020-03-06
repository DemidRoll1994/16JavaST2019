package by.samtsov.service.sql;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.dao.transaction.TransactionFactory;
import by.samtsov.service.Service;
import by.samtsov.service.ServiceFactory;

public class SQLServiceFactory implements ServiceFactory {

    TransactionFactory transactionFactory;

    public SQLServiceFactory(TransactionFactory transactionFactory) {
    this.transactionFactory=transactionFactory;
    }



    public <Type extends Service> Type createService(EntityType entityType) throws PersistenceException {
        //try {
        switch (entityType) {
            case USER:
                SQLUserService userService = new SQLUserService();
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
        throw new PersistenceException("can't create Service");
    }



    @Override
    public void close() {
        transactionFactory.close();
    }

}
