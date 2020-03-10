package by.samtsov.bean.type;

public enum InternalServerErrors {
    INVALID_LOGIN_FORM("Login must contain 3 to 16 characters"),
    LOGIN_ALREADY_EXISTS("Login already exists"),
    LOGIN_DOESNT_EXISTS("Login doesn't exists"),
    INVALID_EMAIL_FORM("Email must be in valid form"),
    EMAIL_ALREADY_EXISTS("User with this email is already exists"),
    EMAIL_DOESNT_EXISTS("Email doesn't exists"),
    INVALID_PASSWORD_FORM("Password be at least 6 characters long, must contain uppercase and lowercase letters, numbers and symbols"),
    INVALID_USERNAME_OR_PASSWORD("Incorrect username or password");


    private String message;

    private InternalServerErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
