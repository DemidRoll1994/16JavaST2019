package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand extends Command {

    @Override
    public ForwardPage execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return new ForwardPage("/index.html");
    }
}
