package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Sentence;

public class SentenceParser extends Parser {

    private static SentenceParser instance = new SentenceParser();

    private SentenceParser() {
        nextParser = LexemeParser.getInstance();
        currentComposite= new Sentence();
    }

    public static SentenceParser getInstance(String newRegex) {
        regex = newRegex;
        return instance;
    }

    public static SentenceParser getInstance() {
        return instance;
    }

    /*public Composite parseComposite(String initialString) {
        String[] childsInStrings = initialString.split(regex);
        List<Composite> childsInComposites = new ArrayList<>();
        SentenceParser sentenceParser = SentenceParser.getInstance();
        for (String child : childsInStrings) {
            childsInComposites.add(sentenceParser.parseComposite(child));
        }
        return new Paragraph(childsInComposites);
    }*/
}
