package by.samtsov.service;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.ServiceException;

public interface UserService extends Service<User> {

    User findByLoginAndPassword(String login, String password) throws ServiceException, InternalServerException;

    User create(String login, String email, String password) throws ServiceException, InternalServerException;

    void updatePassword(User user, String newPassword) throws ServiceException, InternalServerException;
}
