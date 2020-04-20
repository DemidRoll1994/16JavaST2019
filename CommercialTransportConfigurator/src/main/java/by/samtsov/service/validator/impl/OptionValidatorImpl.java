package by.samtsov.service.validator.impl;

import by.samtsov.bean.entity.Option;
import by.samtsov.service.IncorrectDataException;
import by.samtsov.service.validator.OptionValidator;
import by.samtsov.service.validator.Validator;

public class OptionValidatorImpl extends Validator<Option> implements OptionValidator {
    @Override
    public boolean isValid(Option entity) throws IncorrectDataException {
        return false;
    }
}
