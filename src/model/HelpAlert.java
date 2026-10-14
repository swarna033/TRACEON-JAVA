package model;

public class HelpAlert extends EmergencyAlert {

    private String helpType;

    public HelpAlert(String alertId, String familyId, String houseId,
                     String priority, String status, String helpType) {

        super(alertId, familyId, houseId, priority, status);
        this.helpType = helpType;
    }

    @Override
    public void processAlert() {
        System.out.println("HELP Alert processed as MEDIUM PRIORITY assistance request.");
    }

    public void displayHelpAlert() {
        displayAlert();
        System.out.println("Help Type : " + helpType);
    }
}