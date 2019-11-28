package by.samtsov.dao;

import by.samtsov.bean.User;
import by.samtsov.bean.exceptions.PersistentException;

public interface UserDao extends Dao<User> {
    User getByLogin(String login) throws PersistentException;
}
