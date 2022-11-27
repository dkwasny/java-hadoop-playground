package net.kwas.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class GraphTraversals {

    public static List<Integer> depthFirstTraversalRecursive(Node node) {
        return depthFirstTraversalRecursive(node, new HashSet<>());
    }

    private static List<Integer> depthFirstTraversalRecursive(Node node, Set<Integer> seenNodes) {
        List<Integer> retVal = new ArrayList<>();
        if (!seenNodes.contains(node.getId())) {
            retVal.add(node.getId());
            seenNodes.add(node.getId());
            for (Node neighbor : node.getNeighbors()) {
                retVal.addAll(depthFirstTraversalRecursive(neighbor, seenNodes));
            }
        }
        return retVal;
    }

    public static List<Integer> depthFirstTraversal(Node node) {
        return traverse(node, GraphTraversals::addToFront);
    }

    public static List<Integer> breadthFirstTraversal(Node node) {
        return traverse(node, GraphTraversals::addToEnd);
    }

    private static List<Integer> traverse(Node node, BiConsumer<Node, Deque<Node>> appender) {
        List<Integer> retVal = new ArrayList<>();

        Set<Integer> seenNodes = new HashSet<>();
        Deque<Node> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(node);
        while (!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.pollFirst();
            if (!seenNodes.contains(nextNode.getId())) {
                retVal.add(nextNode.getId());
                seenNodes.add(nextNode.getId());
                appender.accept(nextNode, nodesToVisit);
            }
        }

        return retVal;
    }

    private static void addToFront(Node node, Deque<Node> nodesToVisit) {
        List<Node> neighbors = node.getNeighbors();
        for (int i = neighbors.size() - 1; i >= 0; i--) {
            nodesToVisit.addFirst(neighbors.get(i));
        }
    }

    private static void addToEnd(Node node, Deque<Node> nodesToVisit) {
        for (Node neighbor : node.getNeighbors()) {
            nodesToVisit.addLast(neighbor);
        }
    }

}
