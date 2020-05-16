package by.samtsov.dao;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.OptionValue;

import java.util.List;

public interface AvailableModelOptionValuesDao extends Dao<Object>{ //todo dao not oblject
    List<OptionValue> getModelsByOptionValueId(int optionValueId) throws PersistenceException;

    List<Model> getOptionValuesByModelId(int modelId) throws PersistenceException;
}
