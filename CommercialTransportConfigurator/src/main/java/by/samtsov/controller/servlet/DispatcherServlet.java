package by.samtsov.controller.servlet;

import by.samtsov.bean.ForwardPage;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.controller.command.Command;
import by.samtsov.controller.command.CommandManager;
import by.samtsov.controller.command.CommandManagerFactory;
import by.samtsov.dao.transaction.TransactionFactory;
import by.samtsov.service.mysql.MysqlServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(DispatcherServlet.class);

    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/library_db?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "library_user";
    public static final String DB_PASSWORD = "library_password";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    /*public void init() {
        try {
            Logger root = LogManager.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException | IOException e) {
            logger.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    public ServiceFactory getFactory() throws PersistentException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }*/

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response); // todo запретиить какое-то действие, если вручную передают параметры в строке
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //получаем готовый объект команды, которую мы должны будем выполнить
        Command command = (Command) request.getAttribute("command");
        try (CommandManager commandManager = CommandManagerFactory.getManager(getServiceFactory())) {
            //получаем объект текущей сессии
            HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                //смотрим, какие атрибуты переданы с предыдущей страницы сюда
                        Map<String, Object> attributes = (Map<String, Object>) session
                        .getAttribute("redirectedData");
                if (attributes != null) {
                    //все эти атрибуты добавляем к сессии, чтобы потом их можно было использовать в команде
                    for (String key : attributes.keySet()) {
                        request.setAttribute(key, attributes.get(key));
                    }
                    //ну и удаляем атрибут сессии, который уже использвоали
                    session.removeAttribute("redirectedData");
                }
            }

            //выполняем команду, с учетом переданных ему значений со страницы и сессии.
            ForwardPage forwardPage = commandManager.execute(command, request, response);
            command.execute(request, response);
            //-----------------------получили готовые данные, теперь их возвращаем обратно!!!-----------------------------------
            //сохраняем в сессию те атрибуты, которые должны быть переданы дальше.
            if (session != null && forwardPage != null && !forwardPage.getSessionAttributes().isEmpty()) {
                session.setAttribute("redirectedData", forwardPage.getSessionAttributes());
            }
            //узнаем куда отправляем пользователя дальше
            String requestedUri = request.getRequestURI();
            //узнаем каким способом отправляем пользователя
            if (forwardPage != null && forwardPage.isRedirect()) {
                //получаем полный путь для редиректа
                String redirectedUri = request.getContextPath() + forwardPage.getForward();
                logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
                //отправляем всех в дальний путь
                response.sendRedirect(redirectedUri);
            } else {
                // а если это не редирект
                String jspPage;
                if (forwardPage != null) {
                    jspPage = forwardPage.getForward();
                } else {
                    jspPage = command.getName() + ".jsp";
                }
                jspPage = "/WEB-INF/jsp" + jspPage;
                logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
            }
        } catch (PersistentException e) {
            logger.error("It is impossible to process request" + e.getMessage());
            request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        } /*catch (IncorrectDataException e) {
            logger.error("It is impossible to process request" + e.getMessage());
            request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }*/
    }

    private MysqlServiceFactory getServiceFactory() throws PersistentException {
        return new MysqlServiceFactory(new TransactionFactory());
    }
}
