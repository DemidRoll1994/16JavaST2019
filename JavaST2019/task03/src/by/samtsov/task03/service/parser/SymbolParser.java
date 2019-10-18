package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Composite;
import by.samtsov.task03.entity.Symbol;

public class SymbolParser extends Parser {

    static final String defaultRegex = ".+";

    public SymbolParser(Parser nextParser, String matcherRegex) {
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
    }

    public SymbolParser(Parser nextParser) {
        this.nextParser = nextParser;
        currentMatcherRegex = ".+";
    }
    
    @Override
    public Composite parseComposite(String initialString) {
        return new Symbol(initialString);
    }
}
