package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Sentence;

public class SentenceParser extends Parser {

    static final String defaultRegex = ".+";

    public SentenceParser(Parser nextParser, String matcherRegex) {
        currentComposite = new Sentence();
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
    }

    public SentenceParser(Parser nextParser) {
        currentComposite = new Sentence();
        this.nextParser = nextParser;
        currentMatcherRegex = ".+?[\\.!?]+";
    }

}
