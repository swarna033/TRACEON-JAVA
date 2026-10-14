package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:traceon.db";

    public static Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection connection =
                    DriverManager.getConnection(URL);

            System.out.println("Database connected successfully!");

            return connection;

        } catch (Exception e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();

            return null;
        }
    }
}