package by.samtsov.task03.service.parser;

import by.samtsov.task03.entity.Lexeme;

public class LexemeParser extends Parser {

    private static LexemeParser instance = new LexemeParser();

    private LexemeParser() {
        nextParser = WordParser.getInstance();
        currentComposite= new Lexeme();
    }

    public static LexemeParser getInstance(String newRegex) {
        regex = newRegex;
        return instance;
    }

    public static LexemeParser getInstance() {
        return instance;
    }

}
