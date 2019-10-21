package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Composite;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.factory.CompositeFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeParser extends ComponentParser {
    /**
     * full-param Constructor.
     *
     * @param newComponentType - next entity's ComponentType. used for creating
     *                         entities with compositeFactory
     * @param newNextParser    - next parser in chain of responsibilities.
     * @param newMatcherRegex  - Regular expression for extract parsing values
     *                         from string.
     */
    public CompositeParser(final ComponentType newComponentType, final
    ComponentParser newNextParser, final String newMatcherRegex) {
        setCompositeFactory(new CompositeFactory());
        setNextParser(newNextParser);
        setCurrentMatcherRegex(newMatcherRegex);
        setComponentType(newComponentType);
    }

    /**
     * parse initial string for component with subcomponents.
     *
     * @param initialString string contains initial value for parsing Component
     * @return parsed component
     */
    public Component parseComponent(final String initialString) {
        Pattern pattern = Pattern.compile(getCurrentMatcherRegex());
        Matcher matcher = pattern.matcher(initialString);
        Composite composite =
                (Composite) getCompositeFactory().createComponent(
                        getComponentType());
        while (matcher.find()) {
            composite.addChild(getNextParser().parseComponent(matcher.group()));
        }
        return composite;
    }

}
