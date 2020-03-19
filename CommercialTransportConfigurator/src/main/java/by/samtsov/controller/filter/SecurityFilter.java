package by.samtsov.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static Logger logger = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        /*if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Command command = (Command) httpRequest.getAttribute("action");
            Set<Role> allowedRoles = command.getAllowedRoles();
            String userName = "unauthorized user";
            User user = null;
            HttpSession session = httpRequest.getSession(false);
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                command.setAuthorizedUser(user);
                String errorMessage = (String)session.getAttribute("SecurityFilterMessage");
                if(errorMessage != null) {
                    httpRequest.setAttribute("error", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }

            if (user != null) {
                userName = "\"" + user.getEmail() + "\" user";
            }
            if (allowedRoles == null || user == null
                    || allowedRoles.contains(user.getRole())) {
                filterChain.doFilter(request, response);
            } else {
                logger.info("Trying of {} access to forbidden resource \"{}\"", userName, command.getName()));
                if (session != null /*&& command.getClass() != MainAction.class todo del this*//*) {
                    session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
                }
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/forbidden.jsp");
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }*/
    }

    @Override
    public void destroy() {

    }
}
