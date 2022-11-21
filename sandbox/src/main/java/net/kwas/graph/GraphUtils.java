package net.kwas.graph;

import java.util.*;

public class GraphUtils {

    public static List<Integer> depthFirstSearchRecursive(Node node) {
        List<Integer> retVal = new ArrayList<>();
        retVal.add(node.getId());
        for (Node child : node.getNeighbors()) {
            retVal.addAll(depthFirstSearchRecursive(child));
        }
        return retVal;
    }

    public static List<Integer> depthFirstSearch(Node node) {
        List<Integer> retVal = new ArrayList<>();

        Deque<Node> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(node);
        while (!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.pollFirst();
            retVal.add(nextNode.getId());

            List<Node> neighbors = nextNode.getNeighbors();
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                nodesToVisit.addFirst(neighbors.get(i));
            }
        }

        return retVal;
    }

    public static List<Integer> breadthFirstSearch(Node node) {
        List<Integer> retVal = new ArrayList<>();

        Deque<Node> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(node);
        while (!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.pollFirst();
            retVal.add(nextNode.getId());
            for (Node currNode : nextNode.getNeighbors()) {
                nodesToVisit.addLast(currNode);
            }
        }

        return retVal;
    }

}
