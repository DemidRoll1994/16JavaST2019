package by.samtsov.controller.filter;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.service.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            logger.debug(httpRequest.getAttribute("command"));
            Command command = (Command) httpRequest.getAttribute("command");
            Set<Role> allowedRoles = command.getAllowedRoles();
            String userName = "unauthorized user";
            User user = null;
            HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                command.setAuthorizedUser(user);
                String errorMessage = (String) session.getAttribute("SecurityFilterMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("error", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }

            if (user != null) {
                userName = "\"" + user.getEmail() + "\" user";
            }
            if (allowedRoles == null || (user != null && allowedRoles
                    .contains(user.getRole()))) {
                filterChain.doFilter(request, response);
            } else {
                logger.trace("allowedRoles is null {}, user is null {} ",
                        allowedRoles == null, user == null);
                logger.info("{} is trying to access to forbidden resource \"{}\"", userName, command.getName());
                if (session != null /*&& command.getClass() != MainAction.class todo del this*/) {
                    session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
                }
                request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/forbidden.jsp").forward(request, response);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
