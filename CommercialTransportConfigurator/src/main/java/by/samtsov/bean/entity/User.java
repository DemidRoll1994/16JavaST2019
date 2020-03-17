package by.samtsov.bean.entity;

import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;

public class User {
    private int id; //not null
    private String email; //not null
    private String passwordHash; //not null
    private String salt; //not null
    private UserStatus status; //not null
    private Role role; //not null
    private String companyName;
    private long phoneNumber = -1;
    private String address;
    private String name;
    private String surname;

    public User() {
    }

    public User(int id, String passwordHash, String salt, UserStatus status, Role role, String companyName, long phoneNumber, String address, String email, String name, String surname) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.status = status;
        this.role = role;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email=email;
        this.name=name;
        this.surname=surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
