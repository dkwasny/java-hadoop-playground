package net.kwas.graph.tree.binary;

import net.kwas.graph.GraphSearches;
import net.kwas.graph.GraphTraversals;
import net.kwas.graph.tree.binary.simple.BinaryTreeBalancer;
import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BinaryTreeIntegrationTest {

    @Test
    public void depthFirstTraversalRecursive() {
        List<Integer> expected = List.of(8, 4, 3, 2, 1, 6, 5, 7, 11, 9, 10, 12, 13);
        List<Integer> actual = GraphTraversals.depthFirstTraversalRecursive(getTree().getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstTraversal() {
        List<Integer> expected = List.of(8, 4, 3, 2, 1, 6, 5, 7, 11, 9, 10, 12, 13);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(getTree().getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstTraversal() {
        List<Integer> expected = List.of(8, 4, 11, 3, 6, 9, 12, 2, 5, 7, 10, 13, 1);
        List<Integer> actual = GraphTraversals.breadthFirstTraversal(getTree().getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversalRecursive() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        List<Integer> actual = getTree().inOrderTraversalRecursive();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inOrderTraversal() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        List<Integer> actual = getTree().inOrderTraversal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearchRecursive() {
        List<Integer> expected = List.of(8, 11, 9, 10);
        List<Integer> actual = GraphSearches.depthFirstSearchRecursive(getTree().getHead(), 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearch() {
        List<Integer> expected = List.of(8, 11, 9, 10);
        List<Integer> actual = GraphSearches.depthFirstSearch(getTree().getHead(), 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstSearch() {
        List<Integer> expected = List.of(8, 11, 9, 10);
        List<Integer> actual = GraphSearches.breadthFirstSearch(getTree().getHead(), 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalance() {
        SimpleBinaryTree tree = BinaryTreeBalancer.arrayBalance(getTree());
        List<Integer> expected = List.of(7, 4, 2, 1, 3, 6, 5, 11, 9, 8, 10, 13, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void arrayBalanceRecursive() {
        SimpleBinaryTree tree = BinaryTreeBalancer.arrayBalanceRecursive(getTree());
        List<Integer> expected = List.of(7, 4, 2, 1, 3, 6, 5, 11, 9, 8, 10, 13, 12);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(tree.getHead());
        Assertions.assertEquals(expected, actual);
    }

    private SimpleBinaryTree getTree() {
        SimpleBinaryTree retVal =  new SimpleBinaryTree();
        retVal.addValue(8)
            .addValue(4)
            .addValue(11)
            .addValue(3)
            .addValue(6)
            .addValue(9)
            .addValue(12)
            .addValue(2)
            .addValue(5)
            .addValue(7)
            .addValue(10)
            .addValue(13)
            .addValue(1);
        return retVal;
    }

}
