package by.samtsov.service.command.buyer;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.service.command.Command;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.samtsov.bean.type.InternalServerError.INVALID_PASSWORD_FORM;

public class EditPersonalDataCommand extends Command {

    private static Logger logger = LogManager.getLogger(
            EditPersonalDataCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;


    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response) throws InternalServerException, ServiceException {

        ResponsePage responsePage = new ResponsePage("/", true);
        request.getRequestURI();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String companyName = request.getParameter("companyName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        UserService service = factory.createService(USER_ENTITY_TYPE);
        User user = new User();
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

        service.updatePersonalData(user);
        logger.debug("user {} is updated with values : name{}, surname{}, " +
                        "email{}, companyName{}, phoneNumber{}, address{}",
                user, name, surname, email, companyName, phoneNumber, address);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        logger.debug("User {} is successfully registered", user.getEmail());
        return responsePage;
    }

}
