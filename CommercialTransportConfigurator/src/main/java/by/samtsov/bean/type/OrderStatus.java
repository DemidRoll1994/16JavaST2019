package by.samtsov.bean.type;

public enum OrderStatus {
    FORMING,
    FORMED,
    CONFIRMED,
    IN_PROCESS,
    READY;

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }

}
