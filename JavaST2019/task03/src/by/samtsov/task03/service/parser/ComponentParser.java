package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.factory.ComponentFactory;

public abstract class ComponentParser {
    /**
     * Regular expression for extract parsing values from string.
     */
    String currentMatcherRegex;

    /**
     * next parser in "chain of responsibility" pattern.
     */
    ComponentParser nextParser;
    /**
     * factory method pattern entity for creating new child components.
     */
    ComponentFactory compositeFactory;
    /**
     * next entity ComponentType. used for creating entities with
     * compositeFactory
     */
    ComponentType componentType;

    /**
     * parse initial string for component with subcomponents.
     * @param initialString
     * @return
     */
    public abstract Component parseComponent(String initialString);

}
