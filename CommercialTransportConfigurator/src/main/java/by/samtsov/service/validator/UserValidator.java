package by.samtsov.service.validator;

import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;

public interface UserValidator {

    boolean isPasswordValid(String password);

    boolean isEmailValid(String email);

    boolean isNameValid(String name);

    boolean isSurnameValid(String surname);


    boolean isPhoneNumberValid(long phoneNumber);

    boolean isRoleValid(Role role);

    boolean isStatusValid(UserStatus status);
}
