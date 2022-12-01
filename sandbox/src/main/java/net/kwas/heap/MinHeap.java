package net.kwas.heap;

public class MinHeap extends ComparisonHeap {

    public MinHeap() {
    }

    public MinHeap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean shouldSiftUp(int value, int parentValue) {
        return value < parentValue;
    }

    @Override
    public boolean shouldSiftDown(int value, int childValue) {
        return value > childValue;
    }

}
