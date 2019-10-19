package by.samtsov.task03.service.factory;

import by.samtsov.task03.beans.entity.Composite;
import by.samtsov.task03.beans.enums.ComponentType;

public class CompositeFactory implements ComponentFactory {
    @Override
    public Composite createComponent(ComponentType compositeType) {
        return new Composite(compositeType);
    }
}
