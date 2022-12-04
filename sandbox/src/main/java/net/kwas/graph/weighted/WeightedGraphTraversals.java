package net.kwas.graph.weighted;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedGraphTraversals {

    public static Map<Integer, Integer> dijkstraTraversal(WeightedNode root) {
        if (root == null) {
            return Collections.emptyMap();
        }

        Map<Integer, Integer> retVal = new LinkedHashMap<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> weights = new HashMap<>();
        PriorityQueue<WeightedNode> nextNode = new PriorityQueue<>(
            Comparator.comparing(x -> weights.get(x.getId()))
        );

        weights.put(root.getId(), 0);
        nextNode.add(root);

        while (!nextNode.isEmpty()) {
            WeightedNode currNode = nextNode.poll();

            if (!visited.contains(currNode.getId())) {
                for (WeightedEdge edge : currNode.getWeightedEdges()) {
                    int currWeight = weights.get(currNode.getId());
                    int edgeWeight = edge.getWeight();
                    int fullWeight = currWeight + edgeWeight;

                    WeightedNode neighbor = edge.getNode();
                    Integer existingWeight = weights.get(neighbor.getId());
                    if (existingWeight == null || existingWeight > fullWeight) {
                        weights.put(neighbor.getId(), fullWeight);
                        nextNode.add(neighbor);
                    }
                }

                retVal.put(currNode.getId(), weights.get(currNode.getId()));
                visited.add(currNode.getId());
            }
        }

        return retVal;
    }

}
