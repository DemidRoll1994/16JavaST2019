package by.samtsov.dao;

import by.samtsov.bean.entity.OptionValue;

import java.util.List;

public interface OptionValueDao extends Dao<OptionValue> {
    List<OptionValue> findByModelId(int id) throws PersistenceException;

    void updateOptionValuesForModel(int modelId,
                                    List<OptionValue> newOptionValues)
            throws PersistenceException;

    void addOptionValuesByModelId(int modelId
            , List<OptionValue> optionValues) throws PersistenceException;

    void deleteOptionValuesByModelId(int modelId)
            throws PersistenceException;

    List<OptionValue> getByOptionId(int optionId) throws PersistenceException;

}
