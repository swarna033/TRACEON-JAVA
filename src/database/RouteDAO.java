package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RouteDAO {

    // INSERT ROUTE
    public static void insertRoute(
            String routeId,
            String sourceNode,
            String destinationNode,
            double distance,
            String status) {

        String sql = """
            INSERT OR REPLACE INTO route
            (route_id, source_node, destination_node, distance, status)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, routeId);
            statement.setString(2, sourceNode);
            statement.setString(3, destinationNode);
            statement.setDouble(4, distance);
            statement.setString(5, status);

            statement.executeUpdate();

            System.out.println("Route saved to database!");

        } catch (Exception e) {

            System.out.println("Route insertion failed!");
            e.printStackTrace();
        }
    }

    // READ ROUTE
    public static void getRoute(String routeId) {

        String sql = "SELECT * FROM route WHERE route_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, routeId);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("===== ROUTE FROM DATABASE =====");

                System.out.println("Route ID       : "
                        + result.getString("route_id"));

                System.out.println("Source Node    : "
                        + result.getString("source_node"));

                System.out.println("Destination    : "
                        + result.getString("destination_node"));

                System.out.println("Distance       : "
                        + result.getDouble("distance") + " m");

                System.out.println("Status         : "
                        + result.getString("status"));

            } else {

                System.out.println("Route not found!");
            }

        } catch (Exception e) {

            System.out.println("Route retrieval failed!");
            e.printStackTrace();
        }
    }
}