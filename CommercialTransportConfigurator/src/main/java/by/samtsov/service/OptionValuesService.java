package by.samtsov.service;

import by.samtsov.bean.entity.OptionValue;

import java.util.List;

public interface OptionValuesService extends Service<OptionValue> {
    List<OptionValue> getAll(List<OptionValue> optionValueList) throws ServiceException, InternalServerException;
}
