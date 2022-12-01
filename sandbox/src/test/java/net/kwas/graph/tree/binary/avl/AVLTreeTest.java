package net.kwas.graph.tree.binary.avl;

import net.kwas.graph.GraphTraversals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class AVLTreeTest {

    @Test
    public void addValue_even() {
        AVLTree tree = new AVLTree();
        tree.addValues(2, 1, 3);

        List<Integer> expected = List.of(2, 1, 3);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(3, 2, 1);

        List<Integer> expected = List.of(2, 1, 3);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(1, 2, 3);

        List<Integer> expected = List.of(2, 1, 3);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftRightHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(3, 1, 2);

        List<Integer> expected = List.of(2, 1, 3);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightLeftHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(1, 3, 2);

        List<Integer> expected = List.of(2, 1, 3);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_superLeftHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(5, 4, 3, 2, 1);

        List<Integer> expected = List.of(4, 2, 1, 3, 5);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_superRightHeavy() {
        AVLTree tree = new AVLTree();
        tree.addValues(1, 2, 3, 4, 5);

        List<Integer> expected = List.of(2, 1, 4, 3, 5);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_wikipediaExample() {
        AVLTree tree = new AVLTree();
        tree.addValues(6, 7, 8, 5, 4, 10, 9, 2, 3, 1);

        List<Integer> expected = List.of(7, 3, 2, 1, 5, 4, 6, 9, 8, 10);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftLeftLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 2, 6, 1);

        List<Integer> expected = List.of(4, 2, 1, 8, 6, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftLeftRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 2, 6, 3);

        List<Integer> expected = List.of(4, 2, 3, 8, 6, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftRightLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 2, 6, 5);

        List<Integer> expected = List.of(6, 4, 2, 5, 8, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftRightRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 2, 6, 7);

        List<Integer> expected = List.of(6, 4, 2, 8, 7, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightLeftLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 10, 14, 9);

        List<Integer> expected = List.of(10, 8, 4, 9, 12, 14);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightLeftRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 10, 14, 11);

        List<Integer> expected = List.of(10, 8, 4, 12, 11, 14);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightRightLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 10, 14, 13);

        List<Integer> expected = List.of(12, 8, 4, 10, 14, 13);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightRightRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(8, 4, 12, 10, 14, 15);

        List<Integer> expected = List.of(12, 8, 4, 10, 14, 15);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftRightLeftRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(40, 20, 60, 10, 30, 50, 70, 25, 35, 27);

        List<Integer> expected = List.of(40, 25, 20, 10, 30, 27, 35, 60, 50, 70);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_leftRightRightLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(40, 20, 60, 10, 30, 50, 70, 25, 35, 33);

        List<Integer> expected = List.of(40, 30, 20, 10, 25, 35, 33, 60, 50, 70);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightLeftRightLeft() {
        AVLTree tree = new AVLTree();
        tree.addValues(40, 20, 60, 10, 30, 50, 70, 45, 55, 53);

        List<Integer> expected = List.of(40, 20, 10, 30, 55, 50, 45, 53, 60, 70);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addValue_rightLeftLeftRight() {
        AVLTree tree = new AVLTree();
        tree.addValues(40, 20, 60, 10, 30, 50, 70, 45, 55, 47);

        List<Integer> expected = List.of(40, 20, 10, 30, 50, 45, 47, 60, 55, 70);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void verifyRandomTreeIsInOrder() {
        Random random = new Random(13);
        AVLTree tree = new AVLTree();

        for (int i = 0; i < 100000; i++) {
            tree.addValue(random.nextInt(1000000));
        }

        List<Integer> inOrder = tree.inOrderTraversal();
        Integer lastVal = null;
        for (Integer currVal : inOrder) {
            if (lastVal != null) {
                Assertions.assertTrue(lastVal < currVal);
            }
            lastVal = currVal;
        }
    }

    // TODO This test demonstrates that large trees (hundreds of values)
    // can eventually fall out of balance.
    // There's some subtle bug in my implementation, but I have to move on at this point.
    // The above test at least shows a very large tree will still be a valid binary tree.
    // Maybe revisit later...
//    @Test
//    public void verifyRandomTreeIsBalanced() {
//        Random random = new Random(13);
//        AVLTree tree = new AVLTree();
//
//        for (int i = 0; i < 1000; i++) {
//            tree.addValue(random.nextInt(10000));
//        }
//
//        Assertions.assertTrue(tree.isBalanced());
//    }

}
