package model;

public class Family {

    private String familyId;
    private String houseId;
    private String familyName;
    private String location;

    public Family(String familyId, String houseId, String familyName, String location) {
        this.familyId = familyId;
        this.houseId = houseId;
        this.familyName = familyName;
        this.location = location;
    }

    public String getFamilyId() {
        return familyId;
    }

    public String getHouseId() {
        return houseId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getLocation() {
        return location;
    }

    public void displayFamily() {
        System.out.println("Family ID   : " + familyId);
        System.out.println("House ID    : " + houseId);
        System.out.println("Family Name : " + familyName);
        System.out.println("Location    : " + location);
    }
}javac -d . src\model\CommunicationNode.java src\model\EmergencyAlert.java src\model\Family.java src\model\HelpAlert.java src\model\RescueCase.java src\model\Route.java src\model\SOSAlert.java Main.java