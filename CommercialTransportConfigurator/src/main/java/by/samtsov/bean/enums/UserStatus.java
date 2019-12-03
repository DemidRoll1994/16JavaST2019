package by.samtsov.bean.enums;

public enum UserStatus {
    NOT_ACTIVATE,
    ACTIVE,
    BLOCKED;


    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
