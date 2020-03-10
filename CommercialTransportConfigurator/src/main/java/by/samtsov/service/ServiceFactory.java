package by.samtsov.service;

import by.samtsov.bean.type.EntityType;
import by.samtsov.bean.exceptions.InternalServerException;

public interface ServiceFactory {
    public <Type extends Service> Type createService(EntityType entityType) throws InternalServerException;

    void close();
}
