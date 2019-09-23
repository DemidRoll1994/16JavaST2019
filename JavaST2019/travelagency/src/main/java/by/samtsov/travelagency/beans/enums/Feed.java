package by.samtsov.travelagency.beans.enums;

/**
 * Created by samtsov on 21.09.19.
 */
public enum Feed {

    /**
     * Bed & breakfast.
     * includes only breakfast
     */
    BB("Bed and breakfast"),

    /**
     * Half board.
     * includes breakfast and dinner
     */
    HB("Half board"),

    /**
     * Full board.
     * includes breakfast, lunch and dinner
     */
    FB("Full board"),

    /**
     * All inclusive.
     * includes breakfast, lunch, dinner and beverages
     */
    AI("All inclusive");

    /**
     * String that contains name of type.
     */
    private String name;

    /**
     * Enum constructor.
     */
    Feed(String code) {
        this.name = code;
    }

    /**
     * Method @return name of type.
     */
    public String toString() {
        return name;
    }
}
