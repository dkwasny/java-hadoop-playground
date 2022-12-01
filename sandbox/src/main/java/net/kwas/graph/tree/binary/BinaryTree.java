package net.kwas.graph.tree.binary;

public class BinaryTree {

    private BinaryTreeNode head;
    private int size = 0;

    public BinaryTreeNode getHead() {
        return head;
    }

    public void setHead(BinaryTreeNode head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public BinaryTree addValues(int... values) {
        for (int value : values) {
            addValue(value);
        }
        return this;
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

    public boolean contains(int value) {
        boolean retVal = false;
        BinaryTreeNode currNode = head;
        while (!retVal && currNode != null) {
            if (value == currNode.getId()) {
                retVal = true;
            }
            else if (value < currNode.getId()) {
                currNode = currNode.getLeftChild();
            }
            else {
                currNode = currNode.getRightChild();
            }
        }
        return retVal;
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
