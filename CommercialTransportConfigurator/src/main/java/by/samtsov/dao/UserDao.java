package by.samtsov.dao;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.exceptions.PersistenceException;

public interface UserDao extends Dao<User> {
    User getByLogin(String login) throws PersistenceException;

    User getByEmail(String email)throws PersistenceException;
}
