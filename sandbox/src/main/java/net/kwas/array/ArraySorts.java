package net.kwas.array;

import net.kwas.array.ArrayUtils;
import net.kwas.array.heap.HeapHelper;

import java.util.Arrays;

public class ArraySorts {

    public static void heapSort(int[] array) {
        heapSort(array, array.length);
    }

    public static void heapSort(int[] array, int size) {
        HeapHelper maxHeapHelper = HeapHelper.getMaxHeapHelper();
        maxHeapHelper.heapify(array, size);

        for (int i = size; i > 0; i--) {
            ArrayUtils.swap(array, 0, i - 1);
            maxHeapHelper.siftDown(0, array, i - 1);
        }
    }

    public static void quickSort(int[] array) {
        quickSort(array, array.length);
    }

    public static void quickSort(int[] array, int size) {
        quickSort(array, 0, size - 1);
    }

    private static void quickSort(int[] array, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }

        int midIdx = (endIdx - startIdx) / 2 + startIdx;

        int leftIdx = startIdx - 1;
        int rightIdx = endIdx + 1;
        int pivotVal = array[midIdx];

        while (leftIdx < rightIdx) {
            while (array[++leftIdx] < pivotVal) { }

            while (array[--rightIdx] > pivotVal) { }

            if (leftIdx < rightIdx) {
                ArrayUtils.swap(array, leftIdx, rightIdx);
            }
        }

        quickSort(array, startIdx, rightIdx);
        quickSort(array, rightIdx + 1, endIdx);
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, array.length);
    }

    public static void mergeSort(int[] array, int size) {
        int[] workArray = Arrays.copyOf(array, size);
        mergeSort(workArray, array, 0, size - 1);
    }

    private static void mergeSort(int[] array, int[] workArray, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }

        int midIdx = (endIdx - startIdx) / 2 + startIdx;

        int leftStartIdx = startIdx;
        int leftEndIdx = midIdx;
        int rightStartIdx = midIdx + 1;
        int rightEndIdx = endIdx;

        mergeSort(workArray, array, leftStartIdx, leftEndIdx);
        mergeSort(workArray, array, rightStartIdx, rightEndIdx);

        int workIdx = leftStartIdx;
        int leftIdx = leftStartIdx;
        int rightIdx = rightStartIdx;
        while (workIdx <= rightEndIdx) {
            if (leftIdx <= leftEndIdx && rightIdx <= rightEndIdx) {
                int leftVal = array[leftIdx];
                int rightVal = array[rightIdx];

                if (leftVal <= rightVal) {
                    workArray[workIdx] = leftVal;
                    leftIdx++;
                }
                else {
                    workArray[workIdx] = rightVal;
                    rightIdx++;
                }
            }
            else if (leftIdx <= leftEndIdx) {
                workArray[workIdx] = array[leftIdx];
                leftIdx++;
            }
            else {
                workArray[workIdx] = array[rightIdx];
                rightIdx++;
            }

            workIdx++;
        }
    }

}
