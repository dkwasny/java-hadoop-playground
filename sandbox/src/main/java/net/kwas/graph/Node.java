package net.kwas.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> neighbors = new ArrayList<>();
    private final int id;

    public Node(int id) {
        this.id = id;
    }

    public Node withNeighbor(Node node) {
        neighbors.add(node);
        return this;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }
}
