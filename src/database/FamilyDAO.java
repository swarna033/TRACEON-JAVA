package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FamilyDAO {

    // INSERT FAMILY
    public static void insertFamily(
            String familyId,
            String houseId,
            String familyName,
            String location) {

        String sql = """
            INSERT OR REPLACE INTO family
            (family_id, house_id, family_name, location)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, familyId);
            statement.setString(2, houseId);
            statement.setString(3, familyName);
            statement.setString(4, location);

            statement.executeUpdate();

            System.out.println("Family saved to database!");

        } catch (Exception e) {
            System.out.println("Family insertion failed!");
            e.printStackTrace();
        }
    }

    // READ FAMILY
    public static void getFamily(String familyId) {

        String sql = "SELECT * FROM family WHERE family_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, familyId);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("===== FAMILY FROM DATABASE =====");

                System.out.println(
                    "Family ID   : " +
                    result.getString("family_id")
                );

                System.out.println(
                    "House ID    : " +
                    result.getString("house_id")
                );

                System.out.println(
                    "Family Name : " +
                    result.getString("family_name")
                );

                System.out.println(
                    "Location    : " +
                    result.getString("location")
                );

            } else {

                System.out.println("Family not found!");
            }

        } catch (Exception e) {

            System.out.println("Family retrieval failed!");
            e.printStackTrace();
        }
    }
}