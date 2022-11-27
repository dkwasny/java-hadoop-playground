package net.kwas.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphTraversalsTest {

    @Test
    public void depthFirstTraversalRecursive_tree() {
        GenericNode tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = GraphTraversals.depthFirstTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversal_tree() {
        GenericNode tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal_tree() {
        GenericNode tree = getTree();
        List<Integer> expected = List.of(1, 2, 3, 6, 4, 5, 7, 8, 9, 10);
        List<Integer> actual = GraphTraversals.breadthFirstTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversalRecursive_graph() {
        GenericNode graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 4, 3, 7, 8);
        List<Integer> actual = GraphTraversals.depthFirstTraversalRecursive(graph);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversal_graph() {
        GenericNode graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 6, 4, 3, 7, 8);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(graph);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal_graph() {
        GenericNode graph = getGraph();
        List<Integer> expected = List.of(1, 2, 5, 7, 4, 6, 3, 8);
        List<Integer> actual = GraphTraversals.breadthFirstTraversal(graph);
        Assertions.assertEquals(expected, actual);
    }

    private GenericNode getTree() {
        return new GenericNode(1)
            .withNeighbor(new GenericNode(2)
                .withNeighbor(new GenericNode(4))
                .withNeighbor(new GenericNode(5)
                    .withNeighbor(new GenericNode(9))
                    .withNeighbor(new GenericNode(10))
                )
            )
            .withNeighbor(new GenericNode(3))
            .withNeighbor(new GenericNode(6)
                .withNeighbor(new GenericNode(7))
                .withNeighbor(new GenericNode(8))
            );
    }

    private GenericNode getGraph() {
        Map<Integer, GenericNode> nodes = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            nodes.put(i, new GenericNode(i));
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

    private void setupNeighbors(Map<Integer, GenericNode> nodes, int nodeId, int... neighborIds) {
        GenericNode node = nodes.get(nodeId);
        for (int neighborId : neighborIds) {
            node.withNeighbor(nodes.get(neighborId));
        }
    }
}
