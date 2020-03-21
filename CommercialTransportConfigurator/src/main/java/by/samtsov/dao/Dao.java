package by.samtsov.dao;


import java.util.List;

public interface Dao<T> {

    T get(int id) throws PersistenceException;

    List<T> getAll() throws PersistenceException;

    int add(T t) throws PersistenceException;

    void update(T t) throws PersistenceException;

    void delete(int id) throws PersistenceException;
}
