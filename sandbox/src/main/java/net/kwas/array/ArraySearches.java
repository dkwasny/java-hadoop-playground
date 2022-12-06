package net.kwas.array;

public class ArraySearches {

    public static boolean binarySearch(int[] array, int value) {
        boolean retVal = false;

        int start = 0;
        int end = array.length - 1;

        while (start <= end && !retVal) {
            int middle = (start + end) / 2;
            int middleVal = array[middle];

            if (value == middleVal) {
                retVal = true;
            }
            else if (value > middleVal) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }


        return retVal;
    }

}
