package model;

public class CommunicationNode {

    private String nodeId;
    private String status;
    private String lastMessage;
    private int messagesForwarded;

    public CommunicationNode(String nodeId, String status,
                             String lastMessage, int messagesForwarded) {
        this.nodeId = nodeId;
        this.status = status;
        this.lastMessage = lastMessage;
        this.messagesForwarded = messagesForwarded;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getStatus() {
        return status;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getMessagesForwarded() {
        return messagesForwarded;
    }

    public void displayNode() {
        System.out.println("Node ID            : " + nodeId);
        System.out.println("Status             : " + status);
        System.out.println("Last Message       : " + lastMessage);
        System.out.println("Messages Forwarded : " + messagesForwarded);
    }
}