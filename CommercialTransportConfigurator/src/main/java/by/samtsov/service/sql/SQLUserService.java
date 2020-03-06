package by.samtsov.service.sql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.enums.UserStatus;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.bean.exceptions.ServiceException;
import by.samtsov.dao.UserDao;
import by.samtsov.service.UserPasswordService;
import by.samtsov.service.UserService;
import by.samtsov.service.validator.UserValidatorImpl;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.samtsov.bean.enums.InternalServerErrors.*;

public class SQLUserService extends SQLService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(
            SQLUserService.class);
    public static final EntityType USER_ENTITY_TYPE = EntityType.USER;
    public static final String CREATE_USER_ERR_MSG = "can't create user with login ";
    public static final String ROLLBACK_CREATE_ERR_MSG = "can't rollback a transaction while creating user with login ";
    public static final String FIND_USER_ERR_MSG = "can't find user ";

    @Override
    public User get(int id) throws ServiceException, InternalServerException {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.get(id);
        } catch (PersistenceException e) {
            LOGGER.error("Can't read user with id " + id + " from dao layer: \n" + e.getMessage()); //todo
        }
        return null;
    }

    @Override
    public List<User> getAll()throws ServiceException, InternalServerException  {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.getAll();
        } catch (PersistenceException e) {
            LOGGER.error("Can't read users from dao layer: \n" + e.getMessage());//todo
        }
        return null;
    }

    @Override
    public int save(User user) throws ServiceException, InternalServerException {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.add(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't add user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
        return 0;
    }

    @Override
    public void update(User user) throws ServiceException, InternalServerException {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.update(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't update user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public void delete(int id) throws ServiceException, InternalServerException {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.delete(id);
        } catch (PersistenceException e) {
            LOGGER.error("Can't delete user with id " + id
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public void updatePassword(User user, String newPassword) throws ServiceException, InternalServerException {
        try {
            UserPasswordService userPasswordService = new UserPasswordService();
            String salt = userPasswordService.generateSalt();
            user.setSalt(salt);
            user.setPasswordHash(userPasswordService
                    .generateSecurePassword(newPassword, salt));
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.update(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't update password of user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException, InternalServerException {
        UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
        UserPasswordService userPasswordService = new UserPasswordService();
        User user = null;
        try {
            user = userDao.getByLogin(login);
        } catch (PersistenceException e) {
            throw new ServiceException(FIND_USER_ERR_MSG, e);
        }
        if (user != null && userPasswordService.verifyUserPassword(password,
                user.getPasswordHash(), user.getSalt())) {
            return user;
        } else {
            throw new IncorrectDataException("Incorrect username or password");
        }
    }


    public User create(String login, String email, String password) throws ServiceException, InternalServerException {
        UserValidatorImpl userValidator = ValidatorFactory.createValidator(USER_ENTITY_TYPE);
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            if (!userValidator.isLoginValid(login)) {
                throw new IncorrectDataException(INVALID_LOGIN_FORM);
            }
            if (!userValidator.isPasswordValid(email)) {
                throw new IncorrectDataException(INVALID_PASSWORD_FORM);
            }
            if (!userValidator.isEmailValid(password)) {
                throw new IncorrectDataException(INVALID_EMAIL_FORM);
            }

            if (userDao.getByLogin(login) != null) {
                throw new IncorrectDataException(LOGIN_ALREADY_EXISTS);
            }
            if (userDao.getByEmail(email) != null) {
                throw new IncorrectDataException(EMAIL_ALREADY_EXISTS);
            }

            User user = new User();
            user.setLogin(login);
            user.setEmail(email);

            UserPasswordService userPasswordService = new UserPasswordService();
            String salt = userPasswordService.generateSalt();
            String passwordHash = userPasswordService.generateSecurePassword(password, salt);

            user.setPasswordHash(passwordHash);
            user.setSalt(salt);
            user.setRole(Role.BUYER);
            user.setStatus(UserStatus.NOT_ACTIVATE);
            int userId = userDao.add(user);
            transaction.commit();
            return clearPassword(get(userId));
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + login, e);
            }
            throw new ServiceException(CREATE_USER_ERR_MSG + login, e);
        }
    }

    private User clearPassword(User user) {
        user.setPasswordHash(null);
        user.setSalt(null);
        return user;
    }


}
