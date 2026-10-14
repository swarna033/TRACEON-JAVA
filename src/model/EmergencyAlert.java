package model;

public class EmergencyAlert {

    protected String alertId;
    protected String familyId;
    protected String houseId;
    protected String priority;
    protected String status;

    public EmergencyAlert(String alertId, String familyId, String houseId,
                           String priority, String status) {
        this.alertId = alertId;
        this.familyId = familyId;
        this.houseId = houseId;
        this.priority = priority;
        this.status = status;
    }

    public void processAlert() {
        System.out.println("Processing emergency alert...");
    }

    public void displayAlert() {
        System.out.println("Alert ID  : " + alertId);
        System.out.println("Family ID : " + familyId);
        System.out.println("House ID  : " + houseId);
        System.out.println("Priority  : " + priority);
        System.out.println("Status    : " + status);
    }
}