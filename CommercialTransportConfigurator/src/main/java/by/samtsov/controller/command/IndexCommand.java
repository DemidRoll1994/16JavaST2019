package by.samtsov.controller.command;

import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexCommand extends Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, IncorrectDataException {
        try {
            response.sendRedirect("/WEB-INF/jsp/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
