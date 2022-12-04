package net.kwas.graph.weighted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedGraphSearches {

    public static List<Integer> dijkstraSearch(WeightedNode root, int target) {
        if (root == null) {
            return Collections.emptyList();
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> pathMap = new HashMap<>();
        Map<Integer, Integer> weights = new HashMap<>();
        PriorityQueue<WeightedNode> nodeQueue = new PriorityQueue<>(
            Comparator.comparing(x -> weights.get(x.getId()))
        );

        weights.put(root.getId(), 0);
        nodeQueue.add(root);

        boolean found = false;
        while (!nodeQueue.isEmpty()) {
            WeightedNode currentNode = nodeQueue.poll();

            if (currentNode.getId() == target) {
                found = true;
                break;
            }

            if (!visited.contains(currentNode.getId())) {
                for (WeightedEdge edge : currentNode.getWeightedEdges()) {
                    int currWeight = weights.get(currentNode.getId());
                    int neighborWeight = edge.getWeight();
                    int fullWeight = currWeight + neighborWeight;

                    WeightedNode neighbor = edge.getNode();
                    Integer existingWeight = weights.get(neighbor.getId());
                    if (existingWeight == null || existingWeight > fullWeight) {
                        weights.put(neighbor.getId(), fullWeight);
                        nodeQueue.add(neighbor);
                        pathMap.put(neighbor.getId(), currentNode.getId());
                    }
                }
                visited.add(currentNode.getId());
            }
        }

        List<Integer> retVal = new ArrayList<>();
        if (found) {
            Integer currNodeId = target;
            while (currNodeId != null) {
                retVal.add(currNodeId);
                currNodeId = pathMap.get(currNodeId);
            }
            Collections.reverse(retVal);
        }

        return retVal;
    }

}
