package net.kwas.graph.tree.binary;

import net.kwas.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode implements Node {

    private final int id;
    private BinaryTreeNode parent = null;
    private BinaryTreeNode leftChild = null;
    private BinaryTreeNode rightChild = null;

    public BinaryTreeNode(int id) {
        this.id = id;
    }

    public BinaryTreeNode withLeftChild(BinaryTreeNode child) {
        child.setParent(this);
        this.leftChild = child;
        return this;
    }

    public BinaryTreeNode withRightChild(BinaryTreeNode child) {
        child.setParent(this);
        this.rightChild = child;
        return this;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
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
}
