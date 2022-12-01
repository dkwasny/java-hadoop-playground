package net.kwas.graph.tree.binary;

import net.kwas.graph.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBinaryTreeNode<T extends BinaryTreeNode<T>> implements BinaryTreeNode<T> {

    private final int id;

    private T parent = null;
    private T leftChild = null;
    private T rightChild = null;

    public BaseBinaryTreeNode(int id) {
        this.id = id;
    }

    @Override
    public T withLeftChild(T child) {
        child.setParent((T)this);
        setLeftChild(child);
        return (T)this;
    }

    @Override
    public T withRightChild(T child) {
        child.setParent((T)this);
        setRightChild(child);
        return (T)this;
    }

    @Override
    public T getParent() {
        return parent;
    }

    @Override
    public void setParent(T parent) {
        this.parent = parent;
    }

    @Override
    public T getLeftChild() {
        return leftChild;
    }

    @Override
    public void setLeftChild(T leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public T getRightChild() {
        return rightChild;
    }

    @Override
    public void setRightChild(T rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Node> getNeighbors() {
        List<Node> retVal = new ArrayList<>();
        if (getLeftChild() != null) {
            retVal.add(getLeftChild());
        }
        if (getRightChild() != null) {
            retVal.add(getRightChild());
        }
        if (getParent() != null) {
            retVal.add(getParent());
        }
        return retVal;
    }

}
