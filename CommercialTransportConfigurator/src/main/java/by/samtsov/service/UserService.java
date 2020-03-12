package by.samtsov.service;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.InternalServerException;

public interface UserService extends Service<User> {

    User findByEmailAndPassword(String login, String password) throws ServiceException, InternalServerException;

    User create( String name, String surname, String email, String password) throws ServiceException, InternalServerException;

    void updatePassword(User user, String newPassword) throws ServiceException, InternalServerException;
}
