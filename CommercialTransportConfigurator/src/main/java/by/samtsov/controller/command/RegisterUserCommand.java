package by.samtsov.controller.command;

import by.samtsov.view.ForwardPage;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterUserCommand extends Command {

    private static Logger logger = LogManager.getLogger(LoginCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public ForwardPage execute(HttpServletRequest request,
                               HttpServletResponse response) throws InternalServerException, ServiceException {
//        setNextPage("/user/edit.html");
        ForwardPage forwardPage = new ForwardPage("/index.jsp");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserService userService = null;
        User user = null;

        userService = factory.createService(USER_ENTITY_TYPE);
        user = userService.create(login, email, password);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        String successMessage = String.format(
                "User \"%s\" is successfully registered", user.getLogin());
        logger.debug(successMessage);


        //проверить, подходят ли данные на стороне сервера
        //создать пользователя из условных запросов
        //сохранить пользователя в базе данных при помощи сервиса
        //вывести сообщение о том, что пользователь создан и перенаправить на нужную страницу.


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
