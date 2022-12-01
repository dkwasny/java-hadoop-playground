package net.kwas.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphSearchesTest {

    @Test
    public void breadthFirstSearch_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 5, 10);
        List<Integer> actual = GraphSearches.breadthFirstSearch(tree, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstSearch_graph() {
        Node tree = getGraph();
        List<Integer> expected = List.of(1, 7, 3, 8);
        List<Integer> actual = GraphSearches.breadthFirstSearch(tree, 8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearch_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 5, 10);
        List<Integer> actual = GraphSearches.depthFirstSearch(tree, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearch_graph() {
        Node tree = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 3, 8);
        List<Integer> actual = GraphSearches.depthFirstSearch(tree, 8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearchRecursive_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 5, 10);
        List<Integer> actual = GraphSearches.depthFirstSearchRecursive(tree, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearchRecursive_graph() {
        Node tree = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 3, 8);
        List<Integer> actual = GraphSearches.depthFirstSearchRecursive(tree, 8);
        Assertions.assertEquals(expected, actual);
    }

    private SimpleNode getTree() {
        return new SimpleNode(1)
            .withNeighbor(new SimpleNode(2)
                .withNeighbor(new SimpleNode(4))
                .withNeighbor(new SimpleNode(5)
                    .withNeighbor(new SimpleNode(9))
                    .withNeighbor(new SimpleNode(10))
                )
            )
            .withNeighbor(new SimpleNode(3))
            .withNeighbor(new SimpleNode(6)
                .withNeighbor(new SimpleNode(7))
                .withNeighbor(new SimpleNode(8))
            );
    }

    private SimpleNode getGraph() {
        Map<Integer, SimpleNode> nodes = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            nodes.put(i, new SimpleNode(i));
        }

        setupNeighbors(nodes, 1, 2, 5, 7);
        setupNeighbors(nodes, 2, 1, 5, 4);
        setupNeighbors(nodes, 3, 7, 8, 6);
        setupNeighbors(nodes, 4, 2, 6);
        setupNeighbors(nodes, 5, 1, 2, 6);
        setupNeighbors(nodes, 6, 4, 3);
        setupNeighbors(nodes, 7, 1, 3);
        setupNeighbors(nodes, 8, 3);

        return nodes.get(1);
    }

    private void setupNeighbors(Map<Integer, SimpleNode> nodes, int nodeId, int... neighborIds) {
        SimpleNode node = nodes.get(nodeId);
        for (int neighborId : neighborIds) {
            node.withNeighbor(nodes.get(neighborId));
        }
    }
}
