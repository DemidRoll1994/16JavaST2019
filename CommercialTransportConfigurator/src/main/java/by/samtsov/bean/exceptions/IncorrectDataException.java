package by.samtsov.bean.exceptions;

public class IncorrectDataException extends Throwable {

    public IncorrectDataException() {
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }
}
