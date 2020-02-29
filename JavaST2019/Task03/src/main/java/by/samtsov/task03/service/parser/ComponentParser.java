package java.by.samtsov.task03.service.parser;

import java.by.samtsov.task03.beans.entity.Component;
import java.by.samtsov.task03.beans.enums.ComponentType;
import java.by.samtsov.task03.service.factory.ComponentFactory;

public abstract class ComponentParser {
    /**
     * Regular expression for extract parsing values from string.
     */
    private String currentMatcherRegex;
    /**
     * next parser in "chain of responsibility" pattern.
     */
    private ComponentParser nextParser;
    /**
     * factory method pattern entity for creating new child components.
     */
    private ComponentFactory compositeFactory;
    /**
     * next entity's ComponentType. used for creating entities with
     * compositeFactory
     */
    private ComponentType componentType;

    /**
     * getter for currentMatcherRegex field.
     *
     * @return currentMatcherRegex value
     */
    public String getCurrentMatcherRegex() {
        return currentMatcherRegex;
    }

    /**
     * setter for currentMatcherRegex field.
     *
     * @param newCurrentMatcherRegex Regular expression for extract parsing
     *                               values from string
     */
    public void setCurrentMatcherRegex(final String newCurrentMatcherRegex) {
        currentMatcherRegex = newCurrentMatcherRegex;
    }

    /**
     * getter for nextParser field.
     *
     * @return nextParser that should parse next component
     */
    public ComponentParser getNextParser() {
        return nextParser;
    }

    /**
     * nextParser setter.
     *
     * @param newNextParser next parser in "chain of responsibility" pattern.
     */
    public void setNextParser(final ComponentParser newNextParser) {
        nextParser = newNextParser;
    }

    /**
     * getter for compositeFactory field.
     *
     * @return compositeFactory value
     */
    public ComponentFactory getCompositeFactory() {
        return compositeFactory;
    }

    /**
     * setter for compositeFactory field.
     *
     * @param newCompFactory factory method pattern entity for creating new
     *                       child components.
     */
    public void setCompositeFactory(final ComponentFactory newCompFactory) {
        compositeFactory = newCompFactory;
    }

    /**
     * getter for componentType field.
     *
     * @return type of component
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * set componentType parameter's new value.
     *
     * @param newComponentType next entity's ComponentType
     */
    public void setComponentType(final ComponentType newComponentType) {
        componentType = newComponentType;
    }

    /**
     * parse initial string for component with subcomponents.
     *
     * @param initialString string contains initial value for parsing Component
     * @return parsed component
     */
    public abstract Component parseComponent(String initialString);

}
