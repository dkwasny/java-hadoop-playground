package net.kwas.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphUtilsTest {

    @Test
    public void depthFirstSearchRecursive() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = GraphUtils.depthFirstSearchRecursive(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void depthFirstSearch() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 4, 5, 9, 10, 3, 6, 7, 8);
        List<Integer> actual = GraphUtils.depthFirstSearch(tree);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void breadthFirstSearch() {
        Node tree = getTree();
        List<Integer> expected = List.of(1, 2, 3, 6, 4, 5, 7, 8, 9, 10);
        List<Integer> actual = GraphUtils.breadthFirstSearch(tree);
        Assertions.assertEquals(expected, actual);
    }

    private Node getTree() {
        return new Node(1)
            .withNeighbor(new Node(2)
                .withNeighbor(new Node(4))
                .withNeighbor(new Node(5)
                    .withNeighbor(new Node(9))
                    .withNeighbor(new Node(10))
                )
            )
            .withNeighbor(new Node(3))
            .withNeighbor(new Node(6)
                .withNeighbor(new Node(7))
                .withNeighbor(new Node(8))
            );
    }
}
