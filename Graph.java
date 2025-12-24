

import java.util.*;

public class Graph {
    // Adjacency List to store the graph
    private final Map<String, List<Edge>> adjList = new HashMap<>();
    private final Map<String, Station> stationMap = new HashMap<>();

    public void addStation(String name) {
        stationMap.putIfAbsent(name, new Station(name));
        adjList.putIfAbsent(name, new ArrayList<>());
    }

    public void addEdge(String src, String dest, double weight) {
        // Undirected Graph (Two-way street)
        Station s1 = stationMap.get(src);
        Station s2 = stationMap.get(dest);
        
        if (s1 != null && s2 != null) {
            adjList.get(src).add(new Edge(s2, weight));
            adjList.get(dest).add(new Edge(s1, weight));
        }
    }

    // THE RESUME BOOSTER: Dijkstra's Algorithm implementation
    public List<String> getShortestPath(String startName, String endName) {
        // 1. Reset all stations
        for (Station s : stationMap.values()) {
            s.minDistance = Integer.MAX_VALUE;
            s.previous = null;
        }

        Station start = stationMap.get(startName);
        Station end = stationMap.get(endName);

        if (start == null || end == null) return Collections.emptyList();

        start.minDistance = 0;
        
        // PriorityQueue is the key to Dijkstra's efficiency
        PriorityQueue<Station> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            Station current = pq.poll();

            if (current.name.equals(endName)) break; // Found destination

            for (Edge edge : adjList.get(current.name)) {
                Station neighbor = (Station) edge.target;
                double newDist = current.minDistance + edge.weight;

                if (newDist < neighbor.minDistance) {
                    pq.remove(neighbor); // Refresh priority
                    neighbor.minDistance = (int) newDist;
                    neighbor.previous = current;
                    pq.add(neighbor);
                }
            }
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        for (Station at = end; at != null; at = at.previous) {
            path.add(at.name);
        }
        Collections.reverse(path);
        return path;
    }
    
    // Helper to get all stations for the frontend dropdown
    public Set<String> getAllStations() {
        return stationMap.keySet();
    }
}