package by.samtsov.travelagency.beans.enums;

/**
 * Created by samtsov on 21.09.19.
 */
public enum Transport {

    /**
     * Road transport.
     */
    BUS("Coach bus"),

    /**
     * Air transport.
     */
    PLAIN("Airplane"),

    /**
     * Water transport.
     */
    SHIP("Ship"),

    /**
     * Railway transport.
     */
    TRAIN("Train");

    /**
     * String that contains name of type.
     */
    private String name;

    /**
     * Enum constructor.
     */
    Transport(String code) {
        this.name = code;
    }

    /**
     * Method @return name of type.
     */
    public String toString() {
        return name;
    }
}
