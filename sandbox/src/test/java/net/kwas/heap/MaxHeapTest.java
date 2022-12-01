package net.kwas.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxHeapTest {

    @Test
    public void appendValue_decreasing() {
        int[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        MaxHeap heap = new MaxHeap();
        heap.appendValues(expected);

        assertData(expected, heap);
    }

    @Test
    public void appendValue_increasing() {
        MaxHeap heap = new MaxHeap();
        heap.appendValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int[] expected = {10, 9, 6, 7, 8, 2, 5, 1, 4, 3};

        assertData(expected, heap);
    }

    @Test
    public void pop() {
        MaxHeap heap = new MaxHeap();
        heap.appendValues(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

        int expectedValue = 10;
        Assertions.assertEquals(expectedValue, heap.pop());

        int[] expectedData = {9, 7, 8, 3, 6, 5, 4, 1, 2};
        assertData(expectedData, heap);
    }

    @Test
    public void peek() {
        int[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        MaxHeap heap = new MaxHeap();
        heap.appendValues(expected);
        Assertions.assertEquals(10, heap.peek());
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
