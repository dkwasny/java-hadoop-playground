package net.kwas.graph.tree.binary;

import net.kwas.graph.Node;

public interface BinaryTreeNode<T extends BinaryTreeNode<T>> extends Node {

    T getParent();
    void setParent(T parent);

    T getLeftChild();
    void setLeftChild(T leftChild);
    T withLeftChild(T leftChild);

    T getRightChild();
    void setRightChild(T rightChild);
    T withRightChild(T rightChild);

}
