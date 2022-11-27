package net.kwas.graph.tree.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BinaryTreeBalancer {

    public static BinaryTree arrayBalance(BinaryTree tree) {
        List<Integer> inOrder = BinaryTreeTraversals.inOrderTraversal(tree);

        BinaryTree retVal = new BinaryTree();
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

    public static BinaryTree arrayBalanceRecursive(BinaryTree tree) {
        List<Integer> inOrder = BinaryTreeTraversals.inOrderTraversalRecursive(tree);

        BinaryTree retVal = new BinaryTree();
        arrayBalanceRecursive(retVal, inOrder);

        return retVal;
    }

    private static void arrayBalanceRecursive(BinaryTree tree, List<Integer> values) {
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
