package by.samtsov.service;

import by.samtsov.dao.PersistenceException;

import java.util.List;

public interface Service<T> {
    /**
     * Return instance by its id.
     * @param id - identity od instance
     * @return instance type T
     * @throws ServiceException if an exception occurs at the service level
     * @throws InternalServerException if an exception occurs at another
     * level like a dao factory
     */
    T get(int id) throws ServiceException, InternalServerException;

    /**
     * Method returns all type T entities list.
     * @return all type T entities list
     * @throws ServiceException if an exception occurs at the service level
     * @throws InternalServerException if an exception occurs at another
     * level like a dao factory
     */
    List<T> getAll() throws ServiceException, InternalServerException;

    /**
     * Creates new instance in persistence.
     * @param t instance to create
     * @return identity of created instance
     * @throws ServiceException if an exception occurs at the service level
     * @throws InternalServerException if an exception occurs at another
     * level like a dao factory
     */
    int create(T t) throws ServiceException, InternalServerException;

    /**
     * Update instance in persistence.
     * @param t instance to update.
     * @throws ServiceException if an exception occurs at the service level
     * @throws InternalServerException if an exception occurs at another
     * level like a dao factory
     */
    void update(T t) throws ServiceException, InternalServerException;

    /**
     * Delete instance from persistence.
     * @param id - instance identity
     * @throws ServiceException if an exception occurs at the service level
     * @throws InternalServerException if an exception occurs at another
     * level like a dao factory
     */
    void delete(int id) throws ServiceException, InternalServerException;
}
