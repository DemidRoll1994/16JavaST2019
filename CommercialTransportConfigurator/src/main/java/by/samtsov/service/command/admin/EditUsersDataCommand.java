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
import java.util.List;

public class EditUsersDataCommand extends AdminCommand {

    private static final EntityType USER_ENTITY_TYPE = EntityType.USER;
    private static Logger logger = LogManager.getLogger(
            EditUsersDataCommand.class);

    @Override
    public ResponsePage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException, InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/admin/usersList.jsp"
                , false);
        UserService service = factory.createService(USER_ENTITY_TYPE);

        List<User> users = service.getAll();
        logger.trace("users array size {}", users == null ? null :
                users.size());
        request.setAttribute("users", users);
        return responsePage;
    }
}
