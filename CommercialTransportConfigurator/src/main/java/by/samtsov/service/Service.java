package by.samtsov.service;

import java.util.List;

public interface Service<T> {
    T get(int id) throws ServiceException, InternalServerException;

    List<T> getAll() throws ServiceException, InternalServerException;

    int save(T t) throws ServiceException, InternalServerException;

    void update(T t) throws ServiceException, InternalServerException;

    void delete(int id) throws ServiceException, InternalServerException;
}
