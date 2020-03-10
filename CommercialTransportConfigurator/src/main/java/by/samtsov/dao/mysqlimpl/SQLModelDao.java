package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.Model;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.ModelDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLModelDao extends SQLBaseDao implements ModelDao {


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
                // todo mo to the service-layer model.setAvailableOptions(resultSet.getString("salt"));
            }
            return model;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
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
            }
        }
    }

    @Override
    public int update(Model model) throws PersistenceException {
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
        return 0;
    }

    @Override
    public int delete(int modelId) throws PersistenceException {
        String sql = "DELETE FROM `models` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistenceException("There is no autoincrement index after trying to add record into table `users`");
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

}
