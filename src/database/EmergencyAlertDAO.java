package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmergencyAlertDAO {

    // INSERT ALERT
    public static void insertAlert(
            String alertId,
            String familyId,
            String houseId,
            String alertType,
            String priority,
            String status) {

        String sql = """
            INSERT OR REPLACE INTO emergency_alert
            (alert_id, family_id, house_id, alert_type, priority, status)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, alertId);
            statement.setString(2, familyId);
            statement.setString(3, houseId);
            statement.setString(4, alertType);
            statement.setString(5, priority);
            statement.setString(6, status);

            statement.executeUpdate();

            System.out.println("Emergency alert saved to database!");

        } catch (Exception e) {

            System.out.println("Alert insertion failed!");
            e.printStackTrace();
        }
    }

    // READ ALERT
    public static void getAlert(String alertId) {

        String sql = "SELECT * FROM emergency_alert WHERE alert_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, alertId);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("===== ALERT FROM DATABASE =====");

                System.out.println("Alert ID  : "
                        + result.getString("alert_id"));

                System.out.println("Family ID : "
                        + result.getString("family_id"));

                System.out.println("House ID  : "
                        + result.getString("house_id"));

                System.out.println("Alert Type: "
                        + result.getString("alert_type"));

                System.out.println("Priority  : "
                        + result.getString("priority"));

                System.out.println("Status    : "
                        + result.getString("status"));

            } else {

                System.out.println("Alert not found!");
            }

        } catch (Exception e) {

            System.out.println("Alert retrieval failed!");
            e.printStackTrace();
        }
    }
}