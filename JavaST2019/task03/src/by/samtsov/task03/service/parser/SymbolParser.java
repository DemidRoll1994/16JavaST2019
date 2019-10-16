package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Composite;
import by.samtsov.task03.entity.Symbol;

public class SymbolParser extends Parser {

    private static SymbolParser instance = new SymbolParser();

    private SymbolParser() {
    }

    public static SymbolParser getInstance(String newRegex) {
        regex = newRegex;
        return instance;
    }

    public static SymbolParser getInstance() {
        return instance;
    }

    public Composite parseComposite(String initialString) {
        return new Symbol(initialString);
    }

}
