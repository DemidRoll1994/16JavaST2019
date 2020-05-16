package by.samtsov.dao;

import by.samtsov.bean.entity.Configuration;
import by.samtsov.bean.entity.OptionValue;

import java.util.List;

public interface SelectedConfigOptionValuesDao extends Dao {
    List<Configuration> getConfigByOptionValueId(int optionValueId) throws PersistenceException;

    List<OptionValue> getOptionValuesByConfigId(int configId) throws PersistenceException;
}
