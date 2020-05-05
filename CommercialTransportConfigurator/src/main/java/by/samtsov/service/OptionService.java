package by.samtsov.service;

import by.samtsov.bean.entity.Option;

public interface OptionService extends Service<Option>{
    Option get(int id) throws ServiceException, InternalServerException;
}
