package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Paragraph;

public class ParagraphParser extends Parser {

    static final String defaultRegex = ".+";

    public ParagraphParser(Parser nextParser, String matcherRegex) {
        currentComposite = new Paragraph();
        this.nextParser = nextParser;
        currentMatcherRegex = matcherRegex;
    }

    public ParagraphParser(Parser nextParser) {
        currentComposite = new Paragraph();
        this.nextParser = nextParser;
        currentMatcherRegex = ".+";
    }

}
