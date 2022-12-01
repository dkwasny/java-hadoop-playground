package net.kwas.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinHeapTest {

    @Test
    public void appendValue_increasing() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MinHeap heap = new MinHeap();
        heap.appendValues(expected);

        assertData(expected, heap);
    }

    @Test
    public void appendValue_decreasing() {
        MinHeap heap = new MinHeap();
        heap.appendValues(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        int[] expected = {1, 2, 5, 4, 3, 9, 6, 10, 7, 8};

        assertData(expected, heap);
    }

    @Test
    public void pop() {
        MinHeap heap = new MinHeap();
        heap.appendValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int expectedValue = 1;
        Assertions.assertEquals(expectedValue, heap.pop());

        int[] expectedData = {2, 4, 3, 8, 5, 6, 7, 10, 9};
        assertData(expectedData, heap);
    }

    @Test
    public void peek() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MinHeap heap = new MinHeap();
        heap.appendValues(expected);
        Assertions.assertEquals(1, heap.peek());
        assertData(expected, heap);
    }

    private void assertData(int[] expected, ComparisonHeap actual) {
        Assertions.assertEquals(expected.length, actual.getSize());
        int[] actualData = actual.getData();
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertEquals(expected[i], actualData[i]);
        }
    }

}
