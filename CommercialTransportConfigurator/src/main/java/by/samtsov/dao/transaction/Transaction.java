package by.samtsov.dao.transaction;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.Dao;

public interface Transaction  {
    <Type extends Dao<?>> Type createDao(EntityType entityType) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
