package by.samtsov.service;

import by.samtsov.bean.entity.User;

public interface UserService extends Service<User> {
    /**
     * Update personal data of user. can be used only for updating data by
     * user. updates name, surname, email, companyName, phoneNumber and address
     * @param newUser instance to update.
     * @return updated user instance.
     * @throws ServiceException if exception occurs on service layer. Can be
     * used for throw IncorrectDataException
     */
    User updatePersonalData(User newUser) throws ServiceException;
    /**
     * Update personal data of user by admin. can be used only for updating data
     * by admin. updates name, surname, email, companyName, phoneNumber,
     * address, role and status.
     * @param newUser instance to update.
     * @return updated user instance.
     * @throws ServiceException if exception occurs on service layer. Can be
     * used for throw IncorrectDataException
     */
    User updateUserData(User newUser) throws ServiceException;

    /**
     * Check and update password if old password is correct and new password
     * is valid.
     * @param newUser - user to update password
     * @param oldPassword - old password value
     * @param newPassword - new password value
     * @throws ServiceException if exception occurs on service layer. Can be
     * used for throw IncorrectDataException
     */
    void updatePassword(User newUser, String oldPassword,
                         String newPassword) throws ServiceException;

    /**
     * Search user by login and password.
     * @param email - user email
     * @param password - user password
     * @return user instance if user exist
     * @throws ServiceException if exception occurs on service layer. Can be
     * used for throw IncorrectDataException
     * @throws IncorrectDataException if user with entered parameters doesn't
     * exists
     * @throws InternalServerException
     */
    User findByEmailAndPassword(String email, String password) throws ServiceException, InternalServerException;

    /**
     * Create new user. User must be not activated with role = BUYER.
     * @param name - person first name
     * @param surname - person second name
     * @param email - user email
     * @param password - user password
     * @return created user
     * @throws IncorrectDataException if user with entered parameters already
     * exists or entered data is invalid form
     * @throws ServiceException if another exception occurs on service layer.
     * Can be used for throw another IncorrectDataException
     * @throws InternalServerException
     */
    User create( String name, String surname, String email, String password) throws ServiceException, InternalServerException;

    /**
     * Clears data from user to write in session. Must leaves name, surname,
     * email and role and cleans another.
     * @param user - user to prepare
     * @return prepared user
     */
    User prepareToWriteInSession(User user);

    void activateUser(int id) throws ServiceException;

    void blockUser(int id) throws ServiceException;
}
