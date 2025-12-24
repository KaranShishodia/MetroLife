

public class Edge {
    Station target;
    double weight; // Distance or time cost

    public Edge(Station target, double weight) {
        this.target = target;
        this.weight = weight;
    }
}