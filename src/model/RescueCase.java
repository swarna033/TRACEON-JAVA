package model;

public class RescueCase {

    private String caseId;
    private String alertId;
    private String familyId;
    private String rescueTeam;
    private String status;
    private int progress;

    public RescueCase(String caseId, String alertId, String familyId,
                      String rescueTeam, String status, int progress) {
        this.caseId = caseId;
        this.alertId = alertId;
        this.familyId = familyId;
        this.rescueTeam = rescueTeam;
        this.status = status;
        this.progress = progress;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getAlertId() {
        return alertId;
    }

    public String getFamilyId() {
        return familyId;
    }

    public String getRescueTeam() {
        return rescueTeam;
    }

    public String getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public void updateProgress(int progress, String status) {
        this.progress = progress;
        this.status = status;
    }

    public void displayRescueCase() {
        System.out.println("Case ID      : " + caseId);
        System.out.println("Alert ID     : " + alertId);
        System.out.println("Family ID    : " + familyId);
        System.out.println("Rescue Team  : " + rescueTeam);
        System.out.println("Status       : " + status);
        System.out.println("Progress     : " + progress + "%");
    }
}