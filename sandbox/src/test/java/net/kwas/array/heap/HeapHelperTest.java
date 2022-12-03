package net.kwas.array.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapHelperTest {

    private final HeapHelper helper = HeapHelper.getMaxHeapHelper();

    @Test
    public void heapify_fullArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        helper.heapify(array);
        int[] expected = {7, 5, 6, 4, 2, 1, 3};
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void heapify_partialArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        helper.heapify(array, 7);
        int[] expected = {7, 5, 6, 4, 2, 1, 3, 8, 9, 10};
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void siftDown() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        helper.siftDown(0, array, 7);
        int[] expected = {3, 2, 7, 4, 5, 6, 1};
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void siftUp() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        helper.siftUp(6, array);
        int[] expected = {7, 2, 1, 4, 5, 6, 3};
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void parentIndex_fromLeft() {
        Assertions.assertEquals(2, helper.parentIndex(5));
    }

    @Test
    public void parentIndex_fromRight() {
        Assertions.assertEquals(2, helper.parentIndex(6));
    }

    @Test
    public void getIdealChildIndex_left() {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int expected = 5;
        int actual = HeapHelper.getMaxHeapHelper().getIdealChildIndex(2, array, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getIdealChildIndex_right() {
        int[] array = {10, 9, 8, 7, 6, 4, 5, 3, 2, 1};
        int expected = 6;
        int actual = HeapHelper.getMaxHeapHelper().getIdealChildIndex(2, array, 10);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void leftChildIndex() {
        Assertions.assertEquals(5, helper.leftChildIndex(2));
    }

    @Test
    public void rightChildIndex() {
        Assertions.assertEquals(6, helper.rightChildIndex(2));
    }

}
