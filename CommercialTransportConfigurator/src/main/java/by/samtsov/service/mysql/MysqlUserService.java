package by.samtsov.service.mysql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.IncorrectDataException;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.UserDao;
import by.samtsov.service.UserPasswordService;
import by.samtsov.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MysqlUserService extends MysqlService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(
            MysqlUserService.class);

    @Override
    public User get(int id) {
        try {
            UserDao userDao = transaction.createDao(EntityType.USER);
            return userDao.get(id);
        } catch (PersistentException e) {
            LOGGER.error("Can't read user with id " + id + " from dao layer: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            UserDao userDao = transaction.createDao(EntityType.USER);
            return userDao.getAll();
        } catch (PersistentException e) {
            LOGGER.error("Can't read users from dao layer: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public int save(User user) {
        try {
            UserDao userDao = transaction.createDao(EntityType.USER);
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
            UserDao userDao = transaction.createDao(EntityType.USER);
            userDao.update(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't update user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            UserDao userDao = transaction.createDao(EntityType.USER);
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
            UserDao userDao = transaction.createDao(EntityType.USER);
            userDao.update(user);
        } catch (PersistentException e) {
            LOGGER.error("Can't update password of user with id " + user.getId()
                    + " from dao layer: \n" + e.getMessage());
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws IncorrectDataException, PersistentException {
//        try {
            UserDao userDao = transaction.createDao(EntityType.USER);
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
}
