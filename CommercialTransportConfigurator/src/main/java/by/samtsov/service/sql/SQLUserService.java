package by.samtsov.service.sql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.service.ServiceException;
import by.samtsov.dao.UserDao;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.UserPasswordService;
import by.samtsov.service.UserService;
import by.samtsov.service.validator.UserValidatorImpl;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.samtsov.bean.type.InternalServerErrors.*;

public class SQLUserService extends SQLService implements UserService {

    public static final EntityType USER_ENTITY_TYPE = EntityType.USER;
    public static final String CREATE_USER_ERR_MSG = "can't create user with login ";
    public static final String ROLLBACK_CREATE_ERR_MSG = "can't rollback a transaction while creating user with login ";
    public static final String FIND_USER_ERR_MSG = "can't find user ";
    private static final Logger LOGGER = LogManager.getLogger(
            SQLUserService.class);
    UserDao userDao = null;
    UserValidatorImpl userValidator = null;

    public SQLUserService(Transaction transaction) throws InternalServerException {
        LOGGER.debug("transaction is null:" + (transaction == null));
        this.transaction=transaction;
        userDao = transaction.createDao(USER_ENTITY_TYPE);
        userValidator = ValidatorFactory.createValidator(USER_ENTITY_TYPE);
    }

    @Override
    public User get(int id) throws ServiceException {
        try {
            return userDao.get(id);
        } catch (PersistenceException e) {
            LOGGER.error("Can't read user with id " + id + " from dao layer: \n" + e.getMessage()); //todo
        }
        return null;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (PersistenceException e) {
            LOGGER.error("Can't read users from dao layer: \n" + e.getMessage());//todo
        }
        return null;
    }

    @Override
    public int save(User user) throws ServiceException {
        try {
            return userDao.add(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't add user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
        return 0;
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't update user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (PersistenceException e) {
            LOGGER.error("Can't delete user with id " + id
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public void updatePassword(User user, String newPassword) throws ServiceException {
        try {
            UserPasswordService userPasswordService = new UserPasswordService();
            String salt = userPasswordService.generateSalt();
            user.setSalt(salt);
            user.setPasswordHash(userPasswordService
                    .generateSecurePassword(newPassword, salt));
            userDao.update(user);
        } catch (PersistenceException e) {
            LOGGER.error("Can't update password of user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());//todo
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ServiceException {
        UserPasswordService userPasswordService = new UserPasswordService();
        User user = null;
        try {
            user = userDao.getByEmail(email);
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


    public User create(String name, String surname, String email, String password) throws ServiceException {
        try {
            if (!userValidator.isNameValid(name)) {
                throw new IncorrectDataException(INVALID_NAME_FORM);
            }
            if (!userValidator.isSurnameValid(surname)) {
                throw new IncorrectDataException(INVALID_SURNAME_FORM);
            }
            if (!userValidator.isEmailValid(email)) {
                throw new IncorrectDataException(INVALID_EMAIL_FORM);
            }
            if (!userValidator.isPasswordValid(password)) {
                throw new IncorrectDataException(INVALID_PASSWORD_FORM);
            }

            if (userDao.getByEmail(email) != null) {
                throw new IncorrectDataException(EMAIL_ALREADY_EXISTS);
            }

            User user = new User();
            user.setName(name);
            user.setSurname(surname);
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
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + email , e);
            }
            throw new ServiceException(CREATE_USER_ERR_MSG + email, e);
        }
    }

    private User clearPassword(User user) {
        user.setPasswordHash(null);
        user.setSalt(null);
        return user;
    }
}
