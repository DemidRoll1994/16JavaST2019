package by.samtsov.service.validator;

import by.samtsov.service.IncorrectDataException;

public interface Validator<T> {

    boolean isValid(T entity) throws IncorrectDataException;
}
