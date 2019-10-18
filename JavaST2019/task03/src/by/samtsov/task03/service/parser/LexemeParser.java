package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Lexeme;

public class LexemeParser extends Parser {

    static final String defaultRegex = ".+";

    public LexemeParser(Parser nextParser, String matcherRegex) {
        currentComposite = new Lexeme();
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
    }

    public LexemeParser(Parser nextParser) {
        currentComposite = new Lexeme();
        this.nextParser = nextParser;
        currentMatcherRegex = "\\S+";
    }

}
