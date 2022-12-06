package net.kwas.problems;

import net.kwas.graph.CycleDetector;
import net.kwas.graph.GraphTraversals;
import net.kwas.graph.Node;
import net.kwas.graph.SimpleNode;
import net.kwas.graph.tree.binary.BinaryTree;
import net.kwas.graph.tree.binary.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphProblems {

    public static List<Integer> printDependencyPath(List<Integer> ids, List<Map.Entry<Integer, Integer>> dependencyList) {
        List<Integer> retVal = new ArrayList<>();

        // Find all rootIds (not dependent on anything) and calculate the
        // dependency map which maps each id to the ids it depends on
        Map<Integer, List<Integer>> depMap = new HashMap<>();
        Set<Integer> rootIds = new LinkedHashSet<>(ids);
        for (Map.Entry<Integer, Integer> dependency : dependencyList) {
            rootIds.remove(dependency.getValue());
            depMap.computeIfAbsent(dependency.getValue(), x -> new ArrayList<>()).add(dependency.getKey());
        }

        Set<Integer> visited = new HashSet<>();
        if (!rootIds.isEmpty()) {
            // Build the graph based on the dependency information
            Map<Integer, SimpleNode> nodes = createGraph(ids, dependencyList);

            for (Integer rootId : rootIds) {
                Node startingPoint = nodes.get(rootId);

                // Immediately fail if a root has a cycle
                if (CycleDetector.hasCycle(startingPoint)) {
                    break;
                }

                // Do a normal BFS but with additional logic respecting dependencies
                Deque<Node> nodesToVisit = new ArrayDeque<>();
                nodesToVisit.add(startingPoint);
                while (!nodesToVisit.isEmpty()) {
                    Node currNode = nodesToVisit.pollFirst();

                    if (!visited.contains(currNode.getId())) {
                        List<Integer> deps = depMap.get(currNode.getId());
                        // Only visit a node once its dependencies have been visited
                        if (deps == null || visited.containsAll(deps)) {
                            retVal.add(currNode.getId());
                            visited.add(currNode.getId());
                            for (Node neighbor : currNode.getNeighbors()) {
                                nodesToVisit.addLast(neighbor);
                            }
                        } else {
                            // This node needs more dependencies fulfilled.
                            // Put it at the end of the queue and move on.
                            nodesToVisit.addLast(currNode);
                        }
                    }
                }
            }
        }

        // We have to have visited every single node
        if (!visited.containsAll(ids)) {
            retVal = List.of();
        }

        return retVal;
    }

    private static Map<Integer, SimpleNode> createGraph(List<Integer> ids, List<Map.Entry<Integer, Integer>> dependencyList) {
        Map<Integer, SimpleNode> retVal = new HashMap<>();
        for (Integer id : ids) {
            retVal.put(id, new SimpleNode(id));
        }
        for (Map.Entry<Integer, Integer> dependency : dependencyList) {
            retVal.get(dependency.getKey()).withNeighbor(retVal.get(dependency.getValue()));
        }
        return retVal;
    }

    public static <T extends BinaryTreeNode<T>> List<List<Integer>> printBinaryTreeArrayPermutations(BinaryTree<T> tree) {
        LinkedList<List<Integer>> retVal = new LinkedList<>();
        List<Integer> allIds = GraphTraversals.breadthFirstTraversal(tree.getHead());

        Map<Integer, Integer> parentMap = getParentMap(tree);

        // First node always wins!
        int headId = tree.getHead().getId();
        retVal.add(new ArrayList<>());
        retVal.getFirst().add(headId);
        allIds.remove(Integer.valueOf(headId));

        for (int currLevel = 0; currLevel < allIds.size(); currLevel++) {
            int numListsForLevel = retVal.size();
            for (int i = 0; i < numListsForLevel; i++) {
                List<Integer> currList = retVal.getFirst();
                Set<Integer> potentialIds = new HashSet<>(allIds);
                Set<Integer> addedIds = new HashSet<>(currList);
                potentialIds.removeAll(addedIds);

                for (Integer newId : potentialIds) {
                    int parentId = parentMap.get(newId);
                    if (addedIds.contains(parentId)) {
                        List<Integer> newList = new ArrayList<>(currList);
                        newList.add(newId);
                        retVal.add(newList);
                    }
                }

                // We're done with this list...move on
                retVal.removeFirst();
            }
        }

        return retVal;
    }

    private static <T extends BinaryTreeNode<T>> Map<Integer, Integer> getParentMap(BinaryTree<T> tree) {
        Map<Integer, Integer> retVal = new HashMap<>();

        Deque<T> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(tree.getHead());
        while (!nodesToVisit.isEmpty()) {
            T currNode = nodesToVisit.pollFirst();

            T leftChild = currNode.getLeftChild();
            if (leftChild != null) {
                retVal.put(leftChild.getId(), currNode.getId());
                nodesToVisit.addLast(leftChild);
            }
            T rightChild = currNode.getRightChild();
            if (rightChild != null) {
                retVal.put(rightChild.getId(), currNode.getId());
                nodesToVisit.addLast(rightChild);
            }
        }

        return retVal;
    }

}
