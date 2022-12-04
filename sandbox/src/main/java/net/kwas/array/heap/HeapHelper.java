package net.kwas.array.heap;

import net.kwas.array.ArrayUtils;

import java.util.function.BiFunction;

public class HeapHelper {

    private static final HeapHelper MIN_HELPER = new HeapHelper(
        (x, y) -> x > y,
        (x, y) -> x < y
    );
    public static HeapHelper getMinHeapHelper() {
        return MIN_HELPER;
    }

    private static final HeapHelper MAX_HELPER = new HeapHelper(
        (x, y) -> x < y,
        (x, y) -> x > y
    );
    public static HeapHelper getMaxHeapHelper() {
        return MAX_HELPER;
    }

    private final BiFunction<Integer, Integer, Boolean> shouldSiftDown;
    private final BiFunction<Integer, Integer, Boolean> shouldSiftUp;

    public HeapHelper(BiFunction<Integer, Integer, Boolean> shouldSiftDown, BiFunction<Integer, Integer, Boolean> shouldSiftUp) {
        this.shouldSiftDown = shouldSiftDown;
        this.shouldSiftUp = shouldSiftUp;
    }

    public void heapify(int[] array) {
        heapify(array, array.length);
    }

    public void heapify(int[] array, int size) {
        for (int i = size - 1; i >= 0; i--) {
            siftDown(i, array, size);
        }
    }

    public void siftUp(int index, int[] array) {
        int currIdx = index;
        while (currIdx > 0) {
            int parentIdx = parentIndex(currIdx);
            if (shouldSiftUp.apply(array[currIdx], array[parentIdx])) {
                ArrayUtils.swap(array, parentIdx, currIdx);
                currIdx = parentIdx;
            }
            else {
                currIdx = -1;
            }
        }
    }

    public void siftDown(int index, int[] array, int size) {
        int currIndex = index;
        while (currIndex >= 0 && currIndex < size) {
            int childIdx = getIdealChildIndex(currIndex, array, size);
            if (childIdx > 0 && shouldSiftDown.apply(array[currIndex], array[childIdx])) {
                ArrayUtils.swap(array, currIndex, childIdx);
                currIndex = childIdx;
            }
            else {
                currIndex = -1;
            }
        }
    }

    public int parentIndex(int index) {
        return (index - 1) / 2;
    }

    public int getIdealChildIndex(int index, int[] array, int size) {
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        int retVal;
        if (leftChildIndex >= size && rightChildIndex >= size) {
            retVal = -1;
        }
        else if (rightChildIndex >= size || shouldSiftUp.apply(array[leftChildIndex], array[rightChildIndex])) {
            retVal = leftChildIndex;
        }
        else {
            retVal = rightChildIndex;
        }
        return retVal;
    }

    public int leftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int rightChildIndex(int index) {
        return leftChildIndex(index) + 1;
    }

}
