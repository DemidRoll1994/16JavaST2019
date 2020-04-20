package by.samtsov.service.validator.impl;

import by.samtsov.bean.entity.Model;
import by.samtsov.service.validator.ModelValidator;
import by.samtsov.service.validator.Validator;

public class ModelValidatorImpl extends Validator<Model> implements ModelValidator {

    @Override
    public boolean isValid(Model model) {
        return model != null && model.getAvailableOptions() != null && model.getPrice() >= 0 ;
    }


}
