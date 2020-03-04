package java.by.samtsov.task03.service.factory;

import java.by.samtsov.task03.beans.entity.Composite;
import java.by.samtsov.task03.beans.enums.ComponentType;

public class CompositeFactory implements ComponentFactory {
    /**
     * method creating Component instance with selected ComponentType.
     *
     * @param componentType type of creating Component;
     * @return created Component;
     */
    @Override
    public Composite createComponent(final ComponentType componentType) {
        return new Composite(componentType);
    }
}
