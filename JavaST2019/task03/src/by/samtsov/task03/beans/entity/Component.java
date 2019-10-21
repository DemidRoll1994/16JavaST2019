package by.samtsov.task03.beans.entity;

import by.samtsov.task03.beans.enums.ComponentType;

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

}
