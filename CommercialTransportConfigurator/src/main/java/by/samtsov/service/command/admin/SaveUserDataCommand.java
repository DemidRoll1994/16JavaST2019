package by.samtsov.service.command.admin;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.service.command.authorizeduser.AuthorizedUserCommand;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.samtsov.bean.type.InternalServerError.INVALID_PASSWORD_FORM;

public class SaveUserDataCommand extends AuthorizedUserCommand {

    private static Logger logger = LogManager.getLogger(
            SaveUserDataCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;


    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response) throws InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/users/saveUser.action",
                true);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String companyName = request.getParameter("companyName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        UserStatus status = UserStatus.valueOf(request.getParameter("status"));
        Role role = Role.valueOf(request.getParameter("role"));
        UserService service = factory.createService(USER_ENTITY_TYPE);
        User user = service.get(getAuthorizedUser().getId());
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setCompanyName(companyName);
        try {
            if (phoneNumber != null) {
                user.setPhoneNumber(Long.parseLong(phoneNumber));
            }
        } catch (NumberFormatException e) {
            logger.debug("Phone number {} is invalid", user.getPhoneNumber());
            throw new IncorrectDataException(INVALID_PASSWORD_FORM);
        }
        user.setAddress(address);
        user.setRole(role);
        user.setStatus(status);

        service.updateUserData(user);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser"
                , service.prepareToWriteInSession(user));
        logger.debug("user {} is updated with values : name{}, surname{}, " +
                        "email{}, companyName{}, phoneNumber{}, address{}",
                user.getId(), name, surname, email, companyName, phoneNumber
                , address);
        return responsePage;
    }

}
