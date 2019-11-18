package by.samtsov.dao.impl;

import by.samtsov.bean.User;
import by.samtsov.bean.enums.Role;
import by.samtsov.bean.enums.UserStatus;
import by.samtsov.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDaoImpl implements Dao<User> {


    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
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
                user.setCompanyName(resultSet.getString("company"));
                user.setPhoneNumber(resultSet.getLong("Phone_number"));
                user.setAddress(resultSet.getString("address"));
//todo а если из sql придет пустое значение??? все упадет!!
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
    public void add(User user) {
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
            statement.setInt(4, UserStatus.getValue(user.getStatus()));
            statement.setInt(4, Role.getValue(user.getRole()));
            statement.setString(4, user.getCompanyName());
            statement.setLong(4, user.getPhoneNumber());
            statement.setString(4, user.getAddress());
              
            if (author.getYearOfDeath() != null) {
                statement.setInt(5, author.getYearOfDeath());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `authors`");
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
    public void update(User user, String[] params) {
        user.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(
                params[1], "Email cannot be null"));
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
