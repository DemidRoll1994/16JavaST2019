package by.samtsov.service.command;

import by.samtsov.bean.type.Role;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class IndexCommand extends Command {

    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {
        ResponsePage responsePage = new ResponsePage("/", true);
        responsePage.setRedirect(true);
        return responsePage;
    }


    public Set<Role> getAllowedRoles() {
        return null;
    }
}
