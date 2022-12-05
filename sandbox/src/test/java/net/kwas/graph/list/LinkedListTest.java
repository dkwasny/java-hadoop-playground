package net.kwas.graph.list;

import net.kwas.graph.GraphTraversals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LinkedListTest {

    @Test
    public void addValue() {
        LinkedList list = new LinkedList()
            .addValues(1, 2, 3, 4, 5, 6);

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> actual = GraphTraversals.depthFirstTraversal(list.getHead());
        Assertions.assertEquals(expected, actual);
    }

}
