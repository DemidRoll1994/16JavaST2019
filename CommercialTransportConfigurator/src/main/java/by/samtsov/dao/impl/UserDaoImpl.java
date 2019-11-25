package by.samtsov.dao.impl;

import by.samtsov.bean.User;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.enums.UserStatus;
import by.samtsov.bean.exceptions.PersistentException;
import by.samtsov.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements Dao<User> {


    @Override
    public User get(int id) throws PersistentException {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address` FROM " +
                "`users` where `id` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
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
                long phoneNumber = resultSet.getLong("Phone_number");
                if (!resultSet.wasNull()) {
                    user.setPhoneNumber(phoneNumber);
                }
                String address = resultSet.getString("address");
                if (!resultSet.wasNull()) {
                    user.setAddress(address);
                }
            }
            return user;
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
    public List<User> getAll() throws PersistentException {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address` FROM " +
                "`users` ORDER BY `login`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
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
                long phoneNumber = resultSet.getLong("Phone_number");
                if (!resultSet.wasNull()) {
                    user.setPhoneNumber(phoneNumber);
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
        }


    }

    @Override
    public int add(User user) throws PersistentException {
        String sql = "INSERT INTO `users` (`login`, `password_hash`," +
                " `salt`, `status`, `role`,`company`, `Phone_number`," +
                "`address`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
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

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new PersistentException("There is no autoincrement index after trying to add record into table `users`");
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
    public void update(User user) throws PersistentException {
        String sql = "UPDATE `users` SET `login` = ?, `password_hash` = ?, " +
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
        }
    }

    @Override
    public void delete(int userId) throws PersistentException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
