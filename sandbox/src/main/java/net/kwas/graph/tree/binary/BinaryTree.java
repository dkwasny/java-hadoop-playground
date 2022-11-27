package net.kwas.graph.tree.binary;

public class BinaryTree {

    private BinaryTreeNode head;
    private int size = 0;

    public BinaryTreeNode getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public BinaryTree addValue(int value) {
        if (head == null) {
            head = new BinaryTreeNode(value);
        }
        else {
            addChild(value);
        }
        size++;
        return this;
    }

    private BinaryTreeNode addChild(int value) {
        BinaryTreeNode retVal = null;
        BinaryTreeNode currNode = head;
        while (retVal == null) {
            int currValue = currNode.getId();
            if (value == currValue) {
                break;
            }
            else if (value < currValue) {
                if (currNode.getLeftChild() == null) {
                    retVal = currNode.withLeftChild(new BinaryTreeNode(value));
                }
                else {
                    currNode = currNode.getLeftChild();
                }
            }
            else {
                if (currNode.getRightChild() == null) {
                    retVal = currNode.withRightChild(new BinaryTreeNode(value));
                }
                else {
                    currNode = currNode.getRightChild();
                }
            }
        }

        return retVal;
    }

}
