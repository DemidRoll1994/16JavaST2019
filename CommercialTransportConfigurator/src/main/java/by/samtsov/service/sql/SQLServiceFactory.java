package by.samtsov.service.sql;

import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.dao.transaction.TransactionFactory;
import by.samtsov.service.Service;
import by.samtsov.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SQLServiceFactory implements ServiceFactory {

    private static Logger logger = LogManager.getRootLogger();
    TransactionFactory transactionFactory;

    public SQLServiceFactory(TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }


    public <Type extends Service> Type createService(EntityType entityType) throws InternalServerException {
        //try {

        Transaction transaction = transactionFactory.createTransaction();
        logger.debug("transaction created");
        switch (entityType) {
            case USER:
                SQLUserService userService = new SQLUserService(transaction);
                return (Type) userService;
            /*case MODEL:
                ModelServiceImpl modelService = new ModelServiceImpl(transaction);
                return (Type) modelService;
            case ORDER:
                OrderServiceImpl orderService = new OrderServiceImpl(transaction);
                return (Type) orderService;
            case OPTION:
                OptionServiceImpl optionService = new OptionServiceImpl(transaction);
                return (Type) optionService;
            case OPTION_VALUE:
                OptionValueServiceImpl optionValueService = new OptionValueServiceImpl(transaction);
                return (Type) optionValueService;
            case CONFIGURATION:
                ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl(transaction);
                return (Type) configurationService;
        }

    } catch (SQLException sqle) {
        logger.error("error during creating connection for " + entityType + " EntityType");
        throw new PersistentException(sqle.getMessage());
    }   */
        }
        throw new InternalServerException("can't create Service");
    }


    @Override
    public void close() {
        transactionFactory.close();
    }

}
