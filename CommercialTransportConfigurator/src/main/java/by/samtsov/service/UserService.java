package by.samtsov.service;

import by.samtsov.bean.entity.User;

public interface UserService extends Service<User> {

    User updatePersonalData(User newUser) throws ServiceException;

    User updateUserData(User newUser) throws ServiceException;

    void updatePassword(User newUser, String oldPassword, String newPassword) throws ServiceException;

    User findByEmailAndPassword(String login, String password) throws ServiceException, InternalServerException;

    User create( String name, String surname, String email, String password) throws ServiceException, InternalServerException;

    User prepareToWriteInSession(User user);

    void activateUser(int id) throws ServiceException;

    void blockUser(int id) throws ServiceException;
}
