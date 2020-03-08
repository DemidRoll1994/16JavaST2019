package by.samtsov.bean.exceptions;


import by.samtsov.bean.enums.InternalServerErrors;

public class IncorrectDataException extends ServiceException {
    InternalServerErrors internalServerErrorType;

    public IncorrectDataException(InternalServerErrors errorType) {
        internalServerErrorType = errorType;
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
