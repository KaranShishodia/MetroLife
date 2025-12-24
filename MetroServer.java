import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class MetroServer {
    static Graph cityMetro = new Graph();

    public static void main(String[] args) throws IOException {
        initializeDelhiMap(); // Updated method name
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // API: Get Stations
        server.createContext("/stations", exchange -> {
            String response = cityMetro.getAllStations().toString();
            sendResponse(exchange, response);
        });

        // API: Find Path
        server.createContext("/find-path", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String[] params = query.split("&");
            String start = params[0].split("=")[1].replace("%20", " "); // Handle spaces
            String end = params[1].split("=")[1].replace("%20", " ");

            List<String> path = cityMetro.getShortestPath(start, end);
            sendResponse(exchange, path.toString());
        });

        server.setExecutor(null);
        System.out.println("MetroLife Server Started on Port 8080...");
        server.start();
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void initializeDelhiMap() {
        // --- NODES (Stations) ---
        cityMetro.addStation("Rajiv Chowk");     // The Center
        cityMetro.addStation("Kashmere Gate");   // North Hub
        cityMetro.addStation("New Delhi");       // Railway Connector
        cityMetro.addStation("Chandni Chowk");   // Old Delhi
        cityMetro.addStation("Hauz Khas");       // South Hub
        cityMetro.addStation("IGI Airport");     // West (Express)
        cityMetro.addStation("Noida Sec 18");    // East
        cityMetro.addStation("Karol Bagh");      // West
        cityMetro.addStation("Lajpat Nagar");    // South East

        // --- EDGES (Tracks & Time in Minutes) ---
        
        // Yellow Line Axis
        cityMetro.addEdge("Kashmere Gate", "Chandni Chowk", 3);
        cityMetro.addEdge("Chandni Chowk", "New Delhi", 4);
        cityMetro.addEdge("New Delhi", "Rajiv Chowk", 3);
        cityMetro.addEdge("Rajiv Chowk", "Hauz Khas", 15);

        // Blue Line Axis
        cityMetro.addEdge("Karol Bagh", "Rajiv Chowk", 8);
        cityMetro.addEdge("Rajiv Chowk", "Noida Sec 18", 20);

        // Airport Express
        cityMetro.addEdge("New Delhi", "IGI Airport", 20); // Fast!

        // Pink/Violet Interchanges (Connecting the ring)
        cityMetro.addEdge("Hauz Khas", "IGI Airport", 25); 
        cityMetro.addEdge("Lajpat Nagar", "Hauz Khas", 10);
        cityMetro.addEdge("Noida Sec 18", "Lajpat Nagar", 12);
    }
}