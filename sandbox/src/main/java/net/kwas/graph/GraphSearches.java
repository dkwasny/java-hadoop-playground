package net.kwas.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class GraphSearches {

    private record NodePair(Node node, Node parent) { }

    public static List<Integer> depthFirstSearch(Node node, int target) {
        return search(node, target, GraphSearches::addToFront);
    }

    public static List<Integer> breadthFirstSearch(Node node, int target) {
        return search(node, target, GraphSearches::addToEnd);
    }

    private static List<Integer> search(Node node, int target, BiConsumer<Node, Deque<NodePair>> appender) {
        List<Integer> retVal = List.of();

        Map<Integer, Node> parentMap = new HashMap<>();
        Set<Integer> seenNodes = new HashSet<>();
        Deque<NodePair> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(new NodePair(node, null));
        while (!nodesToVisit.isEmpty()) {
            NodePair currPair = nodesToVisit.pollFirst();
            Node currNode = currPair.node;
            if (!seenNodes.contains(currNode.getId())) {
                seenNodes.add(currNode.getId());
                Node parent = currPair.parent;
                if (parent != null && !parentMap.containsKey(currNode.getId())) {
                    parentMap.put(currNode.getId(), parent);
                }
                if (currNode.getId() == target) {
                    retVal = getPath(currNode, parentMap);
                    break;
                }
                appender.accept(currNode, nodesToVisit);
            }
        }

        return retVal;
    }

    private static void addToFront(Node node, Deque<NodePair> nodesToVisit) {
        List<Node> neighbors = node.getNeighbors();
        for (int i = neighbors.size() - 1; i >= 0; i--) {
            Node neighbor = neighbors.get(i);
            nodesToVisit.addFirst(new NodePair(neighbor, node));
        }
    }

    private static void addToEnd(Node node, Deque<NodePair> nodesToVisit) {
        for (Node neighbor : node.getNeighbors()) {
            nodesToVisit.addLast(new NodePair(neighbor, node));
        }
    }

    private static List<Integer> getPath(Node start, Map<Integer, Node> parentMap) {
        List<Integer> retVal = new ArrayList<>();
        Node currNode = start;
        while (currNode != null) {
            retVal.add(currNode.getId());
            currNode = parentMap.get(currNode.getId());
        }
        Collections.reverse(retVal);
        return retVal;
    }

    public static List<Integer> depthFirstSearchRecursive(Node node, int target) {
        List<Integer> retVal = new ArrayList<>();

        if (depthFirstSearchRecursive(node, target, retVal, new HashSet<>())) {
            Collections.reverse(retVal);
        }

        return retVal;
    }

    private static boolean depthFirstSearchRecursive(Node node, int target, List<Integer> path, Set<Integer> seenNodes) {
        if (node == null || seenNodes.contains(node.getId())) {
            return false;
        }

        seenNodes.add(node.getId());
        if (node.getId() == target) {
            path.add(node.getId());
            return true;
        }
        else {
            for (Node neighbor : node.getNeighbors()) {
                if (depthFirstSearchRecursive(neighbor, target, path, seenNodes)) {
                    path.add(node.getId());
                    return true;
                }
            }
        }
        return false;
    }

}
