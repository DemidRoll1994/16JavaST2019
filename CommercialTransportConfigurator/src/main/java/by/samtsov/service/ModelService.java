package by.samtsov.service;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.Option;

public interface ModelService extends Service<Model> {
    Model copyModel(Model oldModel) throws ServiceException;

    void addAvailableOption(int modelId, Option option) throws ServiceException;

    void deleteAvailableOption(int modelId, int optionId)
            throws ServiceException;

    void editAvailableOption(int modelId, Option option)
            throws ServiceException;
}
