package net.kwas.graph.weighted;

public class WeightedEdge {

    private final WeightedNode node;
    private final int weight;

    public WeightedEdge(WeightedNode node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public WeightedNode getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }

}
