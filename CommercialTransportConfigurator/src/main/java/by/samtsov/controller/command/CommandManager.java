package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.bean.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManager extends AutoCloseable {
    ForwardPage execute(Command command, HttpServletRequest request,
                        HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException;


    void close() throws PersistenceException;
}
