package net.kwas.problems;

import net.kwas.graph.tree.binary.simple.SimpleBinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

public class GraphProblemsTest {

    @Test
    public void printDependencyPath_happyPath() {
        List<Integer> expected = List.of(5, 6, 1, 2, 4, 3);
        List<Integer> actual = GraphProblems.printDependencyPath(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(
                new AbstractMap.SimpleEntry<>(1, 4),
                new AbstractMap.SimpleEntry<>(2, 4),
                new AbstractMap.SimpleEntry<>(4, 3),
                new AbstractMap.SimpleEntry<>(6, 1),
                new AbstractMap.SimpleEntry<>(6, 2)
            )
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void printDependencyPath_noDependencies() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> actual = GraphProblems.printDependencyPath(
            List.of(1, 2, 3, 4, 5, 6),
            List.of()
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void printDependencyPath_branchingDependency() {
        List<Integer> expected = List.of(5, 6, 2, 4, 1, 3);
        List<Integer> actual = GraphProblems.printDependencyPath(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(
                new AbstractMap.SimpleEntry<>(2, 4),
                new AbstractMap.SimpleEntry<>(4, 1),
                new AbstractMap.SimpleEntry<>(4, 3),
                new AbstractMap.SimpleEntry<>(6, 1),
                new AbstractMap.SimpleEntry<>(6, 2)
            )
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void printDependencyPath_circularDependency() {
        List<Integer> expected = List.of();
        List<Integer> actual = GraphProblems.printDependencyPath(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(
                new AbstractMap.SimpleEntry<>(1, 6),
                new AbstractMap.SimpleEntry<>(2, 4),
                new AbstractMap.SimpleEntry<>(3, 4),
                new AbstractMap.SimpleEntry<>(4, 1),
                new AbstractMap.SimpleEntry<>(6, 2)
            )
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void printDependencyPath_twoCircularDependency() {
        List<Integer> expected = List.of();
        List<Integer> actual = GraphProblems.printDependencyPath(
            List.of(1, 2, 3, 4, 6, 10, 20, 40, 60),
            List.of(
                new AbstractMap.SimpleEntry<>(1, 6),
                new AbstractMap.SimpleEntry<>(2, 4),
                new AbstractMap.SimpleEntry<>(3, 4),
                new AbstractMap.SimpleEntry<>(3, 40),
                new AbstractMap.SimpleEntry<>(4, 1),
                new AbstractMap.SimpleEntry<>(6, 2),
                new AbstractMap.SimpleEntry<>(10, 60),
                new AbstractMap.SimpleEntry<>(20, 40),
                new AbstractMap.SimpleEntry<>(40, 10),
                new AbstractMap.SimpleEntry<>(60, 20)
            )
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void printBinaryTreeArrayPermutations() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(4, 2, 6, 1, 3, 5, 7);

        List<List<Integer>> lists = GraphProblems.printBinaryTreeArrayPermutations(tree);
        validatePermutations(lists, tree, 3);
    }

    @Test
    public void printBinaryTreeArrayPermutations_bigTest() {
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.addValues(50, 20, 60, 10, 25, 70, 5, 15, 65, 80);

        List<List<Integer>> lists = GraphProblems.printBinaryTreeArrayPermutations(tree);
        validatePermutations(lists, tree, 4);
    }

    private void validatePermutations(List<List<Integer>> lists, SimpleBinaryTree tree, int expectedHeight) {
        for (List<Integer> list : lists) {
            Assertions.assertEquals(tree.getSize(), list.size());
            SimpleBinaryTree newTree = new SimpleBinaryTree();
            for (Integer id : list) {
                newTree.addValue(id);
            }
            int actualHeight = newTree.getHeight();
            Assertions.assertEquals(expectedHeight, actualHeight);
        }
    }

}
