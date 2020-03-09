package by.samtsov.service.validator;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.IncorrectDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final Pattern REGEX_PASSWORD_PATTERN = Pattern.compile(
            "(?=.*[0-9])(?=.*[!-\\/:-@\\[-`{-~])(?=.*[a-z])(?=.*[A-Z])" +
                    "[0-9a-zA-Z!-\\/:-@\\[-`{-~]{6,}");
    private static final Pattern LOGIN_REGEX_PATTERN = Pattern.compile(
            "[a-z0-9_-]{3,16}");
    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile(
            "([\\w\\._]+)@([\\w\\._]+)\\.([a-z]{2,6}\\.?)");
    private static final Pattern NAME_REGEX_PATTERN = Pattern.compile(
            "[A-Za-zА-Яа-я]{2,20}");

    @Override
    public boolean isValid(User user) throws IncorrectDataException {
        return user != null && user.getId() > 0 && user.getLogin() != null
                && user.getPasswordHash() != null && user.getStatus() != null
                && user.getRole() != null;
    }

    public boolean isPasswordValid(String password) throws IncorrectDataException {
        if (password != null) {
            Matcher matcher = REGEX_PASSWORD_PATTERN.matcher(password);
            return matcher.find();
        }
        return false;
    }


    public boolean isLoginValid(String login) throws IncorrectDataException {
        if (login != null) {
            Matcher matcher = LOGIN_REGEX_PATTERN.matcher(login);
            return matcher.find();
        }
        return false;
    }

    public boolean isEmailValid(String email) throws IncorrectDataException {
        if (email != null) {
            Matcher matcher = EMAIL_REGEX_PATTERN.matcher(email);
            return matcher.find();
        }
        return false;
    }


}
