package net.kwas.graph.tree.binary;

import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import net.kwas.graph.tree.binary.simple.SimpleBinaryTreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    @Test
    public void add_ascending() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1)
            .addValue(2)
            .addValue(3);

        SimpleBinaryTreeNode currNode = tree.getHead();
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
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(3)
            .addValue(2)
            .addValue(1);

        SimpleBinaryTreeNode currNode = tree.getHead();
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

    @Test
    public void isBalanced_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void isBalanced_singleElement() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1);
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void isBalanced_sameDepth() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 1, 3, 5, 7);
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void isBalanced_offByOneLeft() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 1);
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void isBalanced_offByOneRight() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 5);
        Assertions.assertTrue(tree.isBalanced());
    }

    @Test
    public void isBalanced_offByTwoLeft() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(40, 20, 60, 3, 2);
        Assertions.assertFalse(tree.isBalanced());
    }

    @Test
    public void isBalanced_offByTwoRight() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(40, 20, 60, 70, 71);
        Assertions.assertFalse(tree.isBalanced());
    }

    @Test
    public void getHeight_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        Assertions.assertEquals(0, tree.getHeight());
    }

    @Test
    public void getHeight_singleElement() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(1);
        Assertions.assertEquals(1, tree.getHeight());
    }

    @Test
    public void getHeight_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 1, 3, 5, 7);
        Assertions.assertEquals(3, tree.getHeight());
    }

    @Test
    public void getHeight_leftHeavy() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 1, 3);
        Assertions.assertEquals(3, tree.getHeight());
    }

    @Test
    public void getHeight_rightHeavy() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 5, 7);
        Assertions.assertEquals(3, tree.getHeight());
    }

    @Test
    public void getHeight_reallyLeftHeavy() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(40, 20, 60, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        Assertions.assertEquals(12, tree.getHeight());
    }

    @Test
    public void getHeight_reallyRightHeavy() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(40, 20, 60, 10, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70);
        Assertions.assertEquals(12, tree.getHeight());
    }
}
