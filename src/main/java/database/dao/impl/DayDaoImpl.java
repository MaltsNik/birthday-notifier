package database.dao.impl;

import database.dao.DayDao;
import database.dao.entity.Day;
import database.dao.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayDaoImpl implements DayDao {
    private final ConnectionPool connectionPool;

    public DayDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Day> getAll() {
        List<Day> dayLis = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DAYS");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                final Day day = new Day();
                day.setId(resultSet.getLong("id"));
                day.setDate(resultSet.getDate("day").toLocalDate());
                dayLis.add(day);
                connectionPool.returnConnection(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dayLis;
    }

    @Override
    public Day getById(Long id) {
        Day day = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM DAYS WHERE id = ? ")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                day = new Day();
                day.setId(resultSet.getLong("id"));
                day.setDate(resultSet.getDate("day").toLocalDate());
            }
            resultSet.close();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return day;
    }

    @Override
    public Day createDay(Day day) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO DAYS (day) VALUES (?)")) {
            statement.setDate(1, Date.valueOf(day.getDate()));
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return day;
    }

    @Override
    public Day updateById(Long id, Day day) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE DAYS SET DAY=? WHERE ID=?")) {
            statement.setDate(1, Date.valueOf(day.getDate()));
            statement.setLong(2, day.getId());
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return day;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM DAYS WHERE ID=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DayDaoImpl dayDao = new DayDaoImpl(ConnectionPool.getConnectionPool());
        //System.out.println(dayDao.getAll());
        //System.out.println(dayDao.getById(1l));
        //System.out.println(dayDao.createDay(new Day(3l,LocalDate.of(2011,11,9))));
       // dayDao.createDay(new Day(3l,LocalDate.of(2011,11,9)));
       // dayDao.updateById(3l,new Day(3l,LocalDate.of(2014,6,14)));
        dayDao.deleteById(3l);
        //System.out.println(dayDao.getAll());
        //System.out.println(dayDao.updateById(1l,new Day()));
    }
}
