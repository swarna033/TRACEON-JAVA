package main.BACKEND.src.database;

public class RescueCaseTest {

    public static void main(String[] args) {

        RescueCaseDAO.insertRescueCase(
            "CASE-001",
            "SOS-001",
            "FAM-001",
            "Rescue Team A",
            "Assigned",
            25
        );

        RescueCaseDAO.getRescueCase("CASE-001");
    }
}