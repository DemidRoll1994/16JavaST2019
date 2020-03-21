package by.samtsov.service.command.authorizeduser;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditPersonalDataCommand extends AuthorizedUserCommand {

    private static Logger logger = LogManager.getLogger(
            EditPersonalDataCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;


    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response) throws InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/authorized/editPersonalData.jsp", false);
        UserService service = factory.createService(USER_ENTITY_TYPE);

        User user = service.get(getAuthorizedUser().getId());
        logger.trace("user {}, name {}, surname {}, email {}, address {}, " +
                        "company {}, phone {}", user.getId(), user.getName(),
                user.getSurname(), user.getEmail(), user.getAddress(),
                user.getCompanyName(), user.getPhoneNumber());
        request.setAttribute("user", user);
        return responsePage;
    }

}
