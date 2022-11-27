package net.kwas.graph.tree.binary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeTraversals {

    public static List<Integer> inOrderTraversal(BinaryTree tree) {
        List<Integer> retVal = new ArrayList<>(tree.getSize());

        Deque<BinaryTreeNode> nodesToVisit = new ArrayDeque<>();
        BinaryTreeNode currNode = tree.getHead();
        while (!nodesToVisit.isEmpty() || currNode != null) {

            while (currNode != null) {
                nodesToVisit.addFirst(currNode);
                currNode = currNode.getLeftChild();
            }

            currNode = nodesToVisit.pollFirst();

            if (currNode != null) {
                retVal.add(currNode.getId());
                currNode = currNode.getRightChild();
            }
        }

        return retVal;
    }

    public static List<Integer> inOrderTraversalRecursive(BinaryTree tree) {
        List<Integer> retVal = new ArrayList<>(tree.getSize());
        inOrderTraversalRecursive(tree.getHead(), retVal);
        return retVal;
    }

    private static void inOrderTraversalRecursive(BinaryTreeNode node, List<Integer> values) {
        if (node != null) {
            inOrderTraversalRecursive(node.getLeftChild(), values);
            values.add(node.getId());
            inOrderTraversalRecursive(node.getRightChild(), values);
        }
    }

}
