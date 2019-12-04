package by.samtsov.service.parser;

import by.samtsov.bean.User;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.enums.UserStatus;
import by.samtsov.bean.exceptions.IncorrectDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserParserImpl implements UserParser {


    private final String ID_NAME="id";
    private final String LOGIN_NAME="login";
    private final String ROLE_NAME="role";
    private final String STATUS_NAME="status";
    private final String COMPANY_NAME="companyName";
    private final String PHONE_NAME="phoneNumber";
    private final String ADDRESS_NAME="address";

    @Override
    public User parse(HttpServletRequest request) { //todo
        User user = new User();
        String parameter = request.getParameter(ID_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            try {
                user.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectDataException(ID_NAME, parameter);
            }
        } else {
            throw new IncorrectDataException(ID_NAME, parameter);
        }

        parameter = request.getParameter(LOGIN_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectDataException(LOGIN_NAME, parameter);
        }

        parameter = request.getParameter(ROLE_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            try {
                user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IncorrectDataException(ROLE_NAME, parameter);
            }
        } else {
            throw new IncorrectDataException(ROLE_NAME, parameter);
        }

        parameter = request.getParameter(STATUS_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setStatus(UserStatus.getByIdentity(Integer.parseInt(parameter)));
            } else {
                throw new IncorrectDataException(STATUS_NAME, parameter);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectDataException(STATUS_NAME, parameter);
        }

        parameter = request.getParameter(COMPANY_NAME);
        user.setCompanyName(parameter);

        parameter = request.getParameter(PHONE_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setPhoneNumber(Long.parseLong(parameter));
            }
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(PHONE_NAME, parameter);
        }

        parameter = request.getParameter(ADDRESS_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setPhoneNumber(Long.parseLong(parameter));
            }
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(ADDRESS_NAME, parameter);
        }
        return user;
    }
}
