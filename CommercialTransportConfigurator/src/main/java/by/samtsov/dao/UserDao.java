package by.samtsov.dao;

import by.samtsov.bean.entity.User;

public interface UserDao extends Dao<User> {
    User findByEmail(String email)throws PersistenceException;
}
