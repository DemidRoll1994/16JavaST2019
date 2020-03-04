package java.by.samtsov.task03.service.factory;

import java.by.samtsov.task03.beans.entity.Component;
import java.by.samtsov.task03.beans.enums.ComponentType;

public interface ComponentFactory {
    /**
     * method creating Component instance with selected ComponentType.
     *
     * @param componentType type of creating Component;
     * @return created Component;
     */
    Component createComponent(ComponentType componentType);
}
