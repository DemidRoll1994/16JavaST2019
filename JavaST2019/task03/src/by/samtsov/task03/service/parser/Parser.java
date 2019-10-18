package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parser {

    String currentMatcherRegex;
    Parser nextParser;
    Composite currentComposite;


    // todo слишком универсальный.  List<Composite> childsInComposites не
    //  правильно вроде.
    //  как он склеит композиты, если уберутся какие-то промежуточные элементы?

    public Composite parseComposite(String initialString) {
        Pattern pattern = Pattern.compile(currentMatcherRegex);
        Matcher matcher = pattern.matcher(initialString);
        while (matcher.find()) {
            currentComposite.addChild(nextParser
                    .parseComposite(matcher.group()));
        }
        /*for (String child : childsInStrings) {
            childsInComposites.add(nextParser.parseComposite(child));
        }
        currentComposite.setChilds(childsInComposites);*/
        return currentComposite;
    }

}
