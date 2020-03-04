package by.samtsov.service.mysql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.UserDao;
import by.samtsov.service.UserPasswordService;
import by.samtsov.service.UserService;
import by.samtsov.service.validator.UserValidator;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MysqlUserService extends MysqlService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(
            MysqlUserService.class);
    public static final EntityType USER_ENTITY_TYPE = EntityType.USER;

    @Override
    public User get(int id) {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.get(id);
        } catch (PersistentException e) {
            LOGGER.error("Can't read user with id " + id + " from dao layer: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.getAll();
        } catch (PersistentException e) {
            LOGGER.error("Can't read users from dao layer: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public int save(User user) {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            return userDao.add(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't add user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
        return 0;
    }

    @Override
    public void update(User user) {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.update(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't update user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.delete(id);
        } catch (PersistentException e) {
            LOGGER.error("Can't delete user with id " + id
                    + " from dao layer: \n" + e.getMessage());
        }
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        try {
            UserPasswordService userPasswordService = new UserPasswordService();
            String salt = userPasswordService.generateSalt();
            user.setSalt(salt);
            user.setPasswordHash(userPasswordService
                    .generateSecurePassword(newPassword, salt));
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            userDao.update(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't update password of user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws IncorrectDataException, PersistentException {
//        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            User user = userDao.getByLogin(login);
            UserPasswordService userPasswordService = new UserPasswordService();
            if (user != null && userPasswordService.verifyUserPassword(password,
                    user.getPasswordHash(), user.getSalt())) {
                return user;
            } else {
                throw new IncorrectDataException("Incorrect username or password");
            }
//        } catch (PersistentException e) {
//            LOGGER.error("Can't read user with login " + login
//                    + " from dao layer: \n" + e.getMessage());
//        }
    }


    public int create(User user) {
        try {
            UserDao userDao = transaction.createDao(USER_ENTITY_TYPE);
            UserValidator userValidator = ValidatorFactory.createValidator(USER_ENTITY_TYPE);
            try {
                if (!userValidator.isValidLogin(login)) {
                    throw new ServiceException(INVALID_LOGIN);
                }
                if (!userValidator.isValidEmail(email)) {
                    throw new ServiceException(INVALID_EMAIL);
                }
                if (!userValidator.isValidPassword(pass)) {
                    throw new ServiceException(INVALID_PASS);
                }

                UserDao dao = transaction.getUserDao();

                if (dao.findUserByLogin(login).isPresent()) {
                    throw new ServiceException(USER_EXISTS);
                }
                if (dao.findUserByEmail(email).isPresent()) {
                    throw new ServiceException(EMAIL_EXISTS);
                }

                User user = new User();
                user.setLogin(login);
                user.setEmail(email);

                String salt = getSalt();
                String newSecurePass = generateSecurePassword(pass, salt);

                user.setPassword(newSecurePass);
                user.setSalt(salt);
                user.setRole(Role.USER);
                user.setStatus(Status.ACTIVE);
                user.setRegistrationDate(LocalDate.now());

                transaction.commit();
                return clearPassword(dao.create(user));
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e, SQL_ERROR);
            }
        }
            return userDao.add(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't add user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
        return 0;
    }


}
