package net.kwas.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraversalsTest {

    @Test
    public void depthFirstTraversalRecursive_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = Traversals.depthFirstTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversal_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = Traversals.depthFirstTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal_tree() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 3, 6, 4, 5, 7, 8, 9, 10);
        List<Integer> actual = Traversals.breadthFirstTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversalRecursive_graph() {
        Node graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 4, 3, 7, 8);
        List<Integer> actual = Traversals.depthFirstTraversalRecursive(graph);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversal_graph() {
        Node graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 4, 3, 7, 8);
        List<Integer> actual = Traversals.depthFirstTraversal(graph);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal_graph() {
        Node graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 7, 4, 6, 3, 8);
        List<Integer> actual = Traversals.breadthFirstTraversal(graph);
        Assertions.assertEquals(expected, actual);
    }

    private Node getTree() {
        return new Node(1)
            .withNeighbor(new Node(2)
                .withNeighbor(new Node(4))
                .withNeighbor(new Node(5)
                    .withNeighbor(new Node(9))
                    .withNeighbor(new Node(10))
                )
            )
            .withNeighbor(new Node(3))
            .withNeighbor(new Node(6)
                .withNeighbor(new Node(7))
                .withNeighbor(new Node(8))
            );
    }

    private Node getGraph() {
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            nodes.put(i, new Node(i));
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

    private void setupNeighbors(Map<Integer, Node> nodes, int nodeId, int... neighborIds) {
        Node node = nodes.get(nodeId);
        for (int neighborId : neighborIds) {
            node.withNeighbor(nodes.get(neighborId));
        }
    }
}
