package by.samtsov.service.validator;

import by.samtsov.bean.exceptions.IncorrectDataException;

public interface Validator<T> {

    boolean isValid(T entity) throws IncorrectDataException;
}
