package net.kwas.graph.weighted;

import net.kwas.graph.GraphSearches;
import net.kwas.graph.GraphTraversals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraphIntegrationTest {

    @Test
    public void depthFirstTraversal() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 2, 3, 7, 4);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(root);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversalRecursive() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 2, 3, 7, 4);
        List<Integer> actual = GraphTraversals.depthFirstTraversalRecursive(root);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 4, 2, 7, 3);
        List<Integer> actual = GraphTraversals.breadthFirstTraversal(root);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearch() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 2, 3);
        List<Integer> actual = GraphSearches.depthFirstSearch(root, 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearchRecursive() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 2, 3);
        List<Integer> actual = GraphSearches.depthFirstSearchRecursive(root, 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstSearch() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 4, 3);
        List<Integer> actual = GraphSearches.breadthFirstSearch(root, 3);
        Assertions.assertEquals(expected, actual);
    }

    private WeightedNode getGraph() {
        Map<Integer, WeightedNode> nodes = new HashMap<>();
        for (int i = 1; i <= 7; i++) {
            nodes.put(i, new WeightedNode(i));
        }

        /*
         * 1 through 5 are outside nodes clockwise.
         * 6 and 7 sit inside.
         */
        setupLink(nodes, 1, 5, 2);
        setupLink(nodes, 2, 1, 1);
        setupLink(nodes, 2, 3, 20);
        setupLink(nodes, 4, 3, 15);
        setupLink(nodes, 4, 6, 5);
        setupLink(nodes, 5, 6, 1);
        setupLink(nodes, 5, 4, 8);
        setupLink(nodes, 6, 2, 2);
        setupLink(nodes, 6, 7, 10);
        setupLink(nodes, 7, 3, 6);

        return nodes.get(1);
    }

    private void setupLink(Map<Integer, WeightedNode> nodes, int from, int to, int weight) {
        nodes.get(from).withNeighbor(nodes.get(to), weight);
    }

}
