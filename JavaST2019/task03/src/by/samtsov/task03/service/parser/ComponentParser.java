package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.factory.CompositeFactory;

public abstract class ComponentParser {

    String currentMatcherRegex;
    ComponentParser nextParser;
    CompositeFactory compositeFactory;
    ComponentType componentType;

    public abstract Component parseComponent(String initialString) ;

}
