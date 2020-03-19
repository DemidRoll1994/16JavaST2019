package by.samtsov.service.command;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.service.validator.UserValidator;
import by.samtsov.service.validator.ValidatorFactory;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        User authorizedUser = getAuthorizedUser();
        updateUser(authorizedUser, name, surname, email, companyName
                , phoneNumber, address);

        setAuthorizedUser(authorizedUser);

        UserService userService = factory.createService(USER_ENTITY_TYPE);
/*
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        logger.debug("User {} is successfully registered", user.getEmail());


        /*HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        String successMessage = String.format(
                "User \"%s\" is successfully registered", user.getLogin());
        logger.debug(successMessage);

        String redirect = request.getHeader("referer");
        messageMap.put("redirect", redirect);
        json = new Gson().toJson(messageMap);*//*

        request.setAttribute("user", user);*/
        return responsePage;
    }

    private boolean updateUser(User authorizedUser, String name, String surname
            , String email, String companyName, String phoneNumber
            , String address) throws InternalServerException {

        logger.trace("trying to edit personal user data: name: {}, surname: " +
                        "{}, email: {}, companyName: {}, phoneNumber: {}, address: {}"
                , name, surname, email, companyName, phoneNumber, address);
        UserValidator userValidator = ValidatorFactory
                .createValidator(USER_ENTITY_TYPE);

        //todo write code
        return false;
    }
}
