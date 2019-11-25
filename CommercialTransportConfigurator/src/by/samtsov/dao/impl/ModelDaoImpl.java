package by.samtsov.dao.impl;

import by.samtsov.bean.Model;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelDaoImpl extends BaseDaoImpl implements Dao<Model> {


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
    public Model get(int id) throws PersistentException {
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
                // todo mo to the service-layer model.setAvailableOptions(resultSet.getString("salt"));
            }
            return model;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Model> getAll() throws PersistentException {
        String sql = "SELECT `id`, `model_name`, `basic_price` FROM `models` " +
                "ORDER BY `model_name`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Model> models = new ArrayList<>();
            Model model = null;
            while (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("model_name"));
                model.setPrice(resultSet.getDouble("basic_price"));
                models.add(model);
            }
            return models;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }


    }

    @Override
    public int add(Model model) throws PersistentException {
        String sql = "INSERT INTO `models` (`model_name`, `basic_price`)" +
                " VALUES (?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getPrice());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistentException("There is no autoincrement index after trying to add record into table `models`");
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void update(Model model) throws PersistentException {
        String sql = "UPDATE `models` SET `model_name` = ?, `basic_price` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getPrice());
            statement.setInt(3, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(int modelId) throws PersistentException {
        String sql = "DELETE FROM `models` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

}