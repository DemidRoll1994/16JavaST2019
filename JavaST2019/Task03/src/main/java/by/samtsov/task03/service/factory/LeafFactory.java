package java.by.samtsov.task03.service.factory;

import java.by.samtsov.task03.beans.entity.Leaf;
import java.by.samtsov.task03.beans.enums.ComponentType;

public class LeafFactory implements ComponentFactory {

    /**
     * method creating Component instance with selected ComponentType.
     *
     * @param componentType type of creating Component;
     * @return created Component;
     */
    @Override
    public Leaf createComponent(final ComponentType componentType) {
        return new Leaf(componentType);
    }
}
