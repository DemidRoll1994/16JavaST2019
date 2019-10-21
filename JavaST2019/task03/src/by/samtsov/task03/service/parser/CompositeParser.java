package by.samtsov.task03.service.parser;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Composite;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.factory.CompositeFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeParser extends ComponentParser {

    public CompositeParser(ComponentType componentType
            , ComponentParser nextParser, String matcherRegex) {
        compositeFactory =new CompositeFactory();
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
        this.componentType = componentType;
    }

    public Component parseComponent(String initialString) {
        Pattern pattern = Pattern.compile(currentMatcherRegex);
        Matcher matcher = pattern.matcher(initialString);
        Composite composite =
                (Composite) compositeFactory.createComponent(componentType);
        while (matcher.find()) {
            composite.addChild(nextParser.parseComponent(matcher.group()));
        }
        return composite;
    }

}
