package net.kwas.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArraySearchesTest {

    @Test
    public void binarySearch_odd() {
        int[] values = { 1, 2, 3, 4, 5, 6, 7 };

        for (int value : values) {
            Assertions.assertTrue(ArraySearches.binarySearch(values, value));
            Assertions.assertFalse(ArraySearches.binarySearch(values, value * 100));
            Assertions.assertFalse(ArraySearches.binarySearch(values, value * -1));
        }
    }

    @Test
    public void binarySearch_even() {
        int[] values = { 1, 2, 3, 4, 5, 6, 7, 8 };

        for (int value : values) {
            Assertions.assertTrue(ArraySearches.binarySearch(values, value));
            Assertions.assertFalse(ArraySearches.binarySearch(values, value * 100));
            Assertions.assertFalse(ArraySearches.binarySearch(values, value * -1));
        }
    }

}
