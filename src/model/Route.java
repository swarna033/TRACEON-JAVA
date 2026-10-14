package model;

public class Route {

    private String routeId;
    private String sourceNode;
    private String destinationNode;
    private int distance;
    private String status;

    public Route(String routeId, String sourceNode, String destinationNode,
                 int distance, String status) {
        this.routeId = routeId;
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.distance = distance;
        this.status = status;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getSourceNode() {
        return sourceNode;
    }

    public String getDestinationNode() {
        return destinationNode;
    }

    public int getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }

    public void displayRoute() {
        System.out.println("Route ID       : " + routeId);
        System.out.println("Source Node    : " + sourceNode);
        System.out.println("Destination    : " + destinationNode);
        System.out.println("Distance       : " + distance + " m");
        System.out.println("Status         : " + status);
    }
}