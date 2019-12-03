package by.samtsov.bean;

import by.samtsov.bean.enums.Role;
import by.samtsov.bean.enums.UserStatus;

public class User {
    private int id; //not null
    private String login; //not null
    private String passwordHash; //not null
    private String salt; //not null
    private UserStatus status; //not null
    private Role role; //not null
    private String companyName;
    private long phoneNumber = -1;
    private String address;


    public User() {
    }

    public User(int id, String login, String passwordHash, String salt, UserStatus status, Role role, String companyName, long phoneNumber, String address) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.status = status;
        this.role = role;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
