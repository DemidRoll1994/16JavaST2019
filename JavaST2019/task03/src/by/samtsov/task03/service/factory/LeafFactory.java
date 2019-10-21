package by.samtsov.task03.service.factory;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Leaf;
import by.samtsov.task03.beans.enums.ComponentType;

public class LeafFactory implements ComponentFactory {

    @Override
    public Leaf createComponent(ComponentType componentType) {
        return new Leaf(componentType);
    }
}
