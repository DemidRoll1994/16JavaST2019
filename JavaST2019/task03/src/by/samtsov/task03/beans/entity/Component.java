package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

public abstract class Component {

    ComponentType componentType;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType newComponentType) {
        componentType = newComponentType;
    }

}
