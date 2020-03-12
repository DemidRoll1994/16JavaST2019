package by.samtsov.controller.command;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.type.EntityType;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.view.ForwardPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterUserCommand extends Command {

    private static Logger logger =
            LogManager.getLogger(RegisterUserCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public ForwardPage execute(HttpServletRequest request,
                               HttpServletResponse response) throws InternalServerException, ServiceException {
//        setNextPage("/user/edit.html");v

        ForwardPage forwardPage = new ForwardPage("/index.jsp");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.debug(String.format("trying to create user: name: %s," +
                " surname: %s, email: %s, password: %s", name, surname, email
                , password));

        UserService userService = factory.createService(USER_ENTITY_TYPE);
        User user = userService.create(name, surname, email, password);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        logger.debug(String.format(
                "User \"%s\" is successfully registered", user.getLogin()));


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
        forwardPage.setRedirect(false);
        return forwardPage;
    }


}
