package by.samtsov.webxml.service.exception;

public class ServiceException extends Exception {


    public ServiceException() {
        super();
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
