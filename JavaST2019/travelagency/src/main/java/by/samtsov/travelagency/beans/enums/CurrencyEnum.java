package by.samtsov.travelagency.beans.enums;

/**
 * Created by samtsov on 21.09.19.
 */
public enum CurrencyEnum {

    /**
     * American dollar
     */
    USD("USD"),

    /**
     * Euro
     */
    EUR("EUR"),

    /**
     * Russian ruble
     */
    RUR("RUR"),

    /**
     * Belorussian ruble
     */
    BYN("BYN");

    /**
     * String that contains name of type.
     */
    private String code;

    /**
     * Enum constructor.
     */
    CurrencyEnum(String currencyCode) {
        this.code = currencyCode;
    }

    /**
     * Method @return name of type.
     */
    public String toString() {
        return code;
    }

}
