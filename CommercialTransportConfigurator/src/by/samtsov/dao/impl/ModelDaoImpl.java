package by.samtsov.dao.impl;

import by.samtsov.bean.Model;
import by.samtsov.dao.Dao;

import java.sql.*;
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
    public Model get(int id) {
        String sql = "SELECT `id`, `model_name`, `basic_price` " +
                "FROM `model` join" +
                "where `id` =?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Model model = null;
            if (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("model_name"));
                model.setPrice(resultSet.getDouble("basic_price"));

                model.setAvailableOptions(resultSet.getString("salt"));

            }
            return model;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address` FROM " +
                "`users` ORDER BY `login`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setSalt(resultSet.getString("salt"));
                user.setStatus(UserStatus.valueOf(resultSet.getString("status")));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                String company = resultSet.getString("company");
                if (!resultSet.wasNull()) {
                    user.setCompanyName(company);
                }
                long phone_number = resultSet.getLong("Phone_number");
                if (!resultSet.wasNull()) {
                    user.setPhoneNumber(phone_number);
                }
                String address = resultSet.getString("address");
                if (!resultSet.wasNull()) {
                    user.setAddress(address);
                }
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }


    }

    @Override
    public int add(User user) {
        String sql = "INSERT INTO `users` (`id`, `login`, `password_hash`," +
                " `salt`, `status`, `role`,`company`, `Phone_number`," +
                "`address`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getSalt());
            statement.setInt(5, user.getStatus().getIdentity());
            statement.setInt(6, user.getRole().getIdentity());

            if (user.getCompanyName() != null) {
                statement.setString(7, user.getCompanyName());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            if (user.getPhoneNumber() != -1) {
                statement.setLong(8, user.getPhoneNumber());
            } else {
                statement.setNull(8, Types.INTEGER);
            }
            if (user.getCompanyName() != null) {
                statement.setString(9, user.getCompanyName());
            } else {
                statement.setNull(9, Types.INTEGER);
            }

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincrement index after trying to add record into table `authors`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE `users` SET `login` = ?, `password_hash` = ?, " +
                "`salt` = ?, `status` = ?, `role` = ?, `company` = ?," +
                " `phoneNumber` = ?, `address` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
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
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {

            }
        }
    }

}
