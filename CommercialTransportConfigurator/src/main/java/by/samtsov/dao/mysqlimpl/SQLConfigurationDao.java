package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.Configuration;
import by.samtsov.bean.entity.Model;
import by.samtsov.bean.entity.OptionValue;
import by.samtsov.dao.ConfigurationDao;
import by.samtsov.dao.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLConfigurationDao extends SQLBaseDao implements ConfigurationDao {

    private static final Logger logger = LogManager.getLogger(
            SQLConfigurationDao.class);

    @Override
    public Configuration get(int id) throws PersistenceException {
        String sql = "SELECT `id`, `name`, `model_id`, `owner_id`, FROM `configurations` where `id` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Configuration configuration = null;
            if (resultSet.next()) {
                configuration = new Configuration();
                configuration.setId(resultSet.getInt("id"));
                configuration.setName(resultSet.getString("name"));
                Model model = new Model();
                model.setId(resultSet.getInt("model_id"));
                configuration.setModel(model);
                configuration.setOwner_id(resultSet.getInt("owner_id"));
              //todo  configuration.setOptionValues(getOptionValuesByConfigId(configuration.getId()));
            }
            return configuration;
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
    public List<Configuration> getAll() throws PersistenceException {
        String sql = "SELECT `id`, `name`, `model_id`, `owner_id`, FROM `configurations` order by id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Configuration> configurations = new ArrayList<>();
            Configuration configuration = null;
            while (resultSet.next()) {
                configuration = new Configuration();
                configuration.setId(resultSet.getInt("id"));
                configuration.setName(resultSet.getString("name"));
                Model model = new Model();
                model.setId(resultSet.getInt("model_id"));
                configuration.setModel(model);
                configuration.setOwner_id(resultSet.getInt("owner_id"));
               //todo configuration.setOptionValues(getOptionValuesByConfigId(configuration.getId()));
                configurations.add(configuration);
            }
            return configurations;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }


    }

    @Override
    public int add(Configuration configuration) throws PersistenceException {
        String sql = "INSERT INTO `configurations` (`name`, `model_id`, " +
                "`owner_id`) VALUES (?, ?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, configuration.getName());
            statement.setInt(2, configuration.getModel().getId());
            statement.setInt(3, configuration.getOwner_id());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistenceException("There is no autoincrement index after trying to add record into table `users`");
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
    public void update(Configuration configuration) throws PersistenceException {
        String sql = "UPDATE `configurations` SET `name` = ?, " +
                "`model_id` = ?, `owner_id` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, configuration.getName());
            statement.setInt(2, configuration.getModel().getId());
            statement.setInt(3, configuration.getOwner_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(int configurationId) throws PersistenceException {
        String sql = "DELETE FROM `configurations` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, configurationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private List<OptionValue> findOptionValuesByConfigId(int configurationId) throws PersistenceException {
        String sql = "SELECT `option_value_id` FROM `selected_config_option_values` order by `id` where `email` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, configurationId);
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

/*
    private void updateOptionValuesForConfig(int configurationId, List<OptionValue> newOptionValues) throws PersistenceException {
        if (newOptionValues == null) {
            throw new PersistenceException("Empty optionValues List. Use delete method to delete all option values from configuration");
        }
        final int columnCountsInTable = 2;
        final String valuesPattern = "(?, ?)";
        String sql = "DELETE FROM `selected_config_option_values` WHERE `config_id` = ?;" +
                "INSERT INTO `selected_config_option_values` (`config_id`, " +
                "`option_value_id`, ) VALUES " + valuesPattern;
        StringBuilder stringBuilder = new StringBuilder().append(sql);
        for (int i = 1; i < newOptionValues.size(); i++) {
            stringBuilder.append("," + valuesPattern);
        }
        sql = stringBuilder.toString();


        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < newOptionValues.size(); i++) {
                statement.setInt(i * columnCountsInTable + 1, configurationId);
                statement.setInt(i * columnCountsInTable + 2, newOptionValues.get(i));
// todo remake. too much hardcode.
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistenceException("There is no autoincrement index after trying to add record into table `users`");
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


        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, configurationId);
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
    }*/

}
