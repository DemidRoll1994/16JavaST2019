package by.samtsov.service.validator;

import by.samtsov.bean.entity.User;
import by.samtsov.service.IncorrectDataException;

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
            "^[a-zа-я]{2,255}$");
    private static final Pattern SURNAME_REGEX_PATTERN = Pattern.compile(
            "^([a-zа-я]+[',.-]?[a-zа-я]+){2,255}$",Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(User user) throws IncorrectDataException {
        return user != null && user.getId() > 0 && user.getLogin() != null
                && user.getPasswordHash() != null && user.getStatus() != null
                && user.getRole() != null;
    }

    public boolean isPasswordValid(String password) throws IncorrectDataException {
        return isValueMatchPatter(password, REGEX_PASSWORD_PATTERN);
    }


    public boolean isLoginValid(String login) throws IncorrectDataException {
        return isValueMatchPatter(login, LOGIN_REGEX_PATTERN);
    }

    public boolean isEmailValid(String email) throws IncorrectDataException {
        return isValueMatchPatter(email, EMAIL_REGEX_PATTERN);
    }

    public boolean isNameValid(String email) throws IncorrectDataException {
        return isValueMatchPatter(email, NAME_REGEX_PATTERN);
    }

    public boolean isSurnameValid(String email) throws IncorrectDataException {
        return isValueMatchPatter(email, SURNAME_REGEX_PATTERN);
    }

    public boolean isValueMatchPatter(String value, Pattern pattern) throws IncorrectDataException {
        if (value != null) {
            Matcher matcher = pattern.matcher(value);
            return matcher.find();
        }
        return false;
    }


}
