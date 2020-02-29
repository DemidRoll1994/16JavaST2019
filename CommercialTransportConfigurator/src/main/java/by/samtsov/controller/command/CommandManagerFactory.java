package by.samtsov.controller.command;

import by.samtsov.service.mysql.MysqlServiceFactory;

public class CommandManagerFactory {
    public static CommandManager getManager(MysqlServiceFactory serviceFactory) {
        return new CommandManagerImpl(serviceFactory);
    }
}
