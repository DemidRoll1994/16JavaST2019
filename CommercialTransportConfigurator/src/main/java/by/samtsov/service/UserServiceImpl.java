package by.samtsov.service;

import by.samtsov.bean.User;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.UserDao;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        //userDao =
    }

    @Override
    public User get(int id) {
        try {
            return userDao.get(id);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            return userDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }return null;
    }

    @Override
    public int add(User user) {
        try {
            return userDao.add(user);
        } catch (PersistentException e) {
            e.printStackTrace();
        }return 0;
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            userDao.delete(id);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
