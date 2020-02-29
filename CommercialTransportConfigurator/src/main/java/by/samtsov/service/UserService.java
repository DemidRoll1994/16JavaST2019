package by.samtsov.service;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.PersistentException;

public interface UserService extends Service<User> {

    User findByLoginAndPassword(String login, String password) throws PersistentException, IncorrectDataException;

    void updatePassword(User user, String newPassword) throws PersistentException, IncorrectDataException;
}
