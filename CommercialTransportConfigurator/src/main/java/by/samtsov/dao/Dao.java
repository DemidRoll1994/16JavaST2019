package by.samtsov.dao;


import by.samtsov.bean.exceptions.PersistenceException;

import java.util.List;

public interface Dao<T> {

    T get(int id) throws PersistenceException;

    List<T> getAll() throws PersistenceException;

    int add(T t) throws PersistenceException;

    int update(T t) throws PersistenceException;

    int delete(int id) throws PersistenceException;
}
