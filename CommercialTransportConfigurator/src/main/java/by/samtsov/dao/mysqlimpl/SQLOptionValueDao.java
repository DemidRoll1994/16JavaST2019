package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.OptionValue;
import by.samtsov.dao.OptionValueDao;
import by.samtsov.dao.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLOptionValueDao extends SQLBaseDao implements OptionValueDao {


    private static final String EMPTY_OPTION_VALUES_ERR_MSG = "option values " +
            "list is empty. use delete method to remove all values for model";

    @Override
    public OptionValue get(int id) throws PersistenceException {
        String sql = "SELECT `id`, `value`, `description`, `price`," +
                " `OPTION_ID` FROM `OPTION_VALUES` where `id` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            OptionValue optionValue = null;
            if (resultSet.next()) {
                optionValue = new OptionValue();
                optionValue.setId(resultSet.getInt("id"));
                optionValue.setValue(resultSet.getString("value"));
                optionValue.setDescription(resultSet.getString("description"));
                optionValue.setPrice(resultSet.getDouble("price"));
                optionValue.setOptionId(resultSet.getInt("OPTION_ID"));
            }
            return optionValue;
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
    public List<OptionValue> getAll() throws PersistenceException {
        String sql = "SELECT `id`, `value`, `description`, `price`, " +
                "`option_id` FROM `OPTION_VALUES` ORDER BY `option_id`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<OptionValue> optionValues = new ArrayList<>();
            OptionValue optionValue = null;
            while (resultSet.next()) {
                optionValue = new OptionValue();
                optionValue.setId(resultSet.getInt("id"));
                optionValue.setValue(resultSet.getString("value"));
                optionValue.setDescription(resultSet.getString("description"));
                optionValue.setPrice(resultSet.getDouble("price"));
                optionValue.setOptionId(resultSet.getInt("option_id"));
                optionValues.add(optionValue);
            }
            return optionValues;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public int add(OptionValue optionValue) throws PersistenceException {
        String sql = "INSERT INTO `OPTION_VALUES` (`value`, `description`," +
                " `price`) VALUES (?, ?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, optionValue.getValue().toString());
            statement.setString(2, optionValue.getDescription());
            statement.setDouble(3, optionValue.getPrice());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistenceException("There is no autoincrement " +
                        "index after trying to add record into table " +
                        "`option_values`");
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
    public List<OptionValue> findByModelId(int modelId) throws PersistenceException {
        String sql = "SELECT `option_value_id` FROM " +
                "`available_model_option_values` where `model_id` = ? ";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            resultSet = statement.executeQuery();
            List<OptionValue> optionValues = new ArrayList<>();
            while (resultSet.next()) {
                OptionValue optionValue = new OptionValue();
                optionValue.setId(resultSet.getInt("option_value_id"));
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


    public void updateOptionValuesForModel(int modelId,
                                           List<OptionValue> newOptionValues) throws PersistenceException {
        deleteAllByModelId(modelId);
        if (newOptionValues != null) {
            addOptionValuesByModelId(modelId, newOptionValues);
        }
    }

    public void addOptionValuesByModelId(int modelId
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
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < optionValues.size(); i++) {
                statement.setInt(i * columnCountsInTable + 1, modelId);
                statement.setInt(i * columnCountsInTable + 2,
                        optionValues.get(i).getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public void deleteAllByModelId(int modelId)
            throws PersistenceException {
        final String sql = "DELETE FROM `available_model_option_values` " +
                "WHERE `model_id` = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    public void deleteByModelAndOptionId(int modelId, int optionValueId)
            throws PersistenceException {
        final String sql = "DELETE FROM `available_model_option_values` " +
                "WHERE `model_id` = ? AND `OPTION_value_ID`=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, modelId);
            statement.setInt(2, optionValueId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<OptionValue> getByOptionId(int optionId) throws PersistenceException {
        String sql = "SELECT `id`, `value`, `description`, `price`" +
                " FROM `OPTION_VALUES` where `OPTION_ID` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, optionId);
            resultSet = statement.executeQuery();
            List<OptionValue> optionValues = new ArrayList<>();
            OptionValue optionValue = null;
            while (resultSet.next()) {
                optionValue = new OptionValue();
                optionValue.setId(resultSet.getInt("id"));
                optionValue.setValue(resultSet.getString("value"));
                optionValue.setDescription(resultSet.getString("description"));
                optionValue.setPrice(resultSet.getDouble("price"));
                optionValue.setOptionId(resultSet.getInt("option_id"));
                optionValues.add(optionValue);
            }
            return optionValues;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                throw new PersistenceException();
            }
        }
    }


    @Override
    public void update(OptionValue user) throws PersistenceException {
        /*String sql = "UPDATE `users` SET `login` = ?, `password_hash` = ?, " +
                "`salt` = ?, `status` = ?, `role` = ?, `company` = ?," +
                " `phoneNumber` = ?, `address` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getSalt());
            statement.setInt(4, user.getStatus().getIdentity());
            statement.setInt(5, user.getRole().getIdentity());
            if (user.getCompanyName() != null) {
                statement.setString(6, user.getCompanyName());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (user.getPhoneNumber() != -1) {
                statement.setLong(7, user.getPhoneNumber());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            if (user.getCompanyName() != null) {
                statement.setString(8, user.getCompanyName());
            } else {
                statement.setNull(8, Types.INTEGER);
            }
            statement.setInt(9, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }*/
    }

    @Override
    public void delete(int userId) throws PersistenceException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
