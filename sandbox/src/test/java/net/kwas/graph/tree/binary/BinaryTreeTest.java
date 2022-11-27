package net.kwas.graph.tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    @Test
    public void add_ascending() {
        BinaryTree tree = new BinaryTree()
            .addValue(1)
            .addValue(2)
            .addValue(3);

        BinaryTreeNode currNode = tree.getHead();
        Assertions.assertEquals(1, currNode.getId());
        Assertions.assertNull(currNode.getLeftChild());

        currNode = currNode.getRightChild();
        Assertions.assertEquals(2, currNode.getId());
        Assertions.assertNull(currNode.getLeftChild());

        currNode = currNode.getRightChild();
        Assertions.assertEquals(3, currNode.getId());
        Assertions.assertNull(currNode.getLeftChild());
        Assertions.assertNull(currNode.getRightChild());

        Assertions.assertEquals(3, tree.getSize());
    }

    @Test
    public void add_descending() {
        BinaryTree tree = new BinaryTree()
            .addValue(3)
            .addValue(2)
            .addValue(1);

        BinaryTreeNode currNode = tree.getHead();
        Assertions.assertEquals(3, currNode.getId());
        Assertions.assertNull(currNode.getRightChild());

        currNode = currNode.getLeftChild();
        Assertions.assertEquals(2, currNode.getId());
        Assertions.assertNull(currNode.getRightChild());

        currNode = currNode.getLeftChild();
        Assertions.assertEquals(1, currNode.getId());
        Assertions.assertNull(currNode.getLeftChild());
        Assertions.assertNull(currNode.getRightChild());

        Assertions.assertEquals(3, tree.getSize());
    }

}
