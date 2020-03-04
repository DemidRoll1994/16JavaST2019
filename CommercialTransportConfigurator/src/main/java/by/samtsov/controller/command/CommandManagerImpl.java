package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.service.mysql.MysqlServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManager {

    private MysqlServiceFactory serviceFactory;

    public CommandManagerImpl(MysqlServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ForwardPage execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        command.setServiceFactory(serviceFactory);
        return command.execute(request, response);
    }

    @Override
    public void close() throws PersistentException {
        serviceFactory.close();
    }
}
