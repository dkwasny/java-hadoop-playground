package net.kwas.graph.tree.binary;

import java.util.List;

public interface BinaryTree<T extends BinaryTreeNode<T>> {

    T getHead();

    void setHead(T head);

    int getSize();

    void setSize(int size);

    BinaryTree<T> addValues(int... values);

    BinaryTree<T> addValue(int value);

    boolean contains(int value);

    List<Integer> inOrderTraversal();

    List<Integer> inOrderTraversalRecursive();

    void invert();

    void invertRecursive();

    boolean isBalanced();

    int getHeight();

}
