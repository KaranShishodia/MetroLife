


public class Station implements Comparable<Station> {
    String name;
    int minDistance = Integer.MAX_VALUE; // For Dijkstra
    Station previous; // To reconstruct the path

    public Station(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Station other) {
        return Integer.compare(this.minDistance, other.minDistance);
    }
}