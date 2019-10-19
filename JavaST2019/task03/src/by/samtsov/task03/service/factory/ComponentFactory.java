package by.samtsov.task03.service.factory;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.enums.ComponentType;

public interface ComponentFactory {
    Component createComponent(ComponentType componentType);
}
