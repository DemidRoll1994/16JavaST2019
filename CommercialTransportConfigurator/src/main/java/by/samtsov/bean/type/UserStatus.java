package by.samtsov.bean.type;

public enum UserStatus {
    NOT_ACTIVATE,
    ACTIVE,
    BLOCKED;


    public Integer getIdentity() {
        return ordinal();
    }

    public static UserStatus getByIdentity(Integer identity) {
        return UserStatus.values()[identity];
    }
}
