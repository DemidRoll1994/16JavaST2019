package by.samtsov.service.command.admin;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUsersDataCommand extends AdminCommand {

    private static final EntityType USER_ENTITY_TYPE = EntityType.USER;
    private static Logger logger = LogManager.getLogger(
            EditUsersDataCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/admin/editUser.jsp"
                , false);
        UserService service = factory.createService(USER_ENTITY_TYPE);
        int userId = -1;
        try {
            String id= request.getParameter("userId");
            if (id==null){
                userId = (Integer) request.getAttribute("userId");
            }else {
                userId = Integer.parseInt(request.getParameter("userId"));
            }
        } catch (Exception e) {
            throw new ServiceException("userId is invalid", e);
        }
        User user = service.get(userId);
        logger.trace("user {} is selected ", user == null ? null :
                user.getId());
        request.setAttribute("user", user);
        return responsePage;
    }
}
