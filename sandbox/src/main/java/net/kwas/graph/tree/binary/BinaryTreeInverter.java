package net.kwas.graph.tree.binary;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTreeInverter {

    public static void invert(BinaryTree tree) {
        BinaryTreeNode head = tree.getHead();
        if (head == null) {
            return;
        }

        Deque<BinaryTreeNode> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(head);

        while (!nodesToVisit.isEmpty()) {
            BinaryTreeNode currNode = nodesToVisit.pollFirst();
            swapChildren(currNode);
            if (currNode.getRightChild() != null) {
                nodesToVisit.addFirst(currNode.getRightChild());
            }
            if (currNode.getLeftChild() != null) {
                nodesToVisit.addFirst(currNode.getLeftChild());
            }
        }
    }

    public static void invertRecursive(BinaryTree tree) {
        invertRecursive(tree.getHead());
    }

    private static void invertRecursive(BinaryTreeNode node) {
        if (node != null) {
            swapChildren(node);
            invertRecursive(node.getLeftChild());
            invertRecursive(node.getRightChild());
        }
    }

    private static void swapChildren(BinaryTreeNode node) {
        BinaryTreeNode tmp = node.getLeftChild();
        node.setLeftChild(node.getRightChild());
        node.setRightChild(tmp);
    }

}
