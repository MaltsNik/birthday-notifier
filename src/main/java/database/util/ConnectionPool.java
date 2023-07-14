package database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final int MAX_CONNECTION = 10;
    private final String DRIVER_NAME = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private static ConnectionPool instance;
    private BlockingQueue<Connection> pool;

    private ConnectionPool() {
        pool = new ArrayBlockingQueue<>(MAX_CONNECTION);
        for (int i = 0; i < MAX_CONNECTION; i++) {
            try {
                pool.put(createConnection());
            } catch (InterruptedException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ConnectionPool getConnectionPool() {
        synchronized (ConnectionPool.class) {
            if (instance == null) {
                instance = new ConnectionPool();
            }
            return instance;
        }
    }

    public synchronized Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void returnConnection(Connection connection){
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
