package by.samtsov.controller.command;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand extends Command {

    private static Logger logger = LogManager.getLogger(LoginCommand.class);
    private final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public ForwardPage execute(HttpServletRequest request, HttpServletResponse response) throws PersistenceException {
//        setNextPage("/user/edit.html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserService userService = factory.createService(USER_ENTITY_TYPE);
        User user = userService.save(login, email, password);
        //проверить, подходят ли данные на стороне сервера
        //создать пользователя из условных запросов
        //сохранить пользователя в базе данных при помощи сервиса
        //вывести сообщение о том, что пользователь создан и перенаправить на нужную страницу.












        UserService userService = factory.createService(USER_ENTITY_TYPE);
        User user = userService.save(login, email, password);
        HttpSession session = request.getSession();
        session.setAttribute("authorizedUser", user);
        String successMessage = String.format(
                "User \"%s\" is successfully registered", user.getLogin());
        LOGGER.debug(successMessage);

        String redirect = request.getHeader("referer");
        messageMap.put("redirect", redirect);
        json = new Gson().toJson(messageMap);
    }


}
