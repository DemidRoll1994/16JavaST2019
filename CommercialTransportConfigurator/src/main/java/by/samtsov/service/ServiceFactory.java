package by.samtsov.service;

import by.samtsov.bean.type.EntityType;

public interface ServiceFactory {
    public <Type extends Service> Type createService(EntityType entityType) throws InternalServerException;

    void close();
}
