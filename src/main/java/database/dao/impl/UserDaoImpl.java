package database.dao.impl;

import database.dao.UserDao;
import database.dao.entity.User;
import database.dao.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final ConnectionPool connectionPool;

    public UserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                final User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("fullName"));
                userList.add(user);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User getById(Long id) {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("fullName"));
            }
            resultSet.close();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (fullname) VALUES (?)")) {
            statement.setString(1, user.getFullName());
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User updateById(Long id, User user) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE USERS SET fullname=? WHERE ID=?")) {
            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE ID=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl(ConnectionPool.getConnectionPool());
        //System.out.println(userDao.getAll());
        //System.out.println(userDao.getById(3L));
        //System.out.println(userDao.createUser(new User(5l, "АЗАЗАЕВ ИДР")));
        System.out.println(userDao.getAll());
        //System.out.println(userDao.updateById(4l, new User(5l, "ГАмазов ЛЕша")));
        //System.out.println(userDao.createUser(new User(4l, "Сидоров Сергей")));
        System.out.println(userDao.getAll());

    }
}
