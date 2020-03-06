package by.samtsov.dao.transaction;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;
import by.samtsov.bean.exceptions.PersistenceException;
import by.samtsov.dao.Dao;

public interface Transaction  {
    <Type extends Dao<?>> Type createDao(EntityType entityType) throws InternalServerException;

    void commit() throws PersistenceException;

    void rollback() throws PersistenceException;
}
