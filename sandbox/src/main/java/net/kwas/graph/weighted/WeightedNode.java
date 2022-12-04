package net.kwas.graph.weighted;

import net.kwas.graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeightedNode implements Node {

    private final List<WeightedEdge> edges = new ArrayList<>();

    private final int id;

    public WeightedNode(int id) {
        this.id = id;
    }

    public WeightedNode withNeighbor(WeightedNode node, int weight) {
        edges.add(new WeightedEdge(node, weight));
        return this;
    }

    public List<WeightedEdge> getWeightedEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    @Override
    public List<Node> getNeighbors() {
        return edges.stream()
            .map(WeightedEdge::getNode)
            .collect(Collectors.toList());
    }

}
