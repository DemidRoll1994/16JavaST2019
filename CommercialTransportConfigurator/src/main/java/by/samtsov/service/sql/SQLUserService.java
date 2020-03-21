package by.samtsov.service.sql;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.EntityType;
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
    public static final String CREATE_USER_ERR_MSG = "can't create user with login ";
    public static final String ROLLBACK_CREATE_ERR_MSG = "can't rollback a transaction while creating user with login ";
    public static final String FIND_USER_ERR_MSG = "can't find user ";
    private static final Logger logger = LogManager.getLogger(
            SQLUserService.class);
    public static final String CAN_T_FIND_MSG = "can't find user";
    public static final String CAN_T_DELETE_ERROR_MSG = "Can't delete user with id ";
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
            return clearPassword(userDao.get(id));
        } catch (PersistenceException e) {
            throw new ServiceException(e); /// todo does commit is обязателен?
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
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG, e); // TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG, e); // TODO THIS IS NOT delete
        }
    }

    @Override
    public int save(User user) throws ServiceException {
        try {
            int id = userDao.add(user);
            transaction.commit();
            return id;
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + user.getId(), e); // TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + user.getId(), e);// TODO THIS IS NOT delete
        }
    }

    @Override
    public User update(User user) throws ServiceException, InternalServerException {
        throw new UnsupportedOperationException();//todo чё за лажа
    }

    @Override
    public User updatePersonalData(User newUser) throws ServiceException {
        try {
            logger.trace("trying to edit personal user data: name: {}, " +
                            "surname: {}, email: {}, companyName: {}, " +
                            "phoneNumber: {}, address: {}", newUser.getName()
                    , newUser.getSurname(), newUser.getEmail()
                    , newUser.getCompanyName(), newUser.getPhoneNumber()
                    , newUser.getAddress());
            if (!userValidator.isNameValid(newUser.getName())) {
                logger.debug("Name {} is invalid", newUser.getName());
                throw new IncorrectDataException(INVALID_NAME_FORM);
            }
            if (!userValidator.isSurnameValid(newUser.getSurname())) {
                logger.debug("Surname {} is invalid", newUser.getSurname());
                throw new IncorrectDataException(INVALID_SURNAME_FORM);
            }
            if (!userValidator.isEmailValid(newUser.getEmail())) {
                logger.debug("Email {} is invalid", newUser.getEmail());
                throw new IncorrectDataException(INVALID_EMAIL_FORM);
            }
            if (!userValidator.isPhoneNumberValid(newUser.getPhoneNumber())) {
                logger.debug("Phone {} is invalid", newUser.getPhoneNumber());
                throw new IncorrectDataException(INVALID_PHONE_FORM);
            }
            User updatingUser = userDao.get(newUser.getId());
            if (updatingUser == null) {
                logger.debug("User with {} not found", newUser.getId());
                throw new IncorrectDataException(INVALID_USER_ID);
            } else {
                logger.trace("trying to edit user personal data[ name: {}, " +
                                "surname: {}, email: {}, companyName: {}, " +
                                "phoneNumber: {}, address: {} , role: {}, " +
                                "Status {}]", updatingUser.getName(), updatingUser.getSurname()
                        , updatingUser.getEmail(), updatingUser.getCompanyName()
                        , updatingUser.getPhoneNumber(), updatingUser.getAddress()
                        , updatingUser.getRole(), updatingUser.getStatus());
                updatingUser.setName(newUser.getName());
                updatingUser.setSurname(newUser.getSurname());
                updatingUser.setEmail(newUser.getEmail());
                updatingUser.setPhoneNumber(newUser.getPhoneNumber());
                userDao.update(updatingUser);
                transaction.commit();
                return clearPassword(newUser);
            }
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + newUser.getId(), e); // TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + newUser.getId(), e);// TODO THIS IS NOT delete
        }
    }

    @Override
    public User updateUserData(User newUser) throws ServiceException {
        try {
            logger.trace("trying to edit personal user data: name: {}, " +
                            "surname: {}, email: {}, companyName: {}, " +
                            "phoneNumber: {}, address: {}", newUser.getName()
                    , newUser.getSurname(), newUser.getEmail(), newUser.getCompanyName()
                    , newUser.getPhoneNumber(), newUser.getAddress());
            if (!userValidator.isNameValid(newUser.getName())) {
                logger.debug("Name {} is invalid", newUser.getName());
                throw new IncorrectDataException(INVALID_NAME_FORM);
            }
            if (!userValidator.isSurnameValid(newUser.getSurname())) {
                logger.debug("Surname {} is invalid", newUser.getSurname());
                throw new IncorrectDataException(INVALID_SURNAME_FORM);
            }
            if (!userValidator.isEmailValid(newUser.getEmail())) {
                logger.debug("Email {} is invalid", newUser.getEmail());
                throw new IncorrectDataException(INVALID_EMAIL_FORM);
            }
            if (!userValidator.isPhoneNumberValid(newUser.getPhoneNumber())) {
                logger.debug("Phone {} is invalid", newUser.getPhoneNumber());
                throw new IncorrectDataException(INVALID_PHONE_FORM);
            }
            if (!userValidator.isRoleValid(newUser.getRole())) {
                logger.debug("Role {} is invalid", newUser.getRole());
                throw new IncorrectDataException(INVALID_ROLE);
            }
            if (!userValidator.isStatusValid(newUser.getStatus())) {
                logger.debug("Status {} is invalid", newUser.getStatus());
                throw new IncorrectDataException(INVALID_STATUS);
            }
            User user = userDao.get(newUser.getId());
            if (user == null) {
                logger.debug("User with {} not found", newUser.getId());
                throw new IncorrectDataException(INVALID_USER_ID);
            } else {
                user.setName(newUser.getName());
                user.setSurname(newUser.getSurname());
                user.setEmail(newUser.getEmail());
                user.setPhoneNumber(newUser.getPhoneNumber());
                user.setRole(newUser.getRole());
                user.setStatus(newUser.getStatus());
                userDao.update(newUser);
                transaction.commit();
                return clearPassword(newUser);
            }
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + newUser.getId(), e); // TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + newUser.getId(), e);// TODO THIS IS NOT delete
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            userDao.delete(id);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + id, e); // TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_DELETE_ERROR_MSG + id, e);
        }
    }

    @Override
    public void updatePassword(User newUser, String oldPassword, String newPassword) throws ServiceException {
        try {
            if (!userValidator.isPasswordValid(newPassword)) {
                logger.debug("Old password is invalid");
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
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + newUser.getId(), e);// TODO THIS IS NOT CREATE
            }
            throw new ServiceException(CAN_T_FIND_MSG + newUser.getId(), e);
        }
    }


    @Override
    public User findByEmailAndPassword(String email, String password) throws ServiceException {
        UserPasswordService userPasswordService = new UserPasswordService();
        User user;
        try {
            user = userDao.getByEmail(email);
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + email, e);
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


    public User create(String name, String surname, String email, String password) throws ServiceException {
        try {
            if (!userValidator.isNameValid(name)) {
                logger.debug(" name " + name + " is invalid");
                throw new IncorrectDataException(INVALID_NAME_FORM);
            }
            if (!userValidator.isSurnameValid(surname)) {
                logger.debug(" surname " + surname + " is invalid");
                throw new IncorrectDataException(INVALID_SURNAME_FORM);
            }
            if (!userValidator.isEmailValid(email)) {
                logger.debug(" email " + email + " is invalid");
                throw new IncorrectDataException(INVALID_EMAIL_FORM);
            }
            if (!userValidator.isPasswordValid(password)) {
                logger.debug(" password " + password + " is invalid");
                throw new IncorrectDataException(INVALID_PASSWORD_FORM);
            }

            if (userDao.getByEmail(email) != null) {
                logger.debug(" email " + email + " is exist");
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
            user = get(userId);
            return clearPassword(user);
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (PersistenceException ex) {
                throw new ServiceException(ROLLBACK_CREATE_ERR_MSG + email, e);
            }
            throw new ServiceException(CREATE_USER_ERR_MSG + email, e);
        }
    }

    private User clearPassword(User user) {
        user.setPasswordHash(null);
        user.setSalt(null);
        return user;
    }


    @Override
    public User prepareToWriteInSession(User user) {
        user.setAddress(null);
        user.setPhoneNumber(0);
        user.setCompanyName(null);
        user.setSalt(null);
        user.setPasswordHash(null);
        user.setStatus(null);
        user.setCompanyName(null);
        user.setCompanyName(null);
        return user;
    }

}
