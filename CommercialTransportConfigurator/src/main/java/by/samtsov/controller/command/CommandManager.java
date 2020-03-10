package by.samtsov.controller.command;

import by.samtsov.view.ForwardPage;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManager extends AutoCloseable {
    ForwardPage execute(Command command, HttpServletRequest request,
                        HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException;


    void close() throws PersistenceException;
}
