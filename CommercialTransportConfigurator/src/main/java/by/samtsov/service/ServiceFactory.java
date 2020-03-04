package by.samtsov.service;

import by.samtsov.bean.enums.EntityType;
import by.samtsov.bean.exceptions.PersistentException;

public interface ServiceFactory {
    public <Type extends Service> Type createService(EntityType entityType) throws PersistentException;

    void close();
}
