package net.kwas.array.heap;

import net.kwas.array.ArrayUtils;

public class ComparisonHeap {

    public static ComparisonHeap newMinHeap() {
        return new ComparisonHeap(HeapHelper.getMinHeapHelper());
    }
    public static ComparisonHeap newMinHeap(int initialCapacity) {
        return new ComparisonHeap(HeapHelper.getMinHeapHelper(), initialCapacity);
    }

    public static ComparisonHeap newMaxHeap() {
        return new ComparisonHeap(HeapHelper.getMaxHeapHelper());
    }
    public static ComparisonHeap newMaxHeap(int initialCapacity) {
        return new ComparisonHeap(HeapHelper.getMaxHeapHelper(), initialCapacity);
    }

    private final HeapHelper helper;
    private int[] data;
    private int size;

    public ComparisonHeap(HeapHelper helper) {
        this(helper, 32);
    }

    public ComparisonHeap(HeapHelper helper, int initialCapacity) {
        this(helper, new int[initialCapacity], 0);
    }

    public ComparisonHeap(HeapHelper helper, int[] data, int size) {
        this.helper = helper;
        this.data = data;
        this.size = size;
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
        helper.siftDown(0, data, size);

        return retVal;
    }

    public ComparisonHeap appendValues(int... values) {
        for (int i : values) {
            appendValue(i);
        }
        return this;
    }

    public ComparisonHeap appendValue(int value) {
        data = ArrayUtils.ensureCapacity(data, size);
        data[size] = value;
        helper.siftUp(size, data);
        size++;
        return this;
    }

}
