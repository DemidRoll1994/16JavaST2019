package by.samtsov.service.validator;

import by.samtsov.service.IncorrectDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator<T> {

    public abstract boolean isValid(T entity) throws IncorrectDataException;
    // todo move to interface or remove/ implementeted classes cannot use
    //  methods of both interfaces and abstract class.

    protected boolean isValidNotRequiredString(String value, Pattern pattern) {
        return value == null || isValueMatchPattern(value, pattern);
    }

    protected boolean isValidRequiredString(String value, Pattern pattern) {
        return isValueMatchPattern(value, pattern);
    }

    private boolean isValueMatchPattern(String value, Pattern pattern) {
        if (value != null) {
            Matcher matcher = pattern.matcher(value);
            return matcher.find();
        }
        return false;
    }

}
