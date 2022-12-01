package net.kwas.graph.tree.binary;

import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BinaryTreeInverterTest {

    @Test
    public void invert() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(5)
            .addValue(1)
            .addValue(3);

        tree.invert();

        List<Integer> expected = List.of(5, 4, 3, 2, 1);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invert_single() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4);

        tree.invert();

        List<Integer> expected = List.of(4);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invert_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();

        tree.invert();

        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    public void invertRecursive() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4)
            .addValue(2)
            .addValue(5)
            .addValue(1)
            .addValue(3);

        tree.invertRecursive();

        List<Integer> expected = List.of(5, 4, 3, 2, 1);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invertRecursive_single() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(4);

        tree.invertRecursive();

        List<Integer> expected = List.of(4);
        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void invertRecursive_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();

        tree.invertRecursive();

        List<Integer> actual = tree.inOrderTraversal();
        Assertions.assertEquals(0, actual.size());
    }

}
