package net.kwas.heap;

public class MaxHeap extends ComparisonHeap {

    public MaxHeap() {
        super();
    }

    public MaxHeap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean shouldSiftUp(int value, int parentValue) {
        return value > parentValue;
    }

    @Override
    public boolean shouldSiftDown(int value, int childValue) {
        return value < childValue;
    }

}
