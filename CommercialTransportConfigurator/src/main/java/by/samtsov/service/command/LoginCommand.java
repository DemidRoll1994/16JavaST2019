package by.samtsov.service.command;

import by.samtsov.view.ResponsePage;
import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.Role;
import by.samtsov.service.InternalServerException;
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

    public Set<Role> getAllowedRoles() {
        return null;
    }

    @Override
    public ResponsePage execute(HttpServletRequest request,
                                HttpServletResponse response)
            throws InternalServerException, ServiceException {
        ResponsePage responsePage = new ResponsePage("/", true);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && password != null) {
            UserService service = factory.createService(EntityType.USER);
            User user = service.findByEmailAndPassword(email, password);
            service.prepareToWriteInSession(user);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                //session.setAttribute("menu", menu.get(user.getRole()));
                logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            } else {
                request.setAttribute("message", "Имя пользователя или пароль не опознанны");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", email, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return responsePage;
    }
}
