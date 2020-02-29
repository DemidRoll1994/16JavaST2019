package java.by.samtsov.task03.service;

import java.by.samtsov.task03.beans.entity.Component;
import java.by.samtsov.task03.beans.entity.Composite;
import java.by.samtsov.task03.beans.entity.Leaf;
import java.by.samtsov.task03.beans.enums.ComponentType;
import java.by.samtsov.task03.service.combiner.CompositeCombiner;
import java.by.samtsov.task03.service.combiner.LeafCombiner;
import java.by.samtsov.task03.service.parser.CompositeParser;
import java.by.samtsov.task03.service.parser.LeafParser;

import java.util.Comparator;
import java.util.List;

public class TextService {
    /**
     * Constant with all symbols match regex.
     */
    private static final String SYMBOL_REGEX = ".";
    /**
     * Constant contains symbol prefix for concatenation.
     */
    private static final String SYMBOL_PREFIX = "";
    /**
     * Constant contains symbol suffix for concatenation.
     */
    private static final String SYMBOL_SUFFIX = "";

    /**
     * Constant with all words match regex.
     */
    private static final String WORD_REGEX = "[?,.!…:;]+|[^\\s?,.!…:;]+";
    /**
     * Constant contains word prefix for concatenation.
     */
    private static final String WORD_PREFIX = "";
    /**
     * Constant contains word suffix for concatenation.
     */
    private static final String WORD_SUFFIX = "";

    /**
     * Constant with all lexemes match regex.
     */
    private static final String LEXEME_REGEX = "\\S+";

    /**
     * Constant contains lexeme prefix for concatenation.
     */
    private static final String LEXEME_PREFIX = "";
    /**
     * Constant contains lexeme suffix for concatenation.
     */
    private static final String LEXEME_SUFFIX = " ";

    /**
     * Constant with all sentences match regex.
     */
    private static final String SENTENCE_REGEX = "\\s*[A-Z].+?[\\.!?]+";
    /**
     * Constant contains sentence prefix for concatenation.
     */
    private static final String SENTENCE_PREFIX = "";
    /**
     * Constant contains sentence suffix for concatenation.
     */
    private static final String SENTENCE_SUFFIX = "";

    /**
     * Constant with all paragraphs match regex.
     */
    private static final String PARAGRAPH_REGEX = ".+";
    /**
     * Constant contains paragraph prefix for concatenation.
     */
    private static final String PARAGRAPH_PREFIX = "    ";
    /**
     * Constant contains paragraph suffix for concatenation.
     */
    private static final String PARAGRAPH_SUFFIX = "\n";

    /**
     * using ComponentParsers parse text to Component.
     *
     * @param textString - initial string
     * @return text as a Component
     */
    public Component parseComponentFromText(final String textString) {

        LeafParser leafParser = new LeafParser();
        CompositeParser wordParser = new CompositeParser(
                ComponentType.SYMBOL, leafParser, SYMBOL_REGEX);
        CompositeParser lexemeParser = new CompositeParser(
                ComponentType.WORD, wordParser, WORD_REGEX);
        CompositeParser sentenceParser = new CompositeParser(
                ComponentType.LEXEME, lexemeParser, LEXEME_REGEX);
        CompositeParser paragraphParser = new CompositeParser(
                ComponentType.SENTENCE, sentenceParser, SENTENCE_REGEX);
        CompositeParser textParser = new CompositeParser(
                ComponentType.PARAGRAPH, paragraphParser, PARAGRAPH_REGEX);
        return textParser.parseComponent(textString);
    }

    /**
     * using ComponentCombiners concatenate component to text.
     *
     * @param component - initial component
     * @return Component as a text
     */

    public String parseTextFromComponent(final Component component) {
        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner symbolCombiner = new CompositeCombiner(
                leafCombiner, SYMBOL_PREFIX, SYMBOL_SUFFIX);
        CompositeCombiner wordCombiner = new CompositeCombiner(
                symbolCombiner, WORD_PREFIX, WORD_SUFFIX);
        CompositeCombiner lexemeCombiner = new CompositeCombiner(
                wordCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);
        CompositeCombiner sentenceCombiner = new CompositeCombiner(
                lexemeCombiner, SENTENCE_PREFIX, SENTENCE_SUFFIX);
        CompositeCombiner paragraphCombiner = new CompositeCombiner(
                sentenceCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);

        return (paragraphCombiner.combine(component));
    }

    /**
     * sort Paragraphs java.by Sentences count.
     *
     * @param text initial text to sort as String
     * @return sorted text as a String
     */
    public String sortParagraphsBySentencesCounts(final String text) {

        LeafParser leafParser = new LeafParser();
        CompositeParser paragraphParser = new CompositeParser(
                ComponentType.SENTENCE, leafParser, SENTENCE_REGEX);
        CompositeParser textParser = new CompositeParser(
                ComponentType.PARAGRAPH, paragraphParser, PARAGRAPH_REGEX);
        Composite textComposite = (Composite) textParser.parseComponent(text);

        textComposite.getChilds().sort(Comparator
                .comparingInt(c -> ((Composite) c).getChilds().size()));

        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner sentenceCombiner = new CompositeCombiner(leafCombiner,
                SENTENCE_PREFIX, SENTENCE_SUFFIX);
        CompositeCombiner paragraphCombiner = new CompositeCombiner(
                sentenceCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);
        return (paragraphCombiner.combine(textComposite));

    }

    /**
     * sort words in each Sentence.
     *
     * @param text initial text to sort as string
     * @return sorted text as a String
     */

    public String sortWordsInSentences(final String text) {

        LeafParser leafParser = new LeafParser();
        CompositeParser wordParser = new CompositeParser(
                ComponentType.WORD, leafParser, WORD_REGEX);
        CompositeParser sentenceParser = new CompositeParser(
                ComponentType.SENTENCE, wordParser, SENTENCE_REGEX);

        Composite textComp = (Composite) sentenceParser.parseComponent(text);


        List<Component> sentences = textComp.getChilds();
        for (Component sentence : sentences) {
            ((Composite) sentence).getChilds().sort(Comparator
                    .comparingInt(c -> ((Leaf) c).getValue().length()));
        }

        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner wordCombiner = new CompositeCombiner(
                leafCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);

        CompositeCombiner sentenceCombiner = new CompositeCombiner(
                wordCombiner, PARAGRAPH_PREFIX, PARAGRAPH_SUFFIX);
        return (sentenceCombiner.combine(textComp));

    }

    /**
     * sorts Lexemes in text java.by @param c in descend order.
     * also if @param с count is equals in few lexemes, sorts java.by alphabet.
     *
     * @param text - initial text to sort
     * @param c    - sorting symbol
     * @return sorted text as a string
     */
    public String sortLexemesInText(final String text, final char c) {

        LeafParser leafParser = new LeafParser();

        CompositeParser textParser = new CompositeParser(
                ComponentType.LEXEME, leafParser, LEXEME_REGEX);
        Composite textComp = (Composite) textParser.parseComponent(text);

        textComp.getChilds().sort((lexeme1, lexeme2) -> {
            int result = countOccurrences(((Leaf) lexeme2).getValue(), c)
                    - countOccurrences(((Leaf) lexeme1).getValue(), c);
            if (result == 0) {
                int minLength = Math.min(
                        ((Leaf) lexeme1).getValue().length(),
                        ((Leaf) lexeme2).getValue().length());
                for (int i = 0; i < minLength; i++) {
                    int comparingByAlphabet =
                            ((Leaf) lexeme1).getValue().charAt(i)
                                    - ((Leaf) lexeme2).getValue()
                                    .charAt(i);
                    if (comparingByAlphabet != 0) {
                        return comparingByAlphabet;
                    }
                }
            }
            return result;
        });
        LeafCombiner leafCombiner = new LeafCombiner();
        CompositeCombiner textCombiner = new CompositeCombiner(
                leafCombiner, LEXEME_PREFIX, LEXEME_SUFFIX);
        return (textCombiner.combine(textComp));
        //todo разобраться с работой, оттестировать, в том числе и на {fgh, fg}
    }

    /**
     * method count a number of occurrences @param desired in @param string.
     *
     * @param string  - string, where we are looking for
     * @param desired - char, what we are looking for
     * @return count of occurrences
     */
    private int countOccurrences(final String string, final char desired) {
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
