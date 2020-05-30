package by.samtsov.service.validator.impl;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.validator.UserValidator;
import by.samtsov.service.validator.Validator;

import java.util.regex.Pattern;

public class UserValidatorImpl extends Validator<User> implements UserValidator {
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
    public boolean isValid(User user) throws IncorrectDataException {
        return user != null && user.getId() > 0 && isEmailValid(user.getEmail())
                && isRoleValid(user.getRole())
                && isStatusValid(user.getStatus())
                && isNameValid(user.getName())
                && isSurnameValid(user.getSurname());
    }


    public boolean isPasswordValid(String password) {
        return isValidRequiredString(password, REGEX_PASSWORD_PATTERN);
    }

    public boolean isEmailValid(String email) {
        return isValidRequiredString(email, EMAIL_REGEX_PATTERN);
    }

    public boolean isNameValid(String name) {
        return isValidRequiredString(name, NAME_REGEX_PATTERN);
    }

    public boolean isSurnameValid(String surname) {
        return isValidRequiredString(surname, SURNAME_REGEX_PATTERN);
    }

    @Override
    public boolean isPhoneNumberValid(long phoneNumber) {
        return phoneNumber >= 0;
    }

    @Override
    public boolean isRoleValid(Role role) {
        return role != null;
    }

    @Override
    public boolean isStatusValid(UserStatus status) {
        return status != null;
    }
}
