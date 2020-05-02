package by.samtsov.service;

public class InternalServerException extends Throwable {
    public InternalServerException(String s) {
        super(s);
    }

    public InternalServerException(String s, Exception e) {

        super(s, e);
    }
}
