package by.samtsov.service;

import by.samtsov.bean.exceptions.IncorrectDataException;

import java.util.List;

public interface Service<T> {
    T get(int id);

    List<T> getAll();

    int save(T t) throws IncorrectDataException;

    void update(T t);

    void delete(int id);
}
