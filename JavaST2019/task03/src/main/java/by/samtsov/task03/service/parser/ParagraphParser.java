package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Paragraph;

public class ParagraphParser extends Parser {

    private static ParagraphParser instance = new ParagraphParser();

    private ParagraphParser() {
        nextParser = SentenceParser.getInstance();
        currentComposite= new Paragraph();
    }

    public static ParagraphParser getInstance(String newRegex) {
        regex = newRegex;
        return instance;
    }

    public static ParagraphParser getInstance() {
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
