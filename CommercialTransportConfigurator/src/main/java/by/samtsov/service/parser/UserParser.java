package by.samtsov.service.parser;

import by.samtsov.bean.User;
import by.samtsov.bean.exceptions.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public class UserParser implements Parser<User> {

    @Override
    public User parse(HttpServletRequest request) { //todo
        User user = new User();
        String parameter = request.getParameter("identity");
        if(parameter != null) {
            try {
                user.setId(Integer.parseInt(parameter));
            } catch(NumberFormatException e) {
                throw new IncorrectDataException("identity", parameter);
            }
        }
        parameter = request.getParameter("login");
        if(parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectDataException("login", parameter);
        }
        parameter = request.getParameter("role");
        try {
            user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectDataException("role", parameter);
        }
        return user;
    }
}
