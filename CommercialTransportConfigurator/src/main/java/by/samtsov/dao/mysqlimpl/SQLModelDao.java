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
        String sql = "SELECT `id`, `model_name`, `basic_price`, `img_path` " +
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
                model.setBasicPrice(resultSet.getDouble("basic_price"));
                model.setImgPath(resultSet.getString("img_path"));
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
        String sql = "SELECT `id`, `model_name`, `basic_price`,`img_path` " +
                "FROM `models`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Model> models = new ArrayList<>();
            Model model;
            while (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("model_name"));
                model.setBasicPrice(resultSet.getDouble("basic_price"));
                model.setImgPath(resultSet.getString("img_path"));
                models.add(model);
            }
            return models;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public int add(Model model) throws PersistenceException {
        String sql = "INSERT INTO `models` (`model_name`, `basic_price`," +
                "`img_path`) VALUES (?, ?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getBasicPrice());
            statement.setString(3, model.getImgPath());
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
                throw new PersistenceException(e);
            }
        }
    }

    @Override
    public void update(Model model) throws PersistenceException {
        String sql = "UPDATE `models` SET `model_name` = ?, `basic_price` = ?" +
                ", `img_path` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getBasicPrice());
            statement.setString(3, model.getImgPath());
            statement.setInt(4, model.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
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
    }



}
