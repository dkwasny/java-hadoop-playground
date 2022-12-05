package net.kwas.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CycleDetectorTest {

    @Test
    public void hasCycle_cyclicGraphOne() {
        SimpleNode root = getCyclicGraphOne();
        Assertions.assertTrue(CycleDetector.hasCycle(root));
    }

    @Test
    public void hasCycle_cyclicGraphTwo() {
        SimpleNode root = getCyclicGraphTwo();
        Assertions.assertTrue(CycleDetector.hasCycle(root));
    }

    @Test
    public void hasCycle_acyclicGraph() {
        SimpleNode root = getAcyclicGraph();
        Assertions.assertFalse(CycleDetector.hasCycle(root));
    }

    @Test
    public void hasCycleRecursive_cyclicGraphOne() {
        SimpleNode root = getCyclicGraphOne();
        Assertions.assertTrue(CycleDetector.hasCycleRecursive(root));
    }

    @Test
    public void hasCycleRecursive_cyclicGraphTwo() {
        SimpleNode root = getCyclicGraphTwo();
        Assertions.assertTrue(CycleDetector.hasCycleRecursive(root));
    }

    @Test
    public void hasCycleRecursive_acyclicGraph() {
        SimpleNode root = getAcyclicGraph();
        Assertions.assertFalse(CycleDetector.hasCycleRecursive(root));
    }

    private SimpleNode getCyclicGraphOne() {
        Map<Integer, SimpleNode> nodes = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            nodes.put(i, new SimpleNode(i));
        }

        setupNeighbors(nodes, 1, 2);
        setupNeighbors(nodes, 2, 3);
        setupNeighbors(nodes, 3, 1);

        return nodes.get(1);
    }

    private SimpleNode getCyclicGraphTwo() {
        Map<Integer, SimpleNode> nodes = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            nodes.put(i, new SimpleNode(i));
        }

        setupNeighbors(nodes, 1, 2);
        setupNeighbors(nodes, 2, 3);
        setupNeighbors(nodes, 3, 4);
        setupNeighbors(nodes, 4, 2);

        return nodes.get(1);
    }

    private SimpleNode getAcyclicGraph() {
        Map<Integer, SimpleNode> nodes = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            nodes.put(i, new SimpleNode(i));
        }

        setupNeighbors(nodes, 1, 2);
        setupNeighbors(nodes, 2, 3);
        setupNeighbors(nodes, 2, 4);
        setupNeighbors(nodes, 3, 4);

        return nodes.get(1);
    }

    private void setupNeighbors(Map<Integer, SimpleNode> nodes, int nodeId, int... neighborIds) {
        SimpleNode node = nodes.get(nodeId);
        for (int neighborId : neighborIds) {
            node.withNeighbor(nodes.get(neighborId));
        }
    }

}
