import database.DatabaseSetup;
import database.FamilyDAO;
import database.EmergencyAlertDAO;
import database.CommunicationNodeDAO;
import database.RouteDAO;
import database.RescueCaseDAO;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("        TRACEON DISASTER SYSTEM");
        System.out.println("======================================");

        // Create database tables
        DatabaseSetup.createTables();

        System.out.println();

        // 1. Family Management
        System.out.println("===== FAMILY MANAGEMENT =====");

        FamilyDAO.insertFamily(
            "FAM-001",
            "H-101",
            "Sample Family",
            "Zone A"
        );

        FamilyDAO.getFamily("FAM-001");

        System.out.println();

        // 2. Emergency Alert Management
        System.out.println("===== EMERGENCY ALERT =====");

        // SOS - HIGH priority
        EmergencyAlertDAO.insertAlert(
            "SOS-001",
            "FAM-001",
            "H-101",
            "SOS",
            "HIGH",
            "Pending"
        );

        // HELP - MEDIUM priority
        EmergencyAlertDAO.insertAlert(
            "HELP-001",
            "FAM-001",
            "H-101",
            "HELP",
            "MEDIUM",
            "Pending"
        );

        EmergencyAlertDAO.getAlert("SOS-001");
        EmergencyAlertDAO.getAlert("HELP-001");

        System.out.println();

        // 3. Communication Node Monitoring
        System.out.println("===== COMMUNICATION NODE =====");

        CommunicationNodeDAO.insertNode(
            "N01",
            "ACTIVE",
            "Heartbeat received",
            25
        );

        CommunicationNodeDAO.getNode("N01");

        System.out.println();

        // 4. Route Monitoring
        System.out.println("===== ROUTE MONITORING =====");

        RouteDAO.insertRoute(
            "R01",
            "N01",
            "N02",
            10.0,
            "AVAILABLE"
        );

        RouteDAO.getRoute("R01");

        System.out.println();

        // 5. Rescue Tracking
        System.out.println("===== RESCUE TRACKING =====");

        RescueCaseDAO.insertRescueCase(
            "CASE-001",
            "SOS-001",
            "FAM-001",
            "Rescue Team A",
            "Assigned",
            25
        );

        RescueCaseDAO.getRescueCase("CASE-001");

        System.out.println();
        System.out.println("======================================");
        System.out.println("     TRACEON SYSTEM COMPLETED");
        System.out.println("======================================");
    }
}