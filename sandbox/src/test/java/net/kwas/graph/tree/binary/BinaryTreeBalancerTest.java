package net.kwas.graph.tree.binary;

import net.kwas.graph.GraphTraversals;
import net.kwas.graph.tree.binary.simple.BinaryTreeBalancer;
import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BinaryTreeBalancerTest {

    @Test
    public void arrayBalance_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalance(tree);
        Assertions.assertEquals(0, balanced.getSize());
    }

    @Test
    public void arrayBalance_single() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1);
        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalance(tree);
        List<Integer> expected = List.of(1);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalance_odd() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1)
            .addValue(2)
            .addValue(3)
            .addValue(4)
            .addValue(5)
            .addValue(6)
            .addValue(7);

        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalance(tree);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5, 7);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalance_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1)
            .addValue(2)
            .addValue(3)
            .addValue(4)
            .addValue(5)
            .addValue(6);

        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalance(tree);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalanceRecursive_empty() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalanceRecursive(tree);
        Assertions.assertEquals(0, balanced.getSize());
    }

    @Test
    public void arrayBalanceRecursive_single() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1);
        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalanceRecursive(tree);
        List<Integer> expected = List.of(1);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalanceRecursive_odd() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1)
            .addValue(2)
            .addValue(3)
            .addValue(4)
            .addValue(5)
            .addValue(6)
            .addValue(7);

        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalanceRecursive(tree);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5, 7);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalanceRecursive_even() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValue(1)
            .addValue(2)
            .addValue(3)
            .addValue(4)
            .addValue(5)
            .addValue(6);

        SimpleBinaryTree balanced = BinaryTreeBalancer.arrayBalanceRecursive(tree);

        List<Integer> expected = List.of(4, 2, 1, 3, 6, 5);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(balanced.getHead());
        Assertions.assertEquals(expected, actual);
    }

}
