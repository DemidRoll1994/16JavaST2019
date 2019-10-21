package by.samtsov.task03.service;

import by.samtsov.task03.beans.entity.Component;
import by.samtsov.task03.beans.entity.Composite;
import by.samtsov.task03.beans.entity.Leaf;
import by.samtsov.task03.beans.enums.ComponentType;
import by.samtsov.task03.service.combiner.CompositeCombiner;
import by.samtsov.task03.service.combiner.LeafCombiner;
import by.samtsov.task03.service.parser.CompositeParser;
import by.samtsov.task03.service.parser.LeafParser;

import java.util.Comparator;
import java.util.List;

public class TextService {

    private static final String SYMBOL_REGEX = ".";
    private static final String SYMBOL_PREFIX = "";
    private static final String SYMBOL_SUFFIX = "";

    private static final String WORD_REGEX = "[?,.!…:;]+|[^\\s?,.!…:;]+";
    private static final String WORD_PREFIX = "";
    private static final String WORD_SUFFIX = "";

    private static final String LEXEME_REGEX = "\\S+";
    private static final String LEXEME_PREFIX = "";
    private static final String LEXEME_SUFFIX = " ";

    private static final String SENTENCE_REGEX = "\\s*[A-Z].+?[\\.!?]+";
    private static final String SENTENCE_PREFIX = "";
    private static final String SENTENCE_SUFFIX = "";

    private static final String PARAGRAPH_REGEX = ".+";
    private static final String PARAGRAPH_PREFIX = "    ";
    private static final String PARAGRAPH_SUFFIX = "\n";

    public Component parseComponentFromText(String textString) {

        LeafParser leafParser = new LeafParser();
        CompositeParser wordParser = new CompositeParser
                (ComponentType.SYMBOL, leafParser, SYMBOL_REGEX);
        CompositeParser lexemeParser = new CompositeParser
                (ComponentType.WORD, wordParser, WORD_REGEX);
        CompositeParser sentenceParser = new CompositeParser
                (ComponentType.LEXEME, lexemeParser, LEXEME_REGEX);
        CompositeParser paragraphParser = new CompositeParser
                (ComponentType.SENTENCE, sentenceParser, SENTENCE_REGEX);
        CompositeParser textParser = new CompositeParser
                (ComponentType.PARAGRAPH, paragraphParser, PARAGRAPH_REGEX);
        return textParser.parseComponent(textString);
    }

    public String parseTextFromComponent(Component component) {
        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner symbolCombiner = new CompositeCombiner
                (leafCombiner, SYMBOL_PREFIX, SYMBOL_SUFFIX);
        CompositeCombiner wordCombiner = new CompositeCombiner
                (symbolCombiner, WORD_PREFIX, WORD_SUFFIX);
        CompositeCombiner lexemeCombiner = new CompositeCombiner
                (wordCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);
        CompositeCombiner sentenceCombiner = new CompositeCombiner
                (lexemeCombiner, SENTENCE_PREFIX, SENTENCE_SUFFIX);
        CompositeCombiner paragraphCombiner = new CompositeCombiner
                (sentenceCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);

        return (paragraphCombiner.combine(component));
    }

    public String sortParagraphsBySentencesCounts(String text) {

        LeafParser leafParser = new LeafParser();
        CompositeParser paragraphParser = new CompositeParser
                (ComponentType.SENTENCE, leafParser, SENTENCE_REGEX);
        CompositeParser textParser = new CompositeParser
                (ComponentType.PARAGRAPH, paragraphParser, PARAGRAPH_REGEX);
        Composite textComposite = (Composite) textParser.parseComponent(text);
        textComposite.getChilds().sort(Comparator
                .comparingInt(c -> ((Composite) c).getChilds().size()));
        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner sentenceCombiner = new CompositeCombiner(leafCombiner
                , SENTENCE_PREFIX, SENTENCE_SUFFIX);
        CompositeCombiner paragraphCombiner = new CompositeCombiner
                (sentenceCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);
        return (paragraphCombiner.combine(textComposite));

    }

    public String sortWordsInSentences(String text) {

        LeafParser leafParser = new LeafParser();
        CompositeParser wordParser = new CompositeParser
                (ComponentType.WORD, leafParser, WORD_REGEX);
        CompositeParser sentenceParser = new CompositeParser
                (ComponentType.SENTENCE, wordParser, SENTENCE_REGEX);

        Composite textComp = (Composite) sentenceParser.parseComponent(text);
        List<Component> sentences = textComp.getChilds();
        for (Component sentence : sentences) {
            ((Composite) sentence).getChilds().sort(Comparator
                    .comparingInt(c -> ((Leaf) c).getSymbolValue().length()));
        }

        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner wordCombiner = new CompositeCombiner
                (leafCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);

        CompositeCombiner sentenceCombiner = new CompositeCombiner
                (wordCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);
        return (sentenceCombiner.combine(textComp));

    }

    public String sortLexemesInText(String text, char c) {

        LeafParser leafParser = new LeafParser();

        CompositeParser textParser = new CompositeParser
                (ComponentType.LEXEME, leafParser, LEXEME_REGEX);
        Composite textComp = (Composite) textParser.parseComponent(text);
/*
        textComp.getChilds().sort(Comparator.comparingInt((Component lexeme)
                -> countOccurrences(((Leaf) lexeme).getSymbolValue(), c))
                .reversed().thenComparingInt(lexeme
                        -> ((Leaf) lexeme).getSymbolValue().charAt(0)));
*/
/*
        textComp.getChilds().sort(new Comparator<Component>() {
            @Override
            public int compare(Component lexeme1, Component lexeme2) {
                int result = countOccurrences(((Leaf) lexeme1).getSymbolValue()
                        , c)
                        - countOccurrences(((Leaf) lexeme2).getSymbolValue()
                        , c);
                if (result != 0) {
                    int maxLength = Math.max(
                            ((Leaf) lexeme1).getSymbolValue().length()
                            , ((Leaf) lexeme2).getSymbolValue().length());
                    for (int i = 0; i < maxLength; i++) {
                        int comparingByAlphabet =
                                ((Leaf) lexeme2).getSymbolValue().charAt(i)
                                        - ((Leaf) lexeme1).getSymbolValue()
                                        .charAt(i);
                        if (comparingByAlphabet == 0) {
                            return comparingByAlphabet;
                        }
                    }
                }
                return result;
            }
        });*/
        textComp.getChilds().sort(new Comparator<Component>() {
            @Override
            public int compare(Component lexeme1, Component lexeme2) {
                int result = countOccurrences(((Leaf) lexeme2).getSymbolValue()
                        , c)
                        - countOccurrences(((Leaf) lexeme1).getSymbolValue()
                        , c);
                if (result == 0) {
                    int minLength = Math.min(
                            ((Leaf) lexeme1).getSymbolValue().length()
                            , ((Leaf) lexeme2).getSymbolValue().length());
                    for (int i = 0; i < minLength; i++) {
                        int comparingByAlphabet =
                                ((Leaf) lexeme1).getSymbolValue().charAt(i)
                                        - ((Leaf) lexeme2).getSymbolValue()
                                        .charAt(i);
                        if (comparingByAlphabet == 0) {
                            return comparingByAlphabet;
                        }
                    }
                }
                return result;
            }
        });
        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner textCombiner = new CompositeCombiner
                (leafCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);
        return (textCombiner.combine(textComp));
    }

    private int countOccurrences(String string, char desired) {
        if (string.indexOf(desired) == -1) {
            return 0;
        }
        int count = 0;
        for (char c : string.toCharArray()) {
            if (desired == c) {
                count++;
            }
        }
        return count;
    }


}
