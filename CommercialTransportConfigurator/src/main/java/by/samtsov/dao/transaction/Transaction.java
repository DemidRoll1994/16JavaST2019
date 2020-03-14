package by.samtsov.dao.transaction;

import by.samtsov.bean.type.EntityType;
import by.samtsov.service.InternalServerException;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.Dao;

public interface Transaction  {
    <Type extends Dao<?>> Type createDao(EntityType entityType) throws InternalServerException;

    void commit() throws PersistenceException;

    void rollback() throws PersistenceException;
}
