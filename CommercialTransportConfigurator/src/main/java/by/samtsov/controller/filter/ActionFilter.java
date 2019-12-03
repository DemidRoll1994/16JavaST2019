package by.samtsov.controller.filter;

import by.samtsov.controller.command.AvailableCommands;
import by.samtsov.controller.command.Command;
import by.samtsov.controller.command.CommandFactory;
import by.samtsov.controller.servlet.DispatcherServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFilter implements Filter {
    private static Logger logger = LogManager.getLogger(DispatcherServlet.class);

    private static Map<String, AvailableCommands> commands = new ConcurrentHashMap<>();

    static {
        commands.put("/", AvailableCommands.INDEX);
        commands.put("/index", AvailableCommands.INDEX);
        commands.put("/register", AvailableCommands.REGISTER);
        commands.put("/login", AvailableCommands.LOGIN);
        commands.put("/logout", AvailableCommands.LOGOUT);


/*
        actions.put("/profile/edit", ProfileEditAction.class);
        actions.put("/profile/save", ProfileSaveAction.class);

        actions.put("/reader/list", ReaderListAction.class);
        actions.put("/reader/edit", ReaderEditAction.class);
        actions.put("/reader/save", ReaderSaveAction.class);
        actions.put("/reader/delete", ReaderDeleteAction.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);

        actions.put("/author/list", AuthorListAction.class);
        actions.put("/author/edit", AuthorEditAction.class);
        actions.put("/author/save", AuthorSaveAction.class);
        actions.put("/author/delete", AuthorDeleteAction.class);

        actions.put("/author/book/list", BookListAction.class);
        actions.put("/author/book/edit", BookEditAction.class);
        actions.put("/author/book/save", BookSaveAction.class);
        actions.put("/author/book/delete", BookDeleteAction.class);

        actions.put("/search/book/form", SearchBookFormAction.class);
        actions.put("/search/book/result", SearchBookResultAction.class);
        actions.put("/author/book/usages", BookUsageListAction.class);

        actions.put("/search/reader/form", SearchReaderFormAction.class);
        actions.put("/search/reader/result", SearchReaderResultAction.class);
        actions.put("/reader/usages", ReaderUsageListAction.class);

        actions.put("/author/book/deliver", DeliverBookAction.class);
        actions.put("/author/book/return", ReturnBookAction.class);*/
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            String commandShortUri;
            int beginAction = contextPath.length();
            /*int endAction = uri.lastIndexOf('.');
            if(endAction >= 0) { todo del
                actionName = uri.substring(beginAction, endAction);
            } else {*/
            commandShortUri = uri.substring(beginAction);
            //todo del}

            try {
                logger.debug("short uri" + commandShortUri + "is selected");
                Command command = CommandFactory.createCommand(commands.get(commandShortUri));
                logger.debug("commmand" + commandShortUri + "is created");
                command.setShortUri(commandShortUri);
                httpRequest.setAttribute("command", command);
                logger.debug("commmand" + commandShortUri + "is set as " +
                        "attribute for httpRequest");
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (NullPointerException e) {
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}