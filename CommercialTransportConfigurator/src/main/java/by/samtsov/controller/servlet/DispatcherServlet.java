package by.samtsov.controller.servlet;

import by.samtsov.service.IncorrectDataException;
import by.samtsov.view.ResponsePage;
import by.samtsov.service.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.ServiceException;
import by.samtsov.service.command.Command;
import by.samtsov.service.command.CommandManager;
import by.samtsov.service.command.CommandManagerFactory;
import by.samtsov.dao.transaction.TransactionFactory;
import by.samtsov.service.sql.SQLServiceFactory;
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
    public static final String JSP_PATH = "/WEB-INF/jsp";
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
        process(request, response); // todo запретить какое-то действие, если вручную передают параметры в строке
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command command = (Command) request.getAttribute("command");
        try (CommandManager commandManager = CommandManagerFactory.getManager(getServiceFactory())) {
            HttpSession session = request.getSession(false);

            moveRedirectedDataFromSessionToRequest(session, request);
            ResponsePage responsePage = commandManager.execute(command, request, response);

            moveRedirectedDataFromRequestToSession(responsePage, session);

            makeResponse(request, response, responsePage, command.getName());


        } catch (IncorrectDataException e) {
            logger.debug("incorrect data : " + command.getName() + ". Error: ", e);
            request.setAttribute("error", "Неверно введены данные." + e.getErrorMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        } catch (PersistenceException | InternalServerException | ServiceException e) {
            logger.error("It is impossible to process command: " + command.getName() + ". Error: \n", e);
            request.setAttribute("error", "Ошибка обработки данных. " +
                    "Обратитесь к администратору или попробуйте позднее");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    private void makeResponse(HttpServletRequest request, HttpServletResponse response, ResponsePage responsePage, String commandName) throws IOException, ServletException {
        String requestedUri = request.getRequestURI();
        logger.debug(String.format("ForwardPage is null: %b, redirect : %b", responsePage == null, responsePage != null ? responsePage.isRedirect() : null));
        if (responsePage != null && responsePage.isRedirect()) {
            String redirectedUri = request.getContextPath() + responsePage.getJspName();
            logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
            response.sendRedirect(redirectedUri);
        } else {
            String jspPage;
            if (responsePage != null) {
                jspPage = JSP_PATH + responsePage.getJspName();
            } else {
                jspPage = JSP_PATH + commandName + ".jsp";
            }
            logger.trace(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
            getServletContext().getRequestDispatcher(jspPage).forward(request, response);
        }
    }

    private void moveRedirectedDataFromRequestToSession(ResponsePage responsePage, HttpSession session) {
        if (session != null && responsePage != null && !responsePage.getRedirectedAttributes().isEmpty()) {
            session.setAttribute("redirectedData", responsePage.getRedirectedAttributes());
        }
    }

    private void moveRedirectedDataFromSessionToRequest(HttpSession session, HttpServletRequest request) {
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> attributes = (Map<String, Object>) session
                    .getAttribute("redirectedData");
            if (attributes != null) {
                for (String key : attributes.keySet()) {
                    request.setAttribute(key, attributes.get(key));
                }
                session.removeAttribute("redirectedData");
            }
        }
    }

    private SQLServiceFactory getServiceFactory() throws PersistenceException {
        return new SQLServiceFactory(new TransactionFactory());
    }
}
