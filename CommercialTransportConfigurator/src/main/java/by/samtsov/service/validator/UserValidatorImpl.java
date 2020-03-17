package by.samtsov.service.validator;

import by.samtsov.bean.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private static final String ANY_LET_REGEX = "[a-zа-яA-ZА-ЯёўіЁЎІ]";
    private static final Pattern REGEX_PASSWORD_PATTERN = Pattern.compile(
            "(?=.*[0-9])(?=.*[!-\\/:-@\\[-`{-~])(?=.*[a-z])(?=.*[A-Z])" +
                    "[0-9a-zA-Z!-\\/:-@\\[-`{-~]{6,}");
    private static final Pattern LOGIN_REGEX_PATTERN = Pattern.compile(
            "[a-z0-9_-]{3,16}");
    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile(
            "([\\w\\._]+)@([\\w\\._]+)\\.([a-z]{2,6}\\.?)");
    private static final Pattern NAME_REGEX_PATTERN = Pattern.compile(
            "^" + ANY_LET_REGEX + "{2,255}$");
    private static final Pattern SURNAME_REGEX_PATTERN = Pattern.compile(
            "^(" + ANY_LET_REGEX + "+[',.-]?" + ANY_LET_REGEX
                    + "+){1,127}$");

    @Override
    public boolean isValid(User user) {
        return user != null && user.getId() > 0 && user.getEmail() != null
                && user.getPasswordHash() != null && user.getStatus() != null
                && user.getRole() != null;
    }

    public boolean isPasswordValid(String password){
        return isValueMatchPatter(password, REGEX_PASSWORD_PATTERN);
    }

    public boolean isEmailValid(String email){
        return isValueMatchPatter(email, EMAIL_REGEX_PATTERN);
    }

    public boolean isNameValid(String email){
        return isValueMatchPatter(email, NAME_REGEX_PATTERN);
    }

    public boolean isSurnameValid(String email) {
        return isValueMatchPatter(email, SURNAME_REGEX_PATTERN);
    }

    public boolean isValueMatchPatter(String value, Pattern pattern) {
        if (value != null) {
            Matcher matcher = pattern.matcher(value);
            return matcher.find();
        }
        return false;
    }


}
