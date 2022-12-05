package net.kwas.graph;

import java.util.ArrayList;
import java.util.List;

public class SimpleNode implements Node {
    private final List<Node> neighbors = new ArrayList<>();
    private final int id;

    public SimpleNode(int id) {
        this.id = id;
    }

    public SimpleNode withNeighbor(SimpleNode node) {
        neighbors.add(node);
        return this;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SimpleNode{" +
            "id=" + id +
            '}';
    }
}
