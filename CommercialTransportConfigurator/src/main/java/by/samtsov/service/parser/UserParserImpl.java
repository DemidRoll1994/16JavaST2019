package by.samtsov.service.parser;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.service.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public class UserParserImpl implements UserParser {


    private static final String NAME_NAME = "name";
    private static final String SURNAME_NAME = "surname";
    private static final String ID_NAME="id";
    private static final String EMAIL_NAME="email";
    private static final String ROLE_NAME="role";
    private static final String STATUS_NAME="status";
    private static final String COMPANY_NAME="companyName";
    private static final String PHONE_NAME="phoneNumber";
    private static final String ADDRESS_NAME="address";

    @Override
    public User parse(HttpServletRequest request) throws IncorrectDataException{ //todo
        User user = new User();
        String parameter = request.getParameter(ID_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            try {
                user.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectDataException(ID_NAME, e);
            }
        } else {
            throw new IncorrectDataException(ID_NAME);
        }

        parameter = request.getParameter(EMAIL_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            user.setEmail(parameter);
        } else {
            throw new IncorrectDataException(EMAIL_NAME);
        }

        parameter = request.getParameter(NAME_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            user.setName(parameter);
        } else {
            throw new IncorrectDataException(NAME_NAME);
        }

        parameter = request.getParameter(SURNAME_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            user.setSurname(parameter);
        } else {
            throw new IncorrectDataException(SURNAME_NAME);
        }

        parameter = request.getParameter(ROLE_NAME);
        if (parameter != null && !parameter.isEmpty()) {
            try {
                user.setRole(Role.getByIdentity(Integer.parseInt(parameter)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IncorrectDataException(ROLE_NAME, e);
            }
        } else {
            throw new IncorrectDataException(ROLE_NAME);
        }

        parameter = request.getParameter(STATUS_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setStatus(UserStatus.getByIdentity(Integer.parseInt(parameter)));
            } else {
                throw new IncorrectDataException(STATUS_NAME);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IncorrectDataException(STATUS_NAME, e);
        }

        parameter = request.getParameter(COMPANY_NAME);
        user.setCompanyName(parameter);

        parameter = request.getParameter(PHONE_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setPhoneNumber(Long.parseLong(parameter));
            }
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(PHONE_NAME, e);
        }

        parameter = request.getParameter(ADDRESS_NAME);
        try {
            if (parameter != null && !parameter.isEmpty()) {
                user.setPhoneNumber(Long.parseLong(parameter));
            }
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(ADDRESS_NAME, e);
        }
        return user;
    }
}
