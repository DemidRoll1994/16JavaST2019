package by.samtsov.dao;

import by.samtsov.bean.exceptions.PersistentException;

import java.util.List;

public interface Dao<T> {

    T get(int id) throws PersistentException;

    List<T> getAll() throws PersistentException;

    int add(T t) throws PersistentException;

    void update(T t) throws PersistentException;

    void delete(int id) throws PersistentException;
}
