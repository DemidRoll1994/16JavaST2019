package by.samtsov.service.command;

import by.samtsov.bean.entity.User;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    private static Logger logger = LogManager.getLogger(LogoutCommand.class);

    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {
        ResponsePage responsePage = new ResponsePage("/", true);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authorizedUser");
        if (user != null) {
            session.removeAttribute("authorizedUser");
            logger.info("user {} is logged out", user.getEmail());
        }else{
            logger.info("trying logout user, but he is already is logged out");
        }
        return responsePage;
    }
}