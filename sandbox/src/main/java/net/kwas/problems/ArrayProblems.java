package net.kwas.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayProblems {

    public static boolean allUniqueCharsWithSet(String string) {
        boolean retVal = true;
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < string.length() && retVal; i++) {
            char currChar = string.charAt(i);
            if (charSet.contains(currChar)) {
                retVal = false;
            }
            charSet.add(currChar);
        }
        return retVal;
    }

    public static boolean allUniqueCharsWithoutSet(String string) {
        boolean retVal = true;
        char[] array = string.toCharArray();
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1 && retVal; i++) {
            if (array[i] == array[i + 1]) {
                retVal = false;
            }
        }
        return retVal;
    }

    public static boolean isPermutationWithMap(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        Map<Character, Integer> leftCharMap = getCharMap(left);
        Map<Character, Integer> rightCharMap = getCharMap(right);
        return leftCharMap.equals(rightCharMap);
    }

    private static Map<Character, Integer> getCharMap(String string) {
        Map<Character, Integer> retVal = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char currChar = string.charAt(i);
            Integer count = retVal.getOrDefault(currChar, 0);
            count++;
            retVal.put(currChar, count);
        }
        return retVal;
    }

    public static boolean isPermutationWithoutMap(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        char[] leftChars = left.toCharArray();
        Arrays.sort(leftChars);

        char[] rightChars = right.toCharArray();
        Arrays.sort(rightChars);

        boolean retVal = true;
        for (int i = 0; i < leftChars.length && retVal; i++) {
            retVal = leftChars[i] == rightChars[i];
        }
        return retVal;
    }

    public static boolean isPalindromePermutation(String left, String right) {
       return isPermutationWithMap(left, right) && isPalindrome(right);
    }

    public static boolean isPalindrome(String string) {
        if (string.isEmpty()) {
            return false;
        }

        int leftIdx = 0;
        int rightIdx = string.length() - 1;
        boolean retVal = true;
        while (leftIdx < rightIdx && retVal) {
            char leftChar = string.charAt(leftIdx++);
            while(leftChar == ' ') {
                leftChar = string.charAt(leftIdx++);
            }
            char rightChar = string.charAt(rightIdx--);
            while(rightChar == ' ') {
                rightChar = string.charAt(rightIdx--);
            }

            if (leftChar != rightChar) {
                retVal = false;
            }
        }
        return retVal;
    }

    public static boolean isOneEditAway(String left, String right) {
        if (left.equals(right)) {
            return true;
        }

        // check for remove
        boolean retVal = false;
        if (left.length() == right.length() + 1) {
            int leftIdx = 0;
            int rightIdx = 0;
            while (leftIdx - rightIdx <= 1 && leftIdx < left.length() && rightIdx < right.length()) {
                if (left.charAt(leftIdx) == left.charAt(rightIdx)) {
                    leftIdx++;
                    rightIdx++;
                }
                else {
                    leftIdx++;
                }
            }
            retVal = leftIdx - rightIdx <= 1;
        }
        // check for insert
        else if (left.length() == right.length() - 1) {
            int leftIdx = 0;
            int rightIdx = 0;
            while (rightIdx - leftIdx <= 1 && leftIdx < left.length() && rightIdx < right.length()) {
                if (left.charAt(leftIdx) == left.charAt(rightIdx)) {
                    leftIdx++;
                    rightIdx++;
                }
                else {
                    rightIdx++;
                }
            }
            retVal = leftIdx - rightIdx <= 1;
        }
        // check for replace
        else if (left.length() == right.length()) {
            int numMismatches = 0;
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    numMismatches++;
                }
            }
            retVal = numMismatches < 2;
        }

        return retVal;
    }

    public static boolean isRotation(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }

        int leftIdx = 0;
        int rightIdx = 0;

        boolean retVal = true;

        while (leftIdx < left.length()) {
            char leftChar = left.charAt(leftIdx);
            char rightChar = right.charAt(rightIdx % right.length());

            if (rightIdx > right.length() * 2) {
                retVal = false;
                break;
            }
            else if (leftChar == rightChar) {
                leftIdx++;
            }
            else {
                leftIdx = 0;
            }
            rightIdx++;
        }

        return retVal;
    }
}
