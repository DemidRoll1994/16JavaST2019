package by.samtsov.controller.command;

import by.samtsov.view.ForwardPage;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class LoginCommand extends Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);

    /*private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

    static {
        menu.put(Role.LIBRARIAN, new ArrayList<>(Arrays.asList(
                new MenuItem("/search/book/form.html", "поиск книг"),
                new MenuItem("/search/reader/form.html", "поиск читателей")
        )));
        menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem("/reader/list.html", "читатели"),
                new MenuItem("/user/list.html", "сотрудники")
        )));
        menu.put(Role.REGISTRAR, new ArrayList<>(Arrays.asList(
                new MenuItem("/author/list.html", "авторы")
        )));
    }*/

    //@Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public ForwardPage execute(HttpServletRequest request,
                               HttpServletResponse response)
            throws InternalServerException, ServiceException {

        ForwardPage forwardPage = new ForwardPage("/index.jsp");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            UserService service = factory.createService(EntityType.USER);
            User user  = service.findByLoginAndPassword(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                //session.setAttribute("menu", menu.get(user.getRole()));
                logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не опознанны");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        forwardPage.setRedirect(false);
        return forwardPage;
    }
}
