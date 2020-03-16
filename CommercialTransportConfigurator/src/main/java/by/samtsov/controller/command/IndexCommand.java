package by.samtsov.controller.command;

import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand extends Command {

    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException{
        ResponsePage responsePage = new ResponsePage("/WEB-INF/jsp/index.jsp");
        responsePage.setRedirect(true);
        return responsePage;
    }
}
