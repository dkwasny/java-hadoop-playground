package net.kwas.graph.tree.binary.avl;

import net.kwas.graph.Node;
import net.kwas.graph.tree.binary.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeNode implements Node {

    private int balance = 0;

    private final int id;

    private AVLTreeNode parent = null;
    private AVLTreeNode leftChild = null;
    private AVLTreeNode rightChild = null;

    public AVLTreeNode(int id) {
        this.id = id;
    }

    public AVLTreeNode withLeftChild(AVLTreeNode child) {
        child.setParent(this);
        this.leftChild = child;
        return this;
    }

    public AVLTreeNode withRightChild(AVLTreeNode child) {
        child.setParent(this);
        this.rightChild = child;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public List<Node> getNeighbors() {
        List<Node> retVal = new ArrayList<>();
        if (leftChild != null) {
            retVal.add(leftChild);
        }
        if (rightChild != null) {
            retVal.add(rightChild);
        }
        if (parent != null) {
            retVal.add(parent);
        }
        return retVal;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AVLTreeNode getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }

    public AVLTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public AVLTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
            "balance=" + balance +
            ", id=" + id +
            '}';
    }
}
