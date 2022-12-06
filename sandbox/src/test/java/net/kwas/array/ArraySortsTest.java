package net.kwas.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ArraySortsTest {

    @Test
    public void heapSort_fullArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArraySorts.heapSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void heapSort_fullArray_odd() {
        int[] array = {8, 4, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArraySorts.heapSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void heapSort_partialArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {2, 3, 4, 5, 8, 10, 7, 1, 6, 9};
        ArraySorts.heapSort(array, 6);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void heapSort_partialArray_odd() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {3, 4, 5, 8, 10, 2, 7, 1, 6, 9};
        ArraySorts.heapSort(array, 5);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void heapSort_bigRandomArray() {
        int[] array = getRandomArray();
        ArraySorts.heapSort(array);
        verifyArray(array);
    }

    @Test
    public void heapSort_bigSortedArray() {
        int[] array = getSortedArray();
        ArraySorts.heapSort(array);
        verifyArray(array);
    }

    @Test
    public void mergeSort_fullArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArraySorts.mergeSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void mergeSort_fullArray_odd() {
        int[] array = {8, 4, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArraySorts.mergeSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void mergeSort_partialArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {2, 3, 4, 5, 8, 10, 7, 1, 6, 9};
        ArraySorts.mergeSort(array, 6);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void mergeSort_partialArray_odd() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {3, 4, 5, 8, 10, 2, 7, 1, 6, 9};
        ArraySorts.mergeSort(array, 5);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void mergeSort_bigRandomArray() {
        int[] array = getRandomArray();
        ArraySorts.mergeSort(array);
        verifyArray(array);
    }

    @Test
    public void mergeSort_bigSortedArray() {
        int[] array = getSortedArray();
        ArraySorts.mergeSort(array);
        verifyArray(array);
    }

    @Test
    public void quickSort_fullArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArraySorts.quickSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void quickSort_fullArray_odd() {
        int[] array = {8, 4, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArraySorts.quickSort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void quickSort_partialArray_even() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {2, 3, 4, 5, 8, 10, 7, 1, 6, 9};
        ArraySorts.quickSort(array, 6);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void quickSort_partialArray_odd() {
        int[] array = {8, 4, 10, 3, 5, 2, 7, 1, 6, 9};
        int[] expected = {3, 4, 5, 8, 10, 2, 7, 1, 6, 9};
        ArraySorts.quickSort(array, 5);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    public void quickSort_bigRandomArray() {
        int[] array = getRandomArray();
        ArraySorts.mergeSort(array);
        verifyArray(array);
    }

    @Test
    public void quickSort_bigSortedArray() {
        int[] array = getSortedArray();
        ArraySorts.quickSort(array);
        verifyArray(array);
    }

    private void verifyArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            Assertions.assertTrue(array[i] <= array[i + 1]);
        }
    }

    private int[] getRandomArray() {
        int size = 1000000;
        int[] retVal = new int[size];

        Random random = new Random(13);
        for (int i = 0; i < size; i++) {
            retVal[i] = random.nextInt();
        }

        return retVal;
    }

    private int[] getSortedArray() {
        int size = 1000000;
        int[] retVal = new int[size];

        Random random = new Random(13);
        for (int i = 0; i < size; i++) {
            retVal[i] = i;
        }

        return retVal;
    }

}
