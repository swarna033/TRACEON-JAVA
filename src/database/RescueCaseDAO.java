package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RescueCaseDAO {

    // INSERT RESCUE CASE
    public static void insertRescueCase(
            String caseId,
            String alertId,
            String familyId,
            String rescueTeam,
            String status,
            int progress) {

        String sql = """
            INSERT OR REPLACE INTO rescue_case
            (case_id, alert_id, family_id, rescue_team, status, progress)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, caseId);
            statement.setString(2, alertId);
            statement.setString(3, familyId);
            statement.setString(4, rescueTeam);
            statement.setString(5, status);
            statement.setInt(6, progress);

            statement.executeUpdate();

            System.out.println("Rescue case saved to database!");

        } catch (Exception e) {

            System.out.println("Rescue case insertion failed!");
            e.printStackTrace();
        }
    }

    // READ RESCUE CASE
    public static void getRescueCase(String caseId) {

        String sql = "SELECT * FROM rescue_case WHERE case_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, caseId);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("===== RESCUE CASE FROM DATABASE =====");

                System.out.println("Case ID      : "
                        + result.getString("case_id"));

                System.out.println("Alert ID     : "
                        + result.getString("alert_id"));

                System.out.println("Family ID    : "
                        + result.getString("family_id"));

                System.out.println("Rescue Team  : "
                        + result.getString("rescue_team"));

                System.out.println("Status       : "
                        + result.getString("status"));

                System.out.println("Progress     : "
                        + result.getInt("progress") + "%");

            } else {

                System.out.println("Rescue case not found!");
            }

        } catch (Exception e) {

            System.out.println("Rescue case retrieval failed!");
            e.printStackTrace();
        }
    }
}