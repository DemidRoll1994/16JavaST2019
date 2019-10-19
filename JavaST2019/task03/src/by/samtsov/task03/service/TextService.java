package by.samtsov.task03.service;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.combiner.CompositeCombiner;
import by.samtsov.task03.service.combiner.LeafCombiner;
import by.samtsov.task03.service.parser.CompositeParser;
import by.samtsov.task03.service.parser.LeafParser;

public class TextService {

    final String symbolPrefix = "";
    final String symbolSuffix = "";

    final String wordRegex = ".";
    final String wordPrefix = "";
    final String wordSuffix = "";

    final String lexemeRegex = "[?,.!…:;]+|[^\\s?,.!…:;]+";
    final String lexemePrefix = "";
    final String lexemeSuffix = " ";

    final String sentenceRegex = "[A-Z].+[\\.!?]+";
    final String sentencePrefix = "";
    final String sentenceSuffix = "";

    final String paragraphRegex = ".+";
    final String paragraphPrefix = "    ";
    final String paragraphSuffix = "\n\r";

    public Component parseTextFromComponent(String textString) {

        LeafParser symbolParser = new LeafParser();
        CompositeParser wordParser = new CompositeParser
                (ComponentType.WORD, symbolParser, wordRegex);
        CompositeParser lexemeParser = new CompositeParser
                (ComponentType.LEXEME, wordParser, lexemeRegex);
        CompositeParser sentenceParser = new CompositeParser
                (ComponentType.SENTENCE, lexemeParser, sentenceRegex);
        CompositeParser paragraphParser = new CompositeParser
                (ComponentType.PARAGRAPH, sentenceParser, paragraphRegex);
        return paragraphParser.parseComponent(textString);
    }

    public String parseComponentFromText(Component component) {
        LeafCombiner symbolCombiner = new LeafCombiner
                (symbolPrefix, symbolSuffix);
        CompositeCombiner wordCombiner = new CompositeCombiner
                (symbolCombiner, wordPrefix, wordSuffix);
        CompositeCombiner lexemeCombiner = new CompositeCombiner
                (wordCombiner, lexemePrefix, lexemeSuffix);
        CompositeCombiner sentenceCombiner = new CompositeCombiner
                (lexemeCombiner, sentencePrefix, sentenceSuffix);
        CompositeCombiner paragraphCombiner = new CompositeCombiner
                (sentenceCombiner, paragraphPrefix, paragraphSuffix);

        return (paragraphCombiner.combine(component));
    }

}
