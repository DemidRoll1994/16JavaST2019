package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandManager {
    ForwardPage execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersistentException, PersistentException;

    void close();
}
