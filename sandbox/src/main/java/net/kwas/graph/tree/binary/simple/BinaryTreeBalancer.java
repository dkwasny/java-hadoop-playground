package net.kwas.graph.tree.binary.simple;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BinaryTreeBalancer {

    public static SimpleBinaryTree arrayBalance(SimpleBinaryTree tree) {
        List<Integer> inOrder = tree.inOrderTraversal();

        SimpleBinaryTree retVal = new SimpleBinaryTree();
        Deque<List<Integer>> listsToProcess = new ArrayDeque<>();
        listsToProcess.add(inOrder);
        while (!listsToProcess.isEmpty()) {
            List<Integer> sublist = listsToProcess.pollFirst();
            if (!sublist.isEmpty()) {
                int centerIdx = sublist.size() / 2;
                retVal.addValue(sublist.get(centerIdx));
                listsToProcess.addLast(sublist.subList(0, centerIdx));
                listsToProcess.addLast(sublist.subList(centerIdx + 1, sublist.size()));
            }
        }

        return retVal;
    }

    public static SimpleBinaryTree arrayBalanceRecursive(SimpleBinaryTree tree) {
        List<Integer> inOrder = tree.inOrderTraversalRecursive();

        SimpleBinaryTree retVal = new SimpleBinaryTree();
        arrayBalanceRecursive(retVal, inOrder);

        return retVal;
    }

    private static void arrayBalanceRecursive(SimpleBinaryTree tree, List<Integer> values) {
        int centerIdx = values.size() / 2;

        if (centerIdx < values.size()) {
            tree.addValue(values.get(centerIdx));
        }

        if (values.size() > 1) {
            arrayBalanceRecursive(tree, values.subList(0, centerIdx));
            arrayBalanceRecursive(tree, values.subList(centerIdx + 1, values.size()));
        }
    }

}
