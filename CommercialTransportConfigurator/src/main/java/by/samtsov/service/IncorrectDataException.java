package by.samtsov.service;


import by.samtsov.bean.type.InternalServerError;

public class IncorrectDataException extends ServiceException {

    InternalServerError internalServerErrorType;

    public IncorrectDataException(InternalServerError errorType) {
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

    public InternalServerError getInternalServerErrorType() {
        return internalServerErrorType;
    }

    public String getErrorMessage() {
        if (internalServerErrorType != null) {
            return internalServerErrorType.getMessage();
        }
        if (getMessage() != null) {
            return getMessage();
        }
        return "Incorrect data";
    }

}
