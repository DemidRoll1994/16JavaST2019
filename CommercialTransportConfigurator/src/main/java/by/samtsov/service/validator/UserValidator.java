package by.samtsov.service.validator;

import by.samtsov.bean.User;
import by.samtsov.bean.exceptions.IncorrectDataException;

public class UserValidator extends Validator<User> {

    @Override
    boolean isValid(User user) throws IncorrectDataException {
        return user != null && user.getId() > 0 && user.getLogin()!=null
                && user.getPasswordHash() != null && user.getStatus() != null
                && user.getRole() != null;
    }
}
