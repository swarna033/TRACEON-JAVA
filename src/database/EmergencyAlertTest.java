package main.BACKEND.src.database;

public class EmergencyAlertTest {

    public static void main(String[] args) {

        // SOS Alert - HIGH priority
        EmergencyAlertDAO.insertAlert(
            "SOS-001",
            "FAM-001",
            "H-101",
            "SOS",
            "HIGH",
            "Pending"
        );

        // HELP Alert - MEDIUM priority
        EmergencyAlertDAO.insertAlert(
            "HELP-001",
            "FAM-001",
            "H-101",
            "HELP",
            "MEDIUM",
            "Pending"
        );

        // Read SOS
        EmergencyAlertDAO.getAlert("SOS-001");

        // Read HELP
        EmergencyAlertDAO.getAlert("HELP-001");
    }
}