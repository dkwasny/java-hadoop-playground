package net.kwas.graph.tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BinaryTreeInverterTest {

    @Test
    public void invert() {
        BinaryTree tree = new BinaryTree()
            .addValue(4)
            .addValue(2)
            .addValue(5)
            .addValue(1)
            .addValue(3);

        BinaryTreeInverter.invert(tree);

        List<Integer> expected = List.of(5, 4, 3, 2, 1);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invert_single() {
        BinaryTree tree = new BinaryTree()
            .addValue(4);

        BinaryTreeInverter.invert(tree);

        List<Integer> expected = List.of(4);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invert_empty() {
        BinaryTree tree = new BinaryTree();

        BinaryTreeInverter.invert(tree);

        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    public void invertRecursive() {
        BinaryTree tree = new BinaryTree()
            .addValue(4)
            .addValue(2)
            .addValue(5)
            .addValue(1)
            .addValue(3);

        BinaryTreeInverter.invertRecursive(tree);

        List<Integer> expected = List.of(5, 4, 3, 2, 1);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invertRecursive_single() {
        BinaryTree tree = new BinaryTree()
            .addValue(4);

        BinaryTreeInverter.invertRecursive(tree);

        List<Integer> expected = List.of(4);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invertRecursive_empty() {
        BinaryTree tree = new BinaryTree();

        BinaryTreeInverter.invertRecursive(tree);

        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(0, actual.size());
    }

}
