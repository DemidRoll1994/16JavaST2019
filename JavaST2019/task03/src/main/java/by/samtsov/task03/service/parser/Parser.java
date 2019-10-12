package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Composite;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {

    static String regex;
    Parser nextParser;
    Composite currentComposite;


    public Composite parseComposite(String initialString) {
        String[] childsInStrings = initialString.split(regex);
        List<Composite> childsInComposites = new ArrayList<>();
        for (String child : childsInStrings) {
            childsInComposites.add(nextParser.parseComposite(child));
        }
        currentComposite.setChilds(childsInComposites);
        return currentComposite;
    }

}
