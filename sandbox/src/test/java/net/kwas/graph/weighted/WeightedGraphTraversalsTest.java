package net.kwas.graph.weighted;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeightedGraphTraversalsTest {

    @Test
    public void dijkstraTraversal_null() {
        Map<Integer, Integer> actual = WeightedGraphTraversals.dijkstraTraversal(null);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void dijkstraTraversal_singleNode() {
        WeightedNode root = new WeightedNode(1);

        Map<Integer, Integer> expected = new LinkedHashMap<>();
        expected.put(1, 0);

        Map<Integer, Integer> actual = WeightedGraphTraversals.dijkstraTraversal(root);
        validate(expected, actual);
    }

    @Test
    public void dijkstraTraversal_simpleCase() {
        WeightedNode root = new WeightedNode(1)
            .withNeighbor(new WeightedNode(2), 10);

        Map<Integer, Integer> expected = new LinkedHashMap<>();
        expected.put(1, 0);
        expected.put(2, 10);

        Map<Integer, Integer> actual = WeightedGraphTraversals.dijkstraTraversal(root);
        validate(expected, actual);
    }

    @Test
    public void dijkstraTraversal_fullCase() {
        WeightedNode root = getGraph();

        Map<Integer, Integer> expected = new LinkedHashMap<>();
        expected.put(1, 0);
        expected.put(5, 2);
        expected.put(6, 3);
        expected.put(2, 5);
        expected.put(4, 10);
        expected.put(7, 13);
        expected.put(3, 19);

        Map<Integer, Integer> actual = WeightedGraphTraversals.dijkstraTraversal(root);
        validate(expected, actual);
    }

    private void validate(Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        Iterator<Map.Entry<Integer, Integer>> expectedIterator = expected.entrySet().iterator();
        Iterator<Map.Entry<Integer, Integer>> actualIterator = actual.entrySet().iterator();

        while(expectedIterator.hasNext()) {
            Map.Entry<Integer, Integer> expectedEntry = expectedIterator.next();
            Map.Entry<Integer, Integer> actualEntry = actualIterator.next();
            Assertions.assertEquals(expectedEntry, actualEntry);
        }

        Assertions.assertFalse(actualIterator.hasNext());
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
