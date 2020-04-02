package by.samtsov.service.sql;

import by.samtsov.bean.entity.OptionValue;
import by.samtsov.service.InternalServerException;
import by.samtsov.service.Service;
import by.samtsov.service.ServiceException;

import java.util.List;

public interface OptionValuesService extends Service<OptionValue> {
    List<OptionValue> getAll(List<OptionValue> optionValueList) throws ServiceException, InternalServerException;
}
