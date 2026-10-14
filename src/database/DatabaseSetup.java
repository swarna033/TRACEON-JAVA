package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {

        String familyTable = """
            CREATE TABLE IF NOT EXISTS family (
                family_id TEXT PRIMARY KEY,
                house_id TEXT,
                family_name TEXT,
                location TEXT
            )
            """;

        String alertTable = """
            CREATE TABLE IF NOT EXISTS emergency_alert (
                alert_id TEXT PRIMARY KEY,
                family_id TEXT,
                house_id TEXT,
                alert_type TEXT,
                priority TEXT,
                status TEXT
            )
            """;

        String nodeTable = """
            CREATE TABLE IF NOT EXISTS communication_node (
                node_id TEXT PRIMARY KEY,
                status TEXT,
                last_message TEXT,
                messages_forwarded INTEGER
            )
            """;

        String routeTable = """
            CREATE TABLE IF NOT EXISTS route (
                route_id TEXT PRIMARY KEY,
                source_node TEXT,
                destination_node TEXT,
                distance REAL,
                status TEXT
            )
            """;

        String rescueTable = """
            CREATE TABLE IF NOT EXISTS rescue_case (
                case_id TEXT PRIMARY KEY,
                alert_id TEXT,
                family_id TEXT,
                rescue_team TEXT,
                status TEXT,
                progress INTEGER
            )
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(familyTable);
            statement.execute(alertTable);
            statement.execute(nodeTable);
            statement.execute(routeTable);
            statement.execute(rescueTable);

            System.out.println("All database tables created successfully!");

        } catch (Exception e) {
            System.out.println("Table creation failed!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTables();
    }
}