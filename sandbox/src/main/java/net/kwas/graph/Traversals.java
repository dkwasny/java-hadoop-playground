package net.kwas.graph;

import java.util.*;

public class Traversals {

    public static List<Integer> depthFirstTraversalRecursive(Node node) {
        return depthFirstTraversalRecursive(node, new HashSet<>());
    }

    public static List<Integer> depthFirstTraversalRecursive(Node node, Set<Integer> seenNodes) {
        List<Integer> retVal = new ArrayList<>();
        if (!seenNodes.contains(node.getId())) {
            retVal.add(node.getId());
            seenNodes.add(node.getId());
            for (Node child : node.getNeighbors()) {
                retVal.addAll(depthFirstTraversalRecursive(child, seenNodes));
            }
        }
        return retVal;
    }

    public static List<Integer> depthFirstTraversal(Node node) {
        List<Integer> retVal = new ArrayList<>();

        Set<Integer> seenNodes = new HashSet<>();
        Deque<Node> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(node);
        while (!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.pollFirst();
            if (!seenNodes.contains(nextNode.getId())) {
                retVal.add(nextNode.getId());
                seenNodes.add(nextNode.getId());
                List<Node> neighbors = nextNode.getNeighbors();
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    nodesToVisit.addFirst(neighbors.get(i));
                }
            }
        }

        return retVal;
    }

    public static List<Integer> breadthFirstTraversal(Node node) {
        List<Integer> retVal = new ArrayList<>();

        Set<Integer> seenNodes = new HashSet<>();
        Deque<Node> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(node);
        while (!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.pollFirst();
            if (!seenNodes.contains(nextNode.getId())) {
                retVal.add(nextNode.getId());
                seenNodes.add(nextNode.getId());
                for (Node currNode : nextNode.getNeighbors()) {
                    nodesToVisit.addLast(currNode);
                }
            }
        }

        return retVal;
    }

}
