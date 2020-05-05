package by.samtsov.service;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.Option;
import by.samtsov.bean.entity.OptionValue;

import java.util.HashMap;

public interface ModelService extends Service<Model> {
    Model copyModel(Model oldModel) throws ServiceException;

    void addAvailableOption(int modelId, Option option) throws ServiceException;

    void deleteAvailableOption(int modelId, int optionId)
            throws ServiceException;

    void editAvailableOption(int modelId, Option option)
            throws ServiceException;

    HashMap<OptionValue, Boolean> findActiveValuesForOption(int modelId
            , int optionId) throws ServiceException;
}
