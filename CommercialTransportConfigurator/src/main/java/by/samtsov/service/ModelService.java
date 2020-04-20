package by.samtsov.service;

import by.samtsov.bean.entity.Model;

public interface ModelService extends Service<Model> {
     Model copyModel(Model oldModel) throws ServiceException;
}
