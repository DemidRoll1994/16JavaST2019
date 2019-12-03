package by.samtsov.dao.impl;

import by.samtsov.bean.Option;
import by.samtsov.bean.enums.OptionType;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.OptionDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OptionDaoImpl extends BaseDaoImpl implements OptionDao {


    @Override
    public Option get(int id) throws PersistentException {
        String sql = "SELECT `id`, `name`, `option_type` " +
                "FROM `options` where `id` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Option option = null;
            if (resultSet.next()) {
                option = new Option();
                option.setId(resultSet.getInt("id"));
                option.setName(resultSet.getString("name"));
                option.setType(OptionType.getByIdentity(
                        resultSet.getInt("option_type")));
                // todo mo to the service-layer option.setAvailableOptions(resultSet.getString("salt"));
            }
            return option;
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
    public List<Option> getAll() throws PersistentException {
        String sql = "SELECT `id`, `name`, `option_type` FROM `options` " +
                "ORDER BY `id`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Option> options = new ArrayList<>();
            Option option;
            while (resultSet.next()) {
                option = new Option();
                option.setId(resultSet.getInt("id"));
                option.setName(resultSet.getString("name"));
                option.setType(OptionType.getByIdentity(
                        resultSet.getInt("option_type")));
                options.add(option);
            }
            return options;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public int add(Option option) throws PersistentException {
        String sql = "INSERT INTO `options` (`name`, `option_type`)" +
                " VALUES (?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, option.getName());
            statement.setDouble(2, option.getType().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistentException("There is no autoincrement " +
                        "index after trying to add record into table `models`");
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
    public void update(Option option) throws PersistentException {
        String sql = "UPDATE `options` SET `name` = ?, `option_type` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, option.getName());
            statement.setDouble(2, option.getType().getIdentity());
            statement.setInt(3, option.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(int optionId) throws PersistentException {
        String sql = "DELETE FROM `options` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, optionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

}
