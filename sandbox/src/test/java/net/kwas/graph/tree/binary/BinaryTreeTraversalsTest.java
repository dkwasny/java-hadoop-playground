package net.kwas.graph.tree.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeTraversalsTest {

    @Test
    public void inOrderTraversal_empty() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = List.of();
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_singleElement() {
        BinaryTree tree = new BinaryTree().addValue(1);
        List<Integer> expected = List.of(1);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_rightStacked() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            tree.addValue(i);
            expected.add(i);
        }
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_leftStacked() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 99; i >= 1; i--) {
            tree.addValue(i);
            expected.add(i);
        }
        Collections.sort(expected);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_even() {
        BinaryTree tree = new BinaryTree()
            .addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversal(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_empty() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = List.of();
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_singleElement() {
        BinaryTree tree = new BinaryTree().addValue(1);
        List<Integer> expected = List.of(1);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_rightStacked() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            tree.addValue(i);
            expected.add(i);
        }
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_leftStacked() {
        BinaryTree tree = new BinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 99; i >= 1; i--) {
            tree.addValue(i);
            expected.add(i);
        }
        Collections.sort(expected);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_even() {
        BinaryTree tree = new BinaryTree()
            .addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> actual = BinaryTreeTraversals.inOrderTraversalRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

}
