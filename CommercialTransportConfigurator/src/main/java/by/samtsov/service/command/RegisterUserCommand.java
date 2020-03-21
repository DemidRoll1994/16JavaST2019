package by.samtsov.service.command;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.service.InternalServerException;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class RegisterUserCommand extends Command {

    private static Logger logger = LogManager.getLogger(
            RegisterUserCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response) throws InternalServerException, ServiceException {
//        setNextPage("/user/edit.html");v

        ResponsePage responsePage = new ResponsePage("/", true);
        request.getRequestURI();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.debug("trying to create user: name: {}, surname: {}, " +
                "email: {}, password: {}", name, surname, email, password);

        UserService userService = factory.createService(USER_ENTITY_TYPE);
        User user = userService.create(name, surname, email, password);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        logger.debug("User {} is successfully registered", user.getEmail());


        //проверить, подходят ли данные на стороне сервера
        //создать пользователя из условных запросов
        //сохранить пользователя в базе данных при помощи сервиса
        //вывести сообщение о том, что пользователь создан и перенаправить на нужную страницу.

        //todo del comments
        /*HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        String successMessage = String.format(
                "User \"%s\" is successfully registered", user.getLogin());
        logger.debug(successMessage);

        String redirect = request.getHeader("referer");
        messageMap.put("redirect", redirect);
        json = new Gson().toJson(messageMap);*/

        request.setAttribute("user", user);
        return responsePage;
    }


    public Set<Role> getAllowRoles() {
        return null;
    }


}
