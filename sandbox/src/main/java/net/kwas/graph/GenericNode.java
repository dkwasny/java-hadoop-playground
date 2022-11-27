package net.kwas.graph;

import java.util.ArrayList;
import java.util.List;

public class GenericNode implements Node {
    private final List<Node> neighbors = new ArrayList<>();
    private final int id;

    public GenericNode(int id) {
        this.id = id;
    }

    public GenericNode withNeighbor(GenericNode node) {
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
