package net.kwas.problems;

import net.kwas.graph.GraphTraversals;
import net.kwas.graph.list.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LinkedListProblemsTest {

    @Test
    public void removeDupesWithSet() {
        LinkedList list = new LinkedList()
            .addValues(1, 2, 3, 4, 1, 2, 5, 6, 1);

        LinkedListProblems.removeDupesWithSet(list);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(list.getHead());
        Assertions.assertEquals(expected, actual);
    }

}
