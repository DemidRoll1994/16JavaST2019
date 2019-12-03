package by.samtsov.service.validator;

import by.samtsov.bean.exceptions.IncorrectDataException;

public abstract class Validator<T> {

    abstract boolean isValid(T entity) throws IncorrectDataException;
}
