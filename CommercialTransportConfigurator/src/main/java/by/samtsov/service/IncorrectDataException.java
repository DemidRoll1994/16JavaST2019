package by.samtsov.service;


import by.samtsov.bean.type.InternalServerErrors;

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

    public InternalServerErrors getInternalServerErrorType() {
        return internalServerErrorType;
    }

    public String getErrorMessage() {
        if (internalServerErrorType == null) {
            return "Incorrect data";
        }
        return internalServerErrorType.getMessage();
    }

}
