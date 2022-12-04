package net.kwas.graph.weighted;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraphSearchesTest {

    @Test
    public void dijkstraSearch_null() {
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(null, 3);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void dijkstraSearch_singleNode() {
        WeightedNode root = new WeightedNode(1);
        List<Integer> expected = List.of(1);
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(root, 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dijkstraSearch_simpleCase_present() {
        WeightedNode root = new WeightedNode(1)
            .withNeighbor(new WeightedNode(2), 5);
        List<Integer> expected = List.of(1, 2);
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(root, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dijkstraSearch_simpleCase_notPresent() {
        WeightedNode root = new WeightedNode(1)
            .withNeighbor(new WeightedNode(2), 5);
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(root, -1);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void dijkstraSearch_fullCase_present() {
        WeightedNode root = getGraph();
        List<Integer> expected = List.of(1, 5, 6, 7, 3);
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(root, 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void dijkstraSearch_fullCase_notPresent() {
        WeightedNode root = getGraph();
        List<Integer> actual = WeightedGraphSearches.dijkstraSearch(root, -1);
        Assertions.assertTrue(actual.isEmpty());
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
