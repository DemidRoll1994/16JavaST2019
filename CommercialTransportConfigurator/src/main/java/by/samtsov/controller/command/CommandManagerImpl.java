package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.bean.exceptions.ServiceException;
import by.samtsov.service.sql.SQLServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManagerImpl implements CommandManager {

    private SQLServiceFactory serviceFactory;

    public CommandManagerImpl(SQLServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ForwardPage execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {
        command.setServiceFactory(serviceFactory);
        return command.execute(request, response);
    }

    @Override
    public void close() throws PersistenceException {
        serviceFactory.close();
    }
}
