package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao extends SQLBaseDao implements UserDao {


    @Override
    public User get(int id) throws PersistenceException {
        String sql = "SELECT `id`, `login`,`password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address`," +
                " `email`, `name`, `surname` FROM `users` where `id` =?";
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
                user.setStatus(UserStatus.getByIdentity(resultSet.getInt("status")));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setEmail(resultSet.getString("email"));
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
                String name = resultSet.getString("name");
                if (!resultSet.wasNull()) {
                    user.setName(name);
                }
                String surname = resultSet.getString("surname");
                if (!resultSet.wasNull()) {
                    user.setSurname(surname);
                }
            }
            return user;
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
    public List<User> getAll() throws PersistenceException {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address`, " +
                "`email`, `name`, `surname` FROM `users` ORDER BY `login`";
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
                user.setStatus(UserStatus.getByIdentity(resultSet.getInt("status")));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setEmail(resultSet.getString("email"));
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
                String name = resultSet.getString("name");
                if (!resultSet.wasNull()) {
                    user.setName(name);
                }
                String surname = resultSet.getString("surname");
                if (!resultSet.wasNull()) {
                    user.setSurname(surname);
                }
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }


    }

    @Override
    public int add(User user) throws PersistenceException {
        String sql = "INSERT INTO `users` (`login`, `password_hash`," +
                " `salt`, `status`, `role`,`company`, `Phone_number`," +
                "`address`, `email`, `name`, `surname`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            statement.setString(9, user.getEmail());
            statement.setString(10, user.getName());
            statement.setString(11, user.getSurname());
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
            }
        }
    }

    @Override
    public int update(User user) throws PersistenceException {
        String sql = "UPDATE `users` SET `login` = ?, `password_hash` = ?, " +
                "`salt` = ?, `status` = ?, `role` = ?, `company` = ?," +
                " `phoneNumber` = ?, `address` = ?, `email` = ?, `name` = ?," +
                " `surname` = ? WHERE `id` = ?";
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
            statement.setString(9, user.getEmail());
            statement.setString(10, user.getName());
            statement.setString(11, user.getSurname());
            statement.setInt(12, user.getId());
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

    @Override
    public int delete(int userId) throws PersistenceException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
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

    @Override
    public User getByLogin(String login) throws PersistenceException {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`, `address`," +
                " `email`, `name`, `surname` FROM `users` where `login` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setSalt(resultSet.getString("salt"));
                user.setStatus(UserStatus.getByIdentity(resultSet.getInt("status")));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setEmail(resultSet.getString("email"));
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
                String name = resultSet.getString("name");
                if (!resultSet.wasNull()) {
                    user.setName(name);
                }
                String surname = resultSet.getString("surname");
                if (!resultSet.wasNull()) {
                    user.setSurname(surname);
                }
            }
            return user;
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
    public User getByEmail(String email) throws PersistenceException {
        String sql = "SELECT `id`, `login`, `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`, `address`," +
                " `email`, `name`, `surname` FROM `users` where `email` =?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPasswordHash(resultSet.getString("password_hash"));
                user.setSalt(resultSet.getString("salt"));
                user.setStatus(UserStatus.getByIdentity(resultSet.getInt("status")));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                user.setEmail(resultSet.getString("email"));
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
                String name = resultSet.getString("name");
                if (!resultSet.wasNull()) {
                    user.setName(name);
                }
                String surname = resultSet.getString("surname");
                if (!resultSet.wasNull()) {
                    user.setSurname(surname);
                }
            }
            return user;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
            }
        }
    }
}
