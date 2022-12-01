package net.kwas.heap;

import java.util.Arrays;

public abstract class ComparisonHeap {

    private int[] data;
    private int size = 0;

    public abstract boolean shouldSiftUp(int value, int parentValue);
    public abstract boolean shouldSiftDown(int value, int childValue);

    public ComparisonHeap() {
        this(32);
    }

    public ComparisonHeap(int initialCapacity) {
        this.data = new int[initialCapacity];
    }

    public int[] getData() {
        return data;
    }

    public int getSize() {
        return size;
    }

    public int peek() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("No data in heap");
        }
        return data[0];
    }

    public int pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("No data in heap");
        }

        int retVal = data[0];
        data[0] = data[size - 1];
        size--;

        int currIndex = 0;
        while (currIndex >= 0 && currIndex < size) {
            int childIdx = getIdealChildIndex(currIndex);
            if (childIdx > 0 && shouldSiftDown(data[currIndex], data[childIdx])) {
                swap(currIndex, childIdx);
                currIndex = childIdx;
            }
            else {
                currIndex = -1;
            }
        }

        return retVal;
    }

    public ComparisonHeap appendValues(int... values) {
        for (int i : values) {
            appendValue(i);
        }
        return this;
    }

    public ComparisonHeap appendValue(int value) {
        ensureCapacity();

        data[size] = value;

        int currIdx = size;
        while (currIdx > 0) {
            int parentIdx = parentIndex(currIdx);
            if (shouldSiftUp(value, data[parentIdx])) {
                swap(parentIdx, currIdx);
                currIdx = parentIdx;
            }
            else {
                currIdx = -1;
            }
        }

        size++;

        return this;
    }

    private void swap(int first, int second) {
        int tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getIdealChildIndex(int index) {
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        int retVal;
        if (leftChildIndex > size && rightChildIndex > size) {
            retVal = -1;
        }
        else if (rightChildIndex > size || shouldSiftUp(data[leftChildIndex], data[rightChildIndex])) {
            retVal = leftChildIndex;
        }
        else {
            retVal = rightChildIndex;
        }
        return retVal;
    }

    private int leftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private int rightChildIndex(int index) {
        return leftChildIndex(index) + 1;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

}
