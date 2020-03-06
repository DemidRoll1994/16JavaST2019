package by.samtsov.bean.enums;

public enum Role {
    BUYER,
    ADMIN,
    VENDOR;

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
        
    }
}
