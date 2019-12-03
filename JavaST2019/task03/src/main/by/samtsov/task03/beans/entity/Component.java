package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

import java.util.Objects;

public abstract class Component {
    /**
     * enum. defines type of component (Paragraph, sentence, lexeme,etc.).
     */
    private ComponentType componentType;

    /**
     * getter for componentType field.
     *
     * @return value of componentType field
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * setter for componentType field.
     *
     * @param newComponentType - new value to set in field
     */
    public void setComponentType(final ComponentType newComponentType) {
        componentType = newComponentType;
    }

    /**
     * Standard equals method for entity.
     *
     * @param o - another object to compare
     * @return true if object is equals or false is not equals
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Component)) {
            return false;
        }
        Component component = (Component) o;
        return componentType == component.componentType;
    }

    /**
     * Standard hashcode method for entity.
     *
     * @return hashcode of entity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(componentType);
    }

    /**
     * Standard toString method for entity.
     *
     * @return information about entity state
     */
    @Override
    public String toString() {
        return "Component{" + "componentType=" + componentType + '}';
    }
}
