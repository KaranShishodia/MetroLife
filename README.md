# MetroLife
MetroLife is a Java-based metro simulation and pathfinding system designed to model a city’s metro network. It represents stations as nodes and connections between them as weighted edges, enabling efficient route planning and shortest-path calculations (e.g., using Dijkstra’s algorithm).

# 🚇 MetroLife: Intelligent Urban Transit Optimizer

![Java](https://img.shields.io/badge/Backend-Java_Core-orange) ![DSA](https://img.shields.io/badge/Algorithm-Dijkstra-red) ![Frontend](https://img.shields.io/badge/Frontend-HTML%2FJS-blue)

**MetroLife** is a full-stack transit simulation engine designed to solve the "Shortest Path Problem" in complex urban networks. Unlike standard routing apps that rely on external APIs (like Google Maps), this project implements the core pathfinding logic from scratch using **Java**, demonstrating a deep understanding of Graph Theory and Heap data structures.


---

##  Key Features

* **Real-time Pathfinding:** Calculates the optimal route between stations in milliseconds.
* **Custom Graph Implementation:** Uses an **Adjacency List** to model the city network efficiently.
* **Priority Queue Optimization:** Utilizes a Min-Heap to ensure the pathfinding algorithm runs in logarithmic time.
* **Lightweight Server:** Runs on a raw Java `HttpServer` without heavy frameworks, minimizing overhead and latency.

---

##  System Architecture

The application follows a **Client-Server Architecture**:

1.  **Client (Frontend):** Interactive UI built with HTML5 & Vanilla JavaScript. It visualizes the nodes (stations) and edges (tracks).
2.  **Server (Backend):** A multi-threaded Java HTTP server that exposes REST endpoints.
3.  **Engine (Logic):** The core `Graph.java` class where the DSA magic happens.

```mermaid
graph LR
    A[Frontend UI] -- HTTP GET --> B((Java HTTP Server))
    B -- Request --> C{Graph Engine}
    C -- Dijkstra Algorithm --> D[Priority Queue]
    C -- Shortest Path --> B
    B -- JSON Response --> A


Algorithms & Complexity AnalysisThis project was built to optimize performance for large-scale networks.1. Shortest Path: Dijkstra's AlgorithmInstead of a naive Breadth-First Search (BFS), I implemented Dijkstra’s Algorithm to handle weighted edges (different travel times between stations).Time Complexity: $O(E + V \log V)$Where $V$ is vertices (stations) and $E$ is edges (tracks).The usage of a Priority Queue (Binary Heap) allows us to extract the minimum weight node in $O(\log V)$ time.Space Complexity: $O(V + E)$We use an Adjacency List (HashMap of Lists) instead of an Adjacency Matrix ($O(V^2)$) to save memory, as metro networks are typically "sparse graphs."
2. Station Lookup: HashMapComplexity: $O(1)$Used to instantly retrieve Station objects based on string names during request parsing.

 Project StructurePlaintextMetroMind/
├── src/
│   ├── Graph.java        # Core DSA Implementation (Adjacency List + Dijkstra)
│   ├── Station.java      # Node Object (implements Comparable for PriorityQueue)
│   ├── Edge.java         # Weighted Connection Object
│   └── MetroServer.java  # HTTP Server & API Endpoints
└── frontend/
    ├── index.html        # Visualization Dashboard
    |-- style.css
    └── script.js         # Fetch API logic to communicate with Java


 Getting StartedPrerequisitesJava Development Kit (JDK) 8 or higher.A web browser.Installation & RunClone the RepositoryBashgit clone [https://github.com/yourusername/MetroMind.git](https://github.com/yourusername/MetroMind.git)
cd MetroLife

Compile the BackendNavigate to the source folder:Bashcd src
javac *.java

Start the ServerBashjava src.MetroServer
You should see: MetroLife Server Started on Port 8080...Launch the FrontendOpen frontend/index.html in your browser.📸

<img width="1242" height="811" alt="Screenshot 2025-12-24 114317" src="https://github.com/user-attachments/assets/383ff572-0d9a-4c41-bbf7-a44c9b59167b" />



Future Improvements*A (A-Star) Search:** Implementing heuristics to further optimize search speed for geographical maps.Traffic Simulation: Introducing dynamic edge weights to simulate rush-hour delays.MST Visualization: Using Kruskal’s Algorithm to show the minimum wiring cost to connect all stations.

AuthorKaran ShishodiaB.Tech Computer Science StudentPassionate about Java, DSA, and System Design.
