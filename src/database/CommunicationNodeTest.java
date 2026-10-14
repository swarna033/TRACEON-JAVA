package main.BACKEND.src.database;

public class CommunicationNodeTest {

    public static void main(String[] args) {

        CommunicationNodeDAO.insertNode(
            "N01",
            "ACTIVE",
            "Heartbeat received",
            25
        );

        CommunicationNodeDAO.getNode("N01");
    }
}