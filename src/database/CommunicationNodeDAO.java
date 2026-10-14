package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CommunicationNodeDAO {

    // INSERT NODE
    public static void insertNode(
            String nodeId,
            String status,
            String lastMessage,
            int messagesForwarded) {

        String sql = """
            INSERT OR REPLACE INTO communication_node
            (node_id, status, last_message, messages_forwarded)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nodeId);
            statement.setString(2, status);
            statement.setString(3, lastMessage);
            statement.setInt(4, messagesForwarded);

            statement.executeUpdate();

            System.out.println("Communication node saved to database!");

        } catch (Exception e) {

            System.out.println("Node insertion failed!");
            e.printStackTrace();
        }
    }

    // READ NODE
    public static void getNode(String nodeId) {

        String sql = "SELECT * FROM communication_node WHERE node_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nodeId);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("===== NODE FROM DATABASE =====");

                System.out.println("Node ID            : "
                        + result.getString("node_id"));

                System.out.println("Status             : "
                        + result.getString("status"));

                System.out.println("Last Message       : "
                        + result.getString("last_message"));

                System.out.println("Messages Forwarded : "
                        + result.getInt("messages_forwarded"));

            } else {

                System.out.println("Node not found!");
            }

        } catch (Exception e) {

            System.out.println("Node retrieval failed!");
            e.printStackTrace();
        }
    }
}