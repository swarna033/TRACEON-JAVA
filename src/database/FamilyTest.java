package main.BACKEND.src.database;

public class FamilyTest {

    public static void main(String[] args) {

        FamilyDAO.insertFamily(
            "FAM-001",
            "H-101",
            "Sample Family",
            "Zone A"
        );

        FamilyDAO.getFamily("FAM-001");
    }
}