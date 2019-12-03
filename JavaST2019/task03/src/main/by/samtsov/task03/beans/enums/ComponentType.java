package by.samtsov.task03.beans.enums;

public enum ComponentType {
    /**
     * text enclosed between two indention.
     */
    PARAGRAPH,
    /**
     * text started from Big letter and ends with symbols .?!...
     */
    SENTENCE,
    /**
     * text enclosed between two spaces.
     */
    LEXEME,
    /**
     * text contains only non-space symbols(mainly letters).
     */
    WORD,
    /**
     * simplest element of text. all texts consist of them.
     */
    SYMBOL
}
