package model;

public class SOSAlert extends EmergencyAlert {

    private String emergencyType;

    public SOSAlert(String alertId, String familyId, String houseId,
                    String priority, String status, String emergencyType) {

        super(alertId, familyId, houseId, priority, status);
        this.emergencyType = emergencyType;
    }

    @Override
    public void processAlert() {
        System.out.println("SOS Alert processed as HIGH PRIORITY emergency.");
    }

    public void displaySOSAlert() {
        displayAlert();
        System.out.println("Emergency Type : " + emergencyType);
    }
}