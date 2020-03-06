package by.samtsov.controller.command;

import by.samtsov.service.sql.SQLServiceFactory;

public class CommandManagerFactory {
    public static CommandManager getManager(SQLServiceFactory serviceFactory) {
        return new CommandManagerImpl(serviceFactory);
    }
}
