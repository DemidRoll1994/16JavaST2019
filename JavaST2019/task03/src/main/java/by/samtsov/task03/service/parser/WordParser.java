package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Word;

public class WordParser extends Parser {

    private static WordParser instance = new WordParser();

    private WordParser() {
        nextParser = SymbolParser.getInstance();
        currentComposite= new Word();
    }

    public static WordParser getInstance(String newRegex) {
        regex = newRegex;
        return instance;
    }

    public static WordParser getInstance() {
        return instance;
    }

}
