package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Word;

public class WordParser extends Parser {

    static final String defaultRegex = ".+";

    public WordParser(Parser nextParser, String matcherRegex) {
        currentComposite = new Word();
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
    }

    public WordParser(Parser nextParser) {
        currentComposite = new Word();
        this.nextParser = nextParser;
        currentMatcherRegex = ".+";
    }
}
