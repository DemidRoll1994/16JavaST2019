package by.samtsov.travelagency.beans.enums;

public enum HealthCareType {

    /**
     * Spa-procedures.
     */
    SPA("Spa"),

    /**
     * Procedures uses sea salt.
     */
    SPELEO("Speleo"),

    /**
     * gastro-procedures.
     * mineral water, juices, vegetables, meet, beer... yes, it's very healthy!!!
     */
    GASTRO("Gastro");

    /**
     * String that contains name of type.
     */
    private String name;

    /**
     * Enum constructor.
     */
    HealthCareType(String code) {
        this.name = code;
    }

    /**
     * Method @return name of type.
     */
    public String toString() {
        return name;
    }
}
