package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.PersistenceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexCommand extends Command{
    @Override
    public ForwardPage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException {
        try {
            response.sendRedirect("/WEB-INF/jsp/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
