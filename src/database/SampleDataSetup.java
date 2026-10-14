package main.BACKEND.src.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SampleDataSetup {

    public static void insertSampleData() {

        try (Connection connection = DatabaseConnection.getConnection()) {

            // =====================================================
            // FAMILY DATA
            // =====================================================

            String familySql = """
                INSERT OR IGNORE INTO family
                (family_id, house_id, family_name, location)
                VALUES (?, ?, ?, ?)
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(familySql)) {

                insertFamily(
                        ps,
                        "FAM-001",
                        "H-101",
                        "Arun Family",
                        "Zone A"
                );

                insertFamily(
                        ps,
                        "FAM-002",
                        "H-102",
                        "Kumar Family",
                        "Zone B"
                );

                insertFamily(
                        ps,
                        "FAM-003",
                        "H-103",
                        "Priya Family",
                        "Zone C"
                );

                insertFamily(
                        ps,
                        "FAM-004",
                        "H-104",
                        "Ravi Family",
                        "Zone D"
                );

                insertFamily(
                        ps,
                        "FAM-005",
                        "H-105",
                        "Meena Family",
                        "Zone E"
                );
            }


            // =====================================================
            // ALERT DATA
            // =====================================================

            String alertSql = """
                INSERT OR IGNORE INTO emergency_alert
                (alert_id, family_id, house_id, alert_type, priority, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(alertSql)) {

                insertAlert(
                        ps,
                        "SOS-001",
                        "FAM-001",
                        "H-101",
                        "SOS",
                        "HIGH",
                        "Pending"
                );

                insertAlert(
                        ps,
                        "SOS-002",
                        "FAM-002",
                        "H-102",
                        "SOS",
                        "HIGH",
                        "Assigned"
                );

                insertAlert(
                        ps,
                        "SOS-003",
                        "FAM-003",
                        "H-103",
                        "SOS",
                        "HIGH",
                        "Completed"
                );

                insertAlert(
                        ps,
                        "HELP-001",
                        "FAM-004",
                        "H-104",
                        "HELP",
                        "MEDIUM",
                        "Pending"
                );

                insertAlert(
                        ps,
                        "HELP-002",
                        "FAM-005",
                        "H-105",
                        "HELP",
                        "MEDIUM",
                        "Completed"
                );

                insertAlert(
                        ps,
                        "HELP-003",
                        "FAM-002",
                        "H-102",
                        "HELP",
                        "MEDIUM",
                        "Assigned"
                );
            }


            // =====================================================
            // NODE DATA
            // =====================================================

            String nodeSql = """
                INSERT OR IGNORE INTO communication_node
                (node_id, status, last_message, messages_forwarded)
                VALUES (?, ?, ?, ?)
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(nodeSql)) {

                insertNode(
                        ps,
                        "N01",
                        "ACTIVE",
                        "Communication received",
                        25
                );

                insertNode(
                        ps,
                        "N02",
                        "ACTIVE",
                        "Communication received",
                        31
                );

                insertNode(
                        ps,
                        "N03",
                        "ACTIVE",
                        "Communication received",
                        28
                );

                insertNode(
                        ps,
                        "N04",
                        "ACTIVE",
                        "Communication received",
                        19
                );

                insertNode(
                        ps,
                        "N05",
                        "ACTIVE",
                        "Communication received",
                        22
                );
            }


            // =====================================================
            // ROUTE DATA
            // IMPORTANT:
            // Distance is stored in METRES.
            // All routes are between 2 KM and 3 KM.
            // =====================================================

            String routeSql = """
                INSERT OR IGNORE INTO route
                (route_id, source_node, destination_node, distance, status)
                VALUES (?, ?, ?, ?, ?)
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(routeSql)) {

                // 2.3 km
                insertRoute(
                        ps,
                        "R01",
                        "N01",
                        "N02",
                        2300,
                        "AVAILABLE"
                );

                // 2.7 km
                insertRoute(
                        ps,
                        "R02",
                        "N02",
                        "N03",
                        2700,
                        "AVAILABLE"
                );

                // 2.4 km
                insertRoute(
                        ps,
                        "R03",
                        "N03",
                        "N04",
                        2400,
                        "AVAILABLE"
                );

                // 2.9 km
                insertRoute(
                        ps,
                        "R04",
                        "N04",
                        "N05",
                        2900,
                        "AVAILABLE"
                );

                // 2.6 km
                insertRoute(
                        ps,
                        "R05",
                        "N01",
                        "N03",
                        2600,
                        "AVAILABLE"
                );

                // 2.2 km
                insertRoute(
                        ps,
                        "R06",
                        "N02",
                        "N05",
                        2200,
                        "BLOCKED"
                );
            }


            // =====================================================
            // IMPORTANT:
            // UPDATE EXISTING ROUTES
            //
            // INSERT OR IGNORE will not change old route values.
            // So we explicitly update the existing routes here.
            // =====================================================

            String updateRouteSql = """
                UPDATE route
                SET distance = ?, status = ?
                WHERE route_id = ?
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(updateRouteSql)) {

                updateRoute(
                        ps,
                        "R01",
                        2300,
                        "AVAILABLE"
                );

                updateRoute(
                        ps,
                        "R02",
                        2700,
                        "AVAILABLE"
                );

                updateRoute(
                        ps,
                        "R03",
                        2400,
                        "AVAILABLE"
                );

                updateRoute(
                        ps,
                        "R04",
                        2900,
                        "AVAILABLE"
                );

                updateRoute(
                        ps,
                        "R05",
                        2600,
                        "AVAILABLE"
                );

                updateRoute(
                        ps,
                        "R06",
                        2200,
                        "BLOCKED"
                );
            }


            // =====================================================
            // RESCUE DATA
            // =====================================================

            String rescueSql = """
                INSERT OR IGNORE INTO rescue_case
                (case_id, alert_id, family_id, rescue_team, status, progress)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

            try (PreparedStatement ps =
                         connection.prepareStatement(rescueSql)) {

                insertRescue(
                        ps,
                        "CASE-001",
                        "SOS-001",
                        "FAM-001",
                        "Rescue Team A",
                        "Assigned",
                        25
                );

                insertRescue(
                        ps,
                        "CASE-002",
                        "SOS-002",
                        "FAM-002",
                        "Rescue Team B",
                        "In Progress",
                        60
                );

                insertRescue(
                        ps,
                        "CASE-003",
                        "SOS-003",
                        "FAM-003",
                        "Rescue Team C",
                        "Completed",
                        100
                );

                insertRescue(
                        ps,
                        "CASE-004",
                        "HELP-002",
                        "FAM-005",
                        "Rescue Team D",
                        "Completed",
                        100
                );
            }


            // =====================================================
            // SUCCESS MESSAGE
            // =====================================================

            System.out.println();
            System.out.println("======================================");
            System.out.println("TRACEON SAMPLE DATA UPDATED");
            System.out.println("======================================");

            System.out.println("Families : 5");
            System.out.println("Alerts   : 6");
            System.out.println("Nodes    : 5");
            System.out.println("Routes   : 6");
            System.out.println("Rescue   : 4");

            System.out.println();
            System.out.println("ROUTE DISTANCES");
            System.out.println("--------------------------------------");
            System.out.println("R01 : 2.3 km");
            System.out.println("R02 : 2.7 km");
            System.out.println("R03 : 2.4 km");
            System.out.println("R04 : 2.9 km");
            System.out.println("R05 : 2.6 km");
            System.out.println("R06 : 2.2 km");
            System.out.println("--------------------------------------");

            System.out.println("All routes are between 2 and 3 km.");
            System.out.println("======================================");

        } catch (Exception e) {

            System.out.println(
                    "Sample data insertion/update failed!"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // INSERT FAMILY
    // =========================================================

    private static void insertFamily(
            PreparedStatement ps,
            String familyId,
            String houseId,
            String familyName,
            String location) throws Exception {

        ps.setString(1, familyId);
        ps.setString(2, houseId);
        ps.setString(3, familyName);
        ps.setString(4, location);

        ps.executeUpdate();
    }


    // =========================================================
    // INSERT ALERT
    // =========================================================

    private static void insertAlert(
            PreparedStatement ps,
            String alertId,
            String familyId,
            String houseId,
            String alertType,
            String priority,
            String status) throws Exception {

        ps.setString(1, alertId);
        ps.setString(2, familyId);
        ps.setString(3, houseId);
        ps.setString(4, alertType);
        ps.setString(5, priority);
        ps.setString(6, status);

        ps.executeUpdate();
    }


    // =========================================================
    // INSERT NODE
    // =========================================================

    private static void insertNode(
            PreparedStatement ps,
            String nodeId,
            String status,
            String lastMessage,
            int messagesForwarded) throws Exception {

        ps.setString(1, nodeId);
        ps.setString(2, status);
        ps.setString(3, lastMessage);
        ps.setInt(4, messagesForwarded);

        ps.executeUpdate();
    }


    // =========================================================
    // INSERT ROUTE
    // =========================================================

    private static void insertRoute(
            PreparedStatement ps,
            String routeId,
            String source,
            String destination,
            double distance,
            String status) throws Exception {

        ps.setString(1, routeId);
        ps.setString(2, source);
        ps.setString(3, destination);
        ps.setDouble(4, distance);
        ps.setString(5, status);

        ps.executeUpdate();
    }


    // =========================================================
    // UPDATE EXISTING ROUTE
    // =========================================================

    private static void updateRoute(
            PreparedStatement ps,
            String routeId,
            double distance,
            String status) throws Exception {

        ps.setDouble(1, distance);
        ps.setString(2, status);
        ps.setString(3, routeId);

        ps.executeUpdate();
    }


    // =========================================================
    // INSERT RESCUE
    // =========================================================

    private static void insertRescue(
            PreparedStatement ps,
            String caseId,
            String alertId,
            String familyId,
            String rescueTeam,
            String status,
            int progress) throws Exception {

        ps.setString(1, caseId);
        ps.setString(2, alertId);
        ps.setString(3, familyId);
        ps.setString(4, rescueTeam);
        ps.setString(5, status);
        ps.setInt(6, progress);

        ps.executeUpdate();
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        DatabaseSetup.createTables();

        insertSampleData();
    }

}