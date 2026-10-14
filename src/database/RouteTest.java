package main.BACKEND.src.database;

public class RouteTest {

    public static void main(String[] args) {

        RouteDAO.insertRoute(
            "R01",
            "N01",
            "N02",
            10.0,
            "AVAILABLE"
        );

        RouteDAO.getRoute("R01");
    }
}