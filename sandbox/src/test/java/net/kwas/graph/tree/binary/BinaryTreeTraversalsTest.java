package net.kwas.graph.tree.binary;

import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeTraversalsTest {

    @Test
    public void inOrderTraversal_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = List.of();
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_singleElement() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1);
        List<Integer> expected = List.of(1);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_rightStacked() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            tree.addValue(i);
            expected.add(i);
        }
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_leftStacked() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 99; i >= 1; i--) {
            tree.addValue(i);
            expected.add(i);
        }
        Collections.sort(expected);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = List.of();
        List<Integer> actual = tree.inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_singleElement() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1);
        List<Integer> expected = List.of(1);
        List<Integer> actual = tree.inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_rightStacked() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            tree.addValue(i);
            expected.add(i);
        }
        List<Integer> actual = tree.inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_leftStacked() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        List<Integer> expected = new ArrayList<>();
        for (int i = 99; i >= 1; i--) {
            tree.addValue(i);
            expected.add(i);
        }
        Collections.sort(expected);
        List<Integer> actual = tree.inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> actual = tree.inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void preOrderTraversalRecursive_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5, 7);
        List<Integer> actual = tree.preOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void postOrderTraversalRecursive_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 3, 2, 5, 7, 6, 4);
        List<Integer> actual = tree.postOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void preOrderTraversal_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5, 7);
        List<Integer> actual = tree.preOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void postOrderTraversal_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(6)
            .addValue(1)
            .addValue(3)
            .addValue(5)
            .addValue(7);

        List<Integer> expected = List.of(1, 3, 2, 5, 7, 6, 4);
        List<Integer> actual = tree.postOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

}
