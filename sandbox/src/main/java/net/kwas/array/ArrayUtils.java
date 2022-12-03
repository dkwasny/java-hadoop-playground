package net.kwas.array;

import java.util.Arrays;

public class ArrayUtils {

    public static int[] ensureCapacity(int[] array, int size) {
        int[] retVal = array;
        if (size == array.length) {
            retVal = Arrays.copyOf(array, array.length * 2);
        }
        return retVal;
    }

    public static void swap(int[] array, int first, int second) {
        int tmp = array[first];
        array[first] = array[second];
        array[second] = tmp;
    }

}
