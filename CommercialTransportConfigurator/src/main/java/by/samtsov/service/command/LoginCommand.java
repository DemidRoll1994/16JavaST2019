package by.samtsov.service.command;

import by.samtsov.bean.MenuItem;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import by.samtsov.view.ResponsePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class LoginCommand extends Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    private static Map<Role, ArrayList> menu = new HashMap<>();

    static {
        menu.put(Role.ADMIN, new ArrayList<>(Arrays.asList(
                new MenuItem("users/list", "список пользователей")
        )));
        menu.put(Role.BUYER, new ArrayList<>(Arrays.asList(
        )));
        menu.put(Role.VENDOR, new ArrayList<>(Arrays.asList(
        )));
    }

    public Set<Role> getAllowedRoles() {
        return null;
    }

    @Override
    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response)
            throws InternalServerException, ServiceException {
        ResponsePage responsePage = new ResponsePage("/", true); //todo редирект не туда
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && password != null) {
            UserService service = factory.createService(EntityType.USER);
            User user = service.findByEmailAndPassword(email, password);
            service.prepareToWriteInSession(user);
            if (user != null) {
                if (user.getStatus() == UserStatus.ACTIVE) {
                    HttpSession session = request.getSession();
                    session.setAttribute("authorizedUser", user);
                    session.setAttribute("menu", menu.get(user.getRole()));
                    logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                } else if (user.getStatus() == UserStatus.NOT_ACTIVATE) {
                    logger.trace(String.format("not activacted user \"%s\" is trying log in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                    request.setAttribute("message", "Пользователь не активен");
                } else if (user.getStatus() == UserStatus.BLOCKED) {
                    logger.info(String.format("blocked user \"%s\" is trying log in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                    request.setAttribute("message", "Пользователь заблокирован");
                }
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не найдены");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return responsePage;
    }
}
