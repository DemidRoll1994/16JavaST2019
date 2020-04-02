package by.samtsov.dao.mysqlimpl;

import by.samtsov.bean.entity.User;
import by.samtsov.bean.type.Role;
import by.samtsov.bean.type.UserStatus;
import by.samtsov.dao.PersistenceException;
import by.samtsov.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao extends SQLBaseDao implements UserDao {

    private static final Logger logger = LogManager.getLogger(
            SQLUserDao.class);

    @Override
    public User get(int id) throws PersistenceException {
        String sql = "SELECT `id`, `password_hash`, `salt`, " +
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
        String sql = "SELECT `id`,  `password_hash`, `salt`, " +
                "`status`, `role`,`company`,`Phone_number`,`address`, " +
                "`email`, `name`, `surname` FROM `users` ORDER BY `email`";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<User> users = new ArrayList<>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
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
        String sql = "INSERT INTO `users` (`password_hash`," +
                " `salt`, `status`, `role`,`company`, `Phone_number`," +
                "`address`, `email`, `name`, `surname`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql
                , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getPasswordHash());
            statement.setString(2, user.getSalt());
            statement.setInt(3, user.getStatus().getIdentity());
            statement.setInt(4, user.getRole().getIdentity());

            if (user.getCompanyName() != null) {
                statement.setString(5, user.getCompanyName());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            if (user.getPhoneNumber() != -1) {
                statement.setLong(6, user.getPhoneNumber());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (user.getCompanyName() != null) {
                statement.setString(7, user.getCompanyName());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getName());
            statement.setString(10, user.getSurname());
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
    public void update(User user) throws PersistenceException {
        String sql = "UPDATE `users` SET `password_hash` = ?, " +
                "`salt` = ?, `status` = ?, `role` = ?, `company` = ?," +
                " `phone_Number` = ?, `address` = ?, `email` = ?, `name` = ?," +
                " `surname` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPasswordHash());
            statement.setString(2, user.getSalt());
            statement.setInt(3, user.getStatus().getIdentity());
            statement.setInt(4, user.getRole().getIdentity());
            if (user.getCompanyName() != null) {
                statement.setString(5, user.getCompanyName());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            if (user.getPhoneNumber() != -1) {
                statement.setLong(6, user.getPhoneNumber());
            } else {
                statement.setNull(6, Types.INTEGER);
            }
            if (user.getCompanyName() != null) {
                statement.setString(7, user.getAddress());
            } else {
                statement.setNull(7, Types.INTEGER);
            }
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getName());
            statement.setString(10, user.getSurname());
            statement.setInt(11, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
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


    @Override
    public User findByEmail(String email) throws PersistenceException {
        String sql = "SELECT `id`,  `password_hash`, `salt`, " +
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
                throw new PersistenceException(e);
            }
        }
    }
}
