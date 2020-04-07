package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.OptionValue;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.ModelDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLModelDao extends SQLBaseDao implements ModelDao {
    private static final String EMPTY_OPTION_VALUES_ERR_MSG = "Empty optionValues List. Use delete method to delete all option values from model";


    /*
    *
    * select distinct
    `models`.id, `models`.model_name, `models`.basic_price,
                (select `options`.id          from `options` where `options`.id =(select `option_values`.OPTION_ID from option_values where `option_values`.id=OPTION_values_ID )),
                (select `options`.name        from `options` where `options`.id =(select `option_values`.OPTION_ID from option_values where `option_values`.id=OPTION_values_ID )),
                (select `options`.option_type from `options` where `options`.id =(select `option_values`.OPTION_ID from option_values where `option_values`.id=OPTION_values_ID ))
from
    models
        join available_model_option_values  on models.id = available_model_option_values.model_ID
where `models`.id=1;
    *
    *
    * */


    @Override
    public Model get(int id) throws PersistenceException {
        String sql = "SELECT `id`, `model_name`, `basic_price` " +
                "FROM `models` where `id` =?";

        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Model model = null;
            if (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("model_name"));
                model.setPrice(resultSet.getDouble("basic_price"));
                model.setAvailableOptionValues(findOptionValuesByModelId(
                        model.getId()));
            }
            return model;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
    }

    @Override
    public List<Model> getAll() throws PersistenceException {
        String sql = "SELECT `id`, `model_name`, `basic_price` FROM `models` " +
                "ORDER BY `model_name`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Model> models = new ArrayList<>();
            Model model;
            while (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("model_name"));
                model.setPrice(resultSet.getDouble("basic_price"));
                models.add(model);
            }
            return models;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }


    }

    @Override
    public int add(Model model) throws PersistenceException {
        String sql = "INSERT INTO `models` (`model_name`, `basic_price`)" +
                " VALUES (?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getPrice());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            addOptionValues(model.getId(), model.getAvailableOptions().);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistenceException("There is no autoincrement index after trying to add record into table `models`");
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
    }

    @Override
    public void update(Model model) throws PersistenceException {
        String sql = "UPDATE `models` SET `model_name` = ?, `basic_price` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getPrice());
            statement.setInt(3, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        updateOptionValuesForModel(model.getId()
                , model.getAvailableOptionValues());
    }

    @Override
    public void delete(int modelId) throws PersistenceException {
        String sql = "DELETE FROM `models` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        deleteOptionValuesByModelId(modelId);
    }


    private List<OptionValue> findOptionValuesByModelId(int modelId) throws PersistenceException {
        String sql = "SELECT `option_value_id` FROM " +
                "`available_model_option_values` where `model_id` = ? ";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            resultSet = statement.executeQuery();
            List<OptionValue> optionValues = new ArrayList<>();
            while (resultSet.next()) {
                OptionValue optionValue = new OptionValue();
                optionValue.setId(resultSet.getInt("id"));
                optionValues.add(optionValue);
            }
            return optionValues;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
    }


    private void updateOptionValuesForModel(int modelId, List<OptionValue> newOptionValues) throws PersistenceException {
        if (newOptionValues == null) {
            throw new PersistenceException(EMPTY_OPTION_VALUES_ERR_MSG);
        }
        deleteOptionValuesByModelId(modelId);
        addOptionValues(modelId, newOptionValues);
    }

    private void addOptionValues(int modelId
            , List<OptionValue> optionValues) throws PersistenceException {
        if (optionValues == null) {
            throw new PersistenceException(EMPTY_OPTION_VALUES_ERR_MSG);
        }
        final int columnCountsInTable = 2;
        final String valuesPattern = "(?, ?)";
        //build sql insert query
        String sql = "INSERT INTO `available_model_option_values` " +
                "(`model_id`, `option_value_id`, ) VALUES " + valuesPattern;
        StringBuilder stringBuilder = new StringBuilder().append(sql);
        for (int i = 1; i < optionValues.size(); i++) {
            stringBuilder.append("," + valuesPattern);
        }
        sql = stringBuilder.toString();

        //fill sql insert query
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < optionValues.size(); i++) {
                statement.setInt(i * columnCountsInTable + 1, modelId);
                statement.setInt(i * columnCountsInTable + 2,
                        optionValues.get(i).getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }
        }
    }

    private void deleteOptionValuesByModelId(int configurationId)
            throws PersistenceException {
        final String sql = "DELETE FROM `available_model_option_values` " +
                "WHERE `model_id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, configurationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

}
