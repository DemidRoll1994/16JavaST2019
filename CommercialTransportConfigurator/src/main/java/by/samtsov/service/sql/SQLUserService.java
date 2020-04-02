package by.samtsov.service.sql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.type.InternalServerError;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.UserDao;
import by.samtsov.dao.transaction.Transaction;
import by.samtsov.service.*;
import by.samtsov.service.validator.UserValidatorImpl;
import by.samtsov.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.samtsov.bean.type.InternalServerError.*;

public class SQLUserService extends SQLService implements UserService {

    public static final EntityType USER_ENTITY_TYPE = EntityType.USER;

    private static final String ROLLBACK_GET_ERR_MSG
            = "can't rollback a transaction while try to get user with login";
    private static final String CAN_T_GET_ERROR_MSG
            = "can't get user with login";
    private static final String ROLLBACK_GET_ALL_ERR_MSG
            = "can't rollback a transaction while try to get all users";
    private static final String CAN_T_GET_ALL_ERROR_MSG
            = "can't get all users";
    private static final String ROLLBACK_SAVE_ERR_MSG
            = "can't rollback a transaction while try to save user with id";
    private static final String CAN_T_SAVE_ERROR_MSG
            = "can't save user with id";
    private static final String ROLLBACK_UPDATE_ERR_MSG
            = "can't rollback a transaction while try to update user with id";
    private static final String CAN_T_UPDATE_ERROR_MSG
            = "can't update user with id";
    private static final String CAN_T_CREATE_ERR_MSG
            = "can't create user with login";
    private static final String ROLLBACK_CREATE_ERR_MSG
            = "can't rollback a transaction while try to delete user with id";
    private static final String CAN_T_DELETE_ERROR_MSG
            = "can't delete user with id";
    private static final String CAN_T_FIND_MSG = "can't find user with id";
    private static final String ROLLBACK_FIND_ERR_MSG
            = "can't rollback a transaction while try to find user with id";
    private static final String UPDATE_PASSWORD_ERR_MSG
            = "can't update password for user with login";
    private static final String ROLLBACK_PASS_UPD_ERR_MSG = "can't rollback a" +
            " transaction while try to update password for user with login";
    private static final Logger logger = LogManager.getLogger(
            SQLUserService.class);
    UserDao userDao = null;
    UserValidatorImpl userValidator = null;

    public SQLUserService(Transaction transaction) throws InternalServerException {
        logger.debug("transaction is null:" + (transaction == null));
        this.transaction = transaction;
        userDao = transaction.createDao(USER_ENTITY_TYPE);
        userValidator = ValidatorFactory.createValidator(USER_ENTITY_TYPE);
    }

    @Override
    public User get(int id) throws ServiceException {
        try {
            User user = userDao.get(id);

            transaction.commit();
            return clearPassword(user);
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_GET_ERR_MSG, e);
            }
            throw new ServiceException(CAN_T_GET_ERROR_MSG, e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            List<User> users = userDao.getAll();
            transaction.commit();
            for (User user : users) {
                clearPassword(user);
            }
            return users;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_GET_ALL_ERR_MSG, e);
            }
            throw new ServiceException(CAN_T_GET_ALL_ERROR_MSG, e);
        }
    }

    @Override
    public int save(User user) throws ServiceException {
        try {
            if (user == null) {
                return -1;
            }
            int id = userDao.add(user);
            transaction.commit();
            return id;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_SAVE_ERR_MSG + user.getId(), e);
            }
            throw new ServiceException(CAN_T_SAVE_ERROR_MSG + user.getId(), e);
        }
    }

    @Override
    public User update(User user) throws ServiceException, InternalServerException {
        throw new UnsupportedOperationException();//todo чё за лажа
    }

    @Override
    public User updatePersonalData(User newUser) throws ServiceException {
        try {

            if (newUser == null) {
                throw new ServiceException("user is null, can't update");
            }
            logger.trace("trying to edit personal user data by user: name: " +
                            "{}, surname: {}, email: {}, companyName: {}, " +
                            "phoneNumber: {}, address: {}", newUser.getName()
                    , newUser.getSurname(), newUser.getEmail()
                    , newUser.getCompanyName(), newUser.getPhoneNumber()
                    , newUser.getAddress());
            User oldUser = userDao.get(newUser.getId());

            if (oldUser == null) {
                logger.debug("User with {} not found", newUser.getId());
                throw new IncorrectDataException(INVALID_USER_ID);
            }

            InternalServerError errorCode = generateErrorCode(oldUser,
                    newUser.getName(),
                    newUser.getSurname(), newUser.getEmail(),
                    newUser.getPhoneNumber());
            if (errorCode != null) {
                throw new IncorrectDataException(errorCode);
            }

            logger.trace("trying to edit user personal data[ name: {}, " +
                            "surname: {}, email: {}, companyName: {}, " +
                            "phoneNumber: {}, address: {} , role: {}, " +
                            "Status {}]", oldUser.getName()
                    , oldUser.getSurname(), oldUser.getEmail()
                    , oldUser.getCompanyName(), oldUser.getPhoneNumber()
                    , oldUser.getAddress(), oldUser.getRole()
                    , oldUser.getStatus());
            oldUser.setName(newUser.getName());
            oldUser.setSurname(newUser.getSurname());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPhoneNumber(newUser.getPhoneNumber());
            oldUser.setCompanyName(newUser.getCompanyName());
            oldUser.setAddress(newUser.getAddress());
            userDao.update(oldUser);
            transaction.commit();
            return clearPassword(oldUser);

        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_UPDATE_ERR_MSG + newUser.getId(), e);
            }
            throw new ServiceException(CAN_T_UPDATE_ERROR_MSG + newUser.getId(), e);
        }
    }

    @Override
    public User updateUserData(User newUser) throws ServiceException {
        try {
            if (newUser == null) {
                throw new ServiceException("user is null, can't update");
            }
            logger.trace("trying to edit personal user data by admin: name: " +
                            "{}, surname: {}, email: {}, companyName: {}, " +
                            "phoneNumber: {}, address: {}", newUser.getName()
                    , newUser.getSurname(), newUser.getEmail()
                    , newUser.getCompanyName(), newUser.getPhoneNumber()
                    , newUser.getAddress());
            User oldUser = userDao.get(newUser.getId());

            if (oldUser == null) {
                logger.debug("User with {} not found", newUser.getId());
                throw new IncorrectDataException(INVALID_USER_ID);
            }
            InternalServerError errorCode = generateErrorCode(oldUser
                    , newUser.getName(), newUser.getSurname()
                    , newUser.getEmail(), newUser.getPhoneNumber()
                    , newUser.getRole(), newUser.getStatus());
            if (errorCode != null) {
                throw new IncorrectDataException(errorCode);
            }
            oldUser.setName(newUser.getName());
            oldUser.setSurname(newUser.getSurname());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPhoneNumber(newUser.getPhoneNumber());
            oldUser.setRole(newUser.getRole());
            oldUser.setStatus(newUser.getStatus());
            oldUser.setCompanyName(newUser.getCompanyName());
            oldUser.setAddress(newUser.getAddress());
            userDao.update(oldUser);
            transaction.commit();
            return clearPassword(oldUser);

        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_UPDATE_ERR_MSG + newUser.getId(), e);
            }
            throw new ServiceException(CAN_T_UPDATE_ERROR_MSG + newUser.getId(), e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        throw new UnsupportedOperationException();
    }


    public void blockUser(int id) throws ServiceException {
        try {
            User user = userDao.get(id);
            user.setStatus(UserStatus.BLOCKED);
            userDao.update(user);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + id, e);
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + id, e);
        }
    }

    public void activateUser(int id) throws ServiceException {
        try {
            User user = userDao.get(id);
            user.setStatus(UserStatus.ACTIVE);
            userDao.update(user);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + id, e);
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + id, e);
        }
    }


    @Override
    public void updatePassword(User newUser, String oldPassword, String
            newPassword) throws ServiceException {
        try {
            if (!userValidator.isPasswordValid(newPassword)) {
                logger.debug("New password is invalid");
                throw new IncorrectDataException(INVALID_PASSWORD_FORM);
            }
            UserPasswordService userPasswordService = new UserPasswordService();
            User oldUser = userDao.get(newUser.getId());
            if (userPasswordService.verifyUserPassword(oldPassword
                    , oldUser.getPasswordHash(), oldUser.getSalt())) {
                String newSalt = userPasswordService.generateSalt();
                newUser.setSalt(newSalt);
                newUser.setPasswordHash(userPasswordService
                        .generateSecurePassword(newPassword, newSalt));
                userDao.update(newUser);
                transaction.commit();
            } else {
                throw new IncorrectDataException(OLD_PASSWORD_INVALID);
            }
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_PASS_UPD_ERR_MSG
                        + newUser.getId(), e);
            }
            throw new ServiceException(UPDATE_PASSWORD_ERR_MSG
                    + newUser.getId(), e);
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws
            ServiceException {
        UserPasswordService userPasswordService = new UserPasswordService();
        User user;
        try {
            user = userDao.findByEmail(email);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_FIND_ERR_MSG + email, e);
            }
            throw new ServiceException(CAN_T_FIND_MSG + email, e);
        }
        if (user != null && userPasswordService.verifyUserPassword(password,
                user.getPasswordHash(), user.getSalt())) {
            return user;
        } else {
            throw new IncorrectDataException("Incorrect username or password");
        }
    }


    public User create(String name, String surname, String email
            , String password) throws ServiceException {
        try {
            InternalServerError errorCode = generateErrorCode(null, name,
                    surname, email);
            if (errorCode != null) {
                throw new IncorrectDataException(errorCode);
            }
            if (!userValidator.isPasswordValid(password)) {
                logger.debug("password is invalid");
                throw new IncorrectDataException(INVALID_PASSWORD_FORM);
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
            user = get(userId);
            return clearPassword(user);
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + email, e);
            }
            throw new ServiceException(CAN_T_CREATE_ERR_MSG + email, e);
        }
    }


    private InternalServerError generateErrorCode(User thisUser, String name
            , String surname, String email, long phone, Role role
            , UserStatus status) throws PersistenceException {
        InternalServerError errorCode = generateErrorCode(thisUser, name, surname, email
                , phone);
        if (errorCode != null)
            return errorCode;
        if (role == null) {
            logger.debug("Role {} is invalid", role);
            errorCode = INVALID_ROLE;
        }
        if (status == null) {
            logger.debug("Status {} is invalid", status);
            errorCode = INVALID_STATUS;
        }
        return errorCode;
    }

    private InternalServerError generateErrorCode(User thisUser, String name
            , String surname, String email, long phone)
            throws PersistenceException {
        InternalServerError errorCode = generateErrorCode(thisUser, name, surname, email);
        if (errorCode == null && !userValidator.isPhoneNumberValid(phone)) {
            logger.debug("Phone {} is invalid", phone);
            errorCode = INVALID_PHONE_FORM;
        }
        return errorCode;
    }

    private InternalServerError generateErrorCode(User thisUser, String name
            , String surname, String email) throws PersistenceException {
        if (!userValidator.isNameValid(name)) {
            logger.debug("Name {} is invalid", name);
            return INVALID_NAME_FORM;
        }
        if (!userValidator.isSurnameValid(surname)) {
            logger.debug("Surname {} is invalid", surname);
            return INVALID_SURNAME_FORM;
        }
        if (!userValidator.isEmailValid(email)) {
            logger.debug("Email {} is invalid", email);
            return INVALID_EMAIL_FORM;
        }
        User anotherUser = userDao.findByEmail(email);
        if (anotherUser != null && thisUser != null
                && anotherUser.getId() != thisUser.getId()) {
            logger.debug("Email {} is exist", email);
            return EMAIL_ALREADY_EXISTS;
        }
        return null;
    }

    private User clearPassword(User user) {
        user.setPasswordHash(null);
        user.setSalt(null);
        return user;
    }


    @Override
    public User prepareToWriteInSession(User user) {
        if (user == null) {
            return null;
        }
        user = clearPassword(user);
        user.setAddress(null);
        user.setPhoneNumber(0);
        user.setCompanyName(null);
        user.setStatus(null);
        return user;
    }

}
