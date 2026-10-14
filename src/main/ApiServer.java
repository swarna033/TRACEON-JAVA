package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import database.DatabaseConnection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Executors;

public class ApiServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(PORT), 0
        );

        server.createContext("/", ApiServer::handleHome);
        server.createContext("/alerts", ApiServer::handleAlertsPage);
        server.createContext("/families", ApiServer::handleFamiliesPage);
        server.createContext("/network", ApiServer::handleNetworkPage);
        server.createContext("/routes", ApiServer::handleRoutesPage);
        server.createContext("/rescue", ApiServer::handleRescuePage);
        server.createContext("/reports", ApiServer::handleReportsPage);

        server.createContext("/api/status", ApiServer::handleStatus);
        server.createContext("/api/alerts", ApiServer::handleAlerts);
        server.createContext("/api/family", ApiServer::handleFamilies);
        server.createContext("/api/nodes", ApiServer::handleNodes);
        server.createContext("/api/routes", ApiServer::handleRoutes);
        server.createContext("/api/rescue", ApiServer::handleRescue);

        server.createContext("/api/sos", ApiServer::handleSOS);

        server.setExecutor(
                Executors.newFixedThreadPool(10)
        );

        server.start();

        System.out.println("================================");
        System.out.println(" TRACEON JAVA BACKEND STARTED");
        System.out.println(" Dashboard: http://localhost:8080/");
        System.out.println(" Alerts:    http://localhost:8080/alerts");
        System.out.println(" Families:  http://localhost:8080/families");
        System.out.println(" Network:   http://localhost:8080/network");
        System.out.println(" Routes:    http://localhost:8080/routes");
        System.out.println(" Rescue:    http://localhost:8080/rescue");
        System.out.println(" Reports:   http://localhost:8080/reports");
        System.out.println(" API:       http://localhost:8080/api/status");
        System.out.println(" SOS API:   POST /api/sos");
        System.out.println("================================");
    }

    // =========================================================
    // HOME / DASHBOARD
    // =========================================================

    private static void handleHome(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Dashboard.html");
    }

    private static void handleAlertsPage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Alerts.html");
    }

    private static void handleFamiliesPage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Families.html");
    }

    private static void handleNetworkPage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Network.html");
    }

    private static void handleRoutesPage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Routes.html");
    }

    private static void handleRescuePage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Rescue.html");
    }

    private static void handleReportsPage(HttpExchange exchange)
            throws IOException {

        serveHtml(exchange, "Reports.html");
    }

    // =========================================================
    // HTML FILE SERVER
    // =========================================================

    private static void serveHtml(
            HttpExchange exchange,
            String fileName
    ) throws IOException {

        Path path = Path.of(
                "src",
                "main",
                fileName
        );

        if (!Files.exists(path)) {

            sendResponse(
                    exchange,
                    404,
                    "text/plain",
                    "Page not found: " + fileName
            );

            return;
        }

        byte[] content = Files.readAllBytes(path);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/html; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                200,
                content.length
        );

        try (OutputStream output =
                     exchange.getResponseBody()) {

            output.write(content);
        }
    }

    // =========================================================
    // API STATUS
    // =========================================================

    private static void handleStatus(
            HttpExchange exchange
    ) throws IOException {

        String json = """
                {
                  "system": "TRACEON",
                  "status": "ONLINE",
                  "database": "SQLite",
                  "backend": "Java"
                }
                """;

        sendJson(exchange, json);
    }

    // =========================================================
    // ALERTS API
    // =========================================================

    private static void handleAlerts(
            HttpExchange exchange
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("[");

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            String sql = """
                    SELECT
                        alert_id,
                        family_id,
                        house_id,
                        alert_type,
                        priority,
                        status
                    FROM emergency_alert
                    ORDER BY rowid DESC
                    """;

            try (Statement statement =
                         connection.createStatement();
                 ResultSet result =
                         statement.executeQuery(sql)) {

                boolean first = true;

                while (result.next()) {

                    if (!first) {
                        json.append(",");
                    }

                    first = false;

                    json.append("{");

                    json.append("\"alert_id\":\"")
                            .append(escapeJson(
                                    result.getString("alert_id")))
                            .append("\",");

                    json.append("\"family_id\":\"")
                            .append(escapeJson(
                                    result.getString("family_id")))
                            .append("\",");

                    json.append("\"house_id\":\"")
                            .append(escapeJson(
                                    result.getString("house_id")))
                            .append("\",");

                    json.append("\"alert_type\":\"")
                            .append(escapeJson(
                                    result.getString("alert_type")))
                            .append("\",");

                    json.append("\"priority\":\"")
                            .append(escapeJson(
                                    result.getString("priority")))
                            .append("\",");

                    json.append("\"status\":\"")
                            .append(escapeJson(
                                    result.getString("status")))
                            .append("\"");

                    json.append("}");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            json = new StringBuilder(
                    "{\"error\":\"Unable to load alerts\"}"
            );
        }

        json.append("]");

        sendJson(exchange, json.toString());
    }

    // =========================================================
    // FAMILY API
    // =========================================================

    private static void handleFamilies(
            HttpExchange exchange
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("[");

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            String sql = """
                    SELECT
                        family_id,
                        house_id,
                        family_name,
                        location
                    FROM family
                    ORDER BY rowid DESC
                    """;

            try (Statement statement =
                         connection.createStatement();
                 ResultSet result =
                         statement.executeQuery(sql)) {

                boolean first = true;

                while (result.next()) {

                    if (!first) {
                        json.append(",");
                    }

                    first = false;

                    json.append("{");

                    json.append("\"family_id\":\"")
                            .append(escapeJson(
                                    result.getString("family_id")))
                            .append("\",");

                    json.append("\"house_id\":\"")
                            .append(escapeJson(
                                    result.getString("house_id")))
                            .append("\",");

                    json.append("\"family_name\":\"")
                            .append(escapeJson(
                                    result.getString("family_name")))
                            .append("\",");

                    json.append("\"location\":\"")
                            .append(escapeJson(
                                    result.getString("location")))
                            .append("\"");

                    json.append("}");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            json = new StringBuilder(
                    "{\"error\":\"Unable to load families\"}"
            );
        }

        json.append("]");

        sendJson(exchange, json.toString());
    }

    // =========================================================
    // NODE API
    // =========================================================

    private static void handleNodes(
            HttpExchange exchange
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("[");

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            String sql = """
                    SELECT
                        node_id,
                        status,
                        last_message,
                        messages_forwarded
                    FROM communication_node
                    ORDER BY rowid
                    """;

            try (Statement statement =
                         connection.createStatement();
                 ResultSet result =
                         statement.executeQuery(sql)) {

                boolean first = true;

                while (result.next()) {

                    if (!first) {
                        json.append(",");
                    }

                    first = false;

                    json.append("{");

                    json.append("\"node_id\":\"")
                            .append(escapeJson(
                                    result.getString("node_id")))
                            .append("\",");

                    json.append("\"status\":\"")
                            .append(escapeJson(
                                    result.getString("status")))
                            .append("\",");

                    json.append("\"last_message\":\"")
                            .append(escapeJson(
                                    result.getString("last_message")))
                            .append("\",");

                    json.append("\"messages_forwarded\":")
                            .append(result.getInt(
                                    "messages_forwarded"));

                    json.append("}");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            json = new StringBuilder(
                    "{\"error\":\"Unable to load nodes\"}"
            );
        }

        json.append("]");

        sendJson(exchange, json.toString());
    }

    // =========================================================
    // ROUTES API
    // =========================================================

    private static void handleRoutes(
            HttpExchange exchange
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("[");

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            String sql = """
                    SELECT
                        route_id,
                        source_node,
                        destination_node,
                        distance,
                        status
                    FROM route
                    ORDER BY rowid
                    """;

            try (Statement statement =
                         connection.createStatement();
                 ResultSet result =
                         statement.executeQuery(sql)) {

                boolean first = true;

                while (result.next()) {

                    if (!first) {
                        json.append(",");
                    }

                    first = false;

                    json.append("{");

                    json.append("\"route_id\":\"")
                            .append(escapeJson(
                                    result.getString("route_id")))
                            .append("\",");

                    json.append("\"source_node\":\"")
                            .append(escapeJson(
                                    result.getString("source_node")))
                            .append("\",");

                    json.append("\"destination_node\":\"")
                            .append(escapeJson(
                                    result.getString("destination_node")))
                            .append("\",");

                    json.append("\"distance\":")
                            .append(result.getDouble("distance"))
                            .append(",");

                    json.append("\"status\":\"")
                            .append(escapeJson(
                                    result.getString("status")))
                            .append("\"");

                    json.append("}");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            json = new StringBuilder(
                    "{\"error\":\"Unable to load routes\"}"
            );
        }

        json.append("]");

        sendJson(exchange, json.toString());
    }

    // =========================================================
    // RESCUE API
    // =========================================================

    private static void handleRescue(
            HttpExchange exchange
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("[");

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            String sql = """
                    SELECT
                        case_id,
                        alert_id,
                        family_id,
                        rescue_team,
                        status,
                        progress
                    FROM rescue_case
                    ORDER BY rowid DESC
                    """;

            try (Statement statement =
                         connection.createStatement();
                 ResultSet result =
                         statement.executeQuery(sql)) {

                boolean first = true;

                while (result.next()) {

                    if (!first) {
                        json.append(",");
                    }

                    first = false;

                    json.append("{");

                    json.append("\"case_id\":\"")
                            .append(escapeJson(
                                    result.getString("case_id")))
                            .append("\",");

                    json.append("\"alert_id\":\"")
                            .append(escapeJson(
                                    result.getString("alert_id")))
                            .append("\",");

                    json.append("\"family_id\":\"")
                            .append(escapeJson(
                                    result.getString("family_id")))
                            .append("\",");

                    json.append("\"rescue_team\":\"")
                            .append(escapeJson(
                                    result.getString("rescue_team")))
                            .append("\",");

                    json.append("\"status\":\"")
                            .append(escapeJson(
                                    result.getString("status")))
                            .append("\",");

                    json.append("\"progress\":")
                            .append(result.getInt("progress"));

                    json.append("}");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            json = new StringBuilder(
                    "{\"error\":\"Unable to load rescue cases\"}"
            );
        }

        json.append("]");

        sendJson(exchange, json.toString());
    }

    // =========================================================
    // SOS API
    // =========================================================

    private static void handleSOS(
            HttpExchange exchange
    ) throws IOException {

        if (!exchange.getRequestMethod()
                .equalsIgnoreCase("POST")) {

            sendResponse(
                    exchange,
                    405,
                    "text/plain",
                    "Only POST method is allowed"
            );

            return;
        }

        String body = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        String familyId =
                getJsonValue(body, "family_id");

        String houseId =
                getJsonValue(body, "house_id");

        if (familyId == null ||
                familyId.isBlank()) {

            familyId = "F001";
        }

        if (houseId == null ||
                houseId.isBlank()) {

            houseId = "H001";
        }

        String alertId =
                "SOS-" + System.currentTimeMillis();

        String sql = """
                INSERT INTO emergency_alert
                (
                    alert_id,
                    family_id,
                    house_id,
                    alert_type,
                    priority,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, alertId);
            statement.setString(2, familyId);
            statement.setString(3, houseId);
            statement.setString(4, "SOS");
            statement.setString(5, "HIGH");
            statement.setString(6, "ACTIVE");

            statement.executeUpdate();

            String json = """
                    {
                      "success": true,
                      "message": "SOS alert created successfully",
                      "alert_id": "%s",
                      "priority": "HIGH",
                      "status": "ACTIVE"
                    }
                    """.formatted(
                    escapeJson(alertId)
            );

            sendJson(exchange, json);

        } catch (Exception e) {

            e.printStackTrace();

            sendJson(
                    exchange,
                    """
                    {
                      "success": false,
                      "message": "Failed to create SOS alert"
                    }
                    """
            );
        }
    }

    // =========================================================
    // JSON VALUE READER
    // =========================================================

    private static String getJsonValue(
            String json,
            String key
    ) {

        if (json == null ||
                json.isBlank()) {

            return null;
        }

        String search =
                "\"" + key + "\"";

        int keyPosition =
                json.indexOf(search);

        if (keyPosition == -1) {
            return null;
        }

        int colonPosition =
                json.indexOf(
                        ":",
                        keyPosition + search.length()
                );

        if (colonPosition == -1) {
            return null;
        }

        int firstQuote =
                json.indexOf(
                        "\"",
                        colonPosition
                );

        if (firstQuote == -1) {
            return null;
        }

        int secondQuote =
                json.indexOf(
                        "\"",
                        firstQuote + 1
                );

        if (secondQuote == -1) {
            return null;
        }

        return json.substring(
                firstQuote + 1,
                secondQuote
        );
    }

    // =========================================================
    // JSON ESCAPE
    // =========================================================

    private static String escapeJson(
            String value
    ) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // =========================================================
    // SEND JSON
    // =========================================================

    private static void sendJson(
            HttpExchange exchange,
            String json
    ) throws IOException {

        sendResponse(
                exchange,
                200,
                "application/json; charset=UTF-8",
                json
        );
    }

    // =========================================================
    // SEND RESPONSE
    // =========================================================

    private static void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String contentType,
            String response
    ) throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders().set(
                "Content-Type",
                contentType
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Methods",
                "GET, POST, OPTIONS"
        );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );

        exchange.sendResponseHeaders(
                statusCode,
                bytes.length
        );

        try (OutputStream output =
                     exchange.getResponseBody()) {

            output.write(bytes);
        }
    }
}