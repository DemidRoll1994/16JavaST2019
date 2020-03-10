package by.samtsov.bean.type;

public enum OptionType {
    STRING,
    INTEGER,
    DOUBLE,
    BOOLEAN;

    public Integer getIdentity() {
        return ordinal();
    }

    public static OptionType getByIdentity(Integer identity) {
        return OptionType.values()[identity];
    }
}
