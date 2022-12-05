package net.kwas.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CycleDetector {

    private record NodeVisit(Node node, boolean isVisit) { }

    public static boolean hasCycle(Node node) {
        Set<Integer> nodesInPath = new HashSet<>();
        Deque<NodeVisit> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(new NodeVisit(node, true));

        boolean retVal = false;
        while (!nodesToVisit.isEmpty()) {
            NodeVisit currVisit = nodesToVisit.pollFirst();
            Node currNode = currVisit.node;

            if (currVisit.isVisit()) {
                if (nodesInPath.contains(currNode.getId())) {
                    retVal = true;
                    break;
                }
                nodesInPath.add(currNode.getId());

                nodesToVisit.addFirst(new NodeVisit(currNode, false));
                for (Node neighbor : currNode.getNeighbors()) {
                    nodesToVisit.addFirst(new NodeVisit(neighbor, true));
                }
            }
            else {
                nodesInPath.remove(currNode.getId());
            }
        }

        return retVal;
    }


    public static boolean hasCycleRecursive(Node node) {
        return hasCycleRecursive(node, new HashSet<>());
    }

    private static boolean hasCycleRecursive(Node node, Set<Integer> nodesInPath) {
        if (nodesInPath.contains(node.getId())) {
            return true;
        }
        nodesInPath.add(node.getId());

        for (Node neighbor : node.getNeighbors()) {
            if (hasCycleRecursive(neighbor, nodesInPath)){
                return true;
            }
        }

        nodesInPath.remove(node.getId());
        return false;
    }

}
