package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DemoData {

    public static void main(String[] args) {

        insertNodes();
        insertRoutes();

        System.out.println();
        System.out.println("================================");
        System.out.println(" TRACEON DEMO NETWORK CREATED");
        System.out.println(" Nodes  : N01, N02, N03, N04");
        System.out.println(" Routes : R01, R02, R03, R04");
        System.out.println("================================");
    }

    // ==============================
    // INSERT COMMUNICATION NODES
    // ==============================

    private static void insertNodes() {

        String sql = """
            INSERT OR IGNORE INTO communication_node
            (node_id, status, last_message, messages_forwarded)
            VALUES (?, ?, ?, ?)
            """;

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            addNode(statement, "N01", "ACTIVE",
                    "Heartbeat received", 25);

            addNode(statement, "N02", "ACTIVE",
                    "SOS forwarded", 18);

            addNode(statement, "N03", "ACTIVE",
                    "Heartbeat received", 31);

            addNode(statement, "N04", "ACTIVE",
                    "Rescue message received", 14);

            System.out.println("Communication nodes inserted successfully!");

        } catch (Exception e) {

            System.out.println("Node insertion failed!");
            e.printStackTrace();
        }
    }

    private static void addNode(
            PreparedStatement statement,
            String nodeId,
            String status,
            String lastMessage,
            int messagesForwarded
    ) throws Exception {

        statement.setString(1, nodeId);
        statement.setString(2, status);
        statement.setString(3, lastMessage);
        statement.setInt(4, messagesForwarded);

        statement.executeUpdate();
    }

    // ==============================
    // INSERT ROUTES
    // ==============================

    private static void insertRoutes() {

        String sql = """
            INSERT OR IGNORE INTO route
            (route_id, source_node, destination_node, distance, status)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)
        ) {

            addRoute(statement,
                    "R01", "N01", "N02", 10, "AVAILABLE");

            addRoute(statement,
                    "R02", "N01", "N03", 12, "AVAILABLE");

            addRoute(statement,
                    "R03", "N02", "N04", 15, "AVAILABLE");

            addRoute(statement,
                    "R04", "N03", "N04", 11, "AVAILABLE");

            System.out.println("Communication routes inserted successfully!");

        } catch (Exception e) {

            System.out.println("Route insertion failed!");
            e.printStackTrace();
        }
    }

    private static void addRoute(
            PreparedStatement statement,
            String routeId,
            String sourceNode,
            String destinationNode,
            double distance,
            String status
    ) throws Exception {

        statement.setString(1, routeId);
        statement.setString(2, sourceNode);
        statement.setString(3, destinationNode);
        statement.setDouble(4, distance);
        statement.setString(5, status);

        statement.executeUpdate();
    }
}