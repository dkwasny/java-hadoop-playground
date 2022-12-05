package net.kwas.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class ArrayProblemsTest {

    @Test
    public void allUniqueCharacters() {
        List<String> passes = List.of(
            "asdf",
            "fdsa",
            "kwas",
            "d",
            ""
        );
        for (String string : passes) {
            Assertions.assertTrue(ArrayProblems.allUniqueCharsWithSet(string));
            Assertions.assertTrue(ArrayProblems.allUniqueCharsWithoutSet(string));
        }

        List<String> fails = List.of(
            "asdfa",
            "aasdf",
            "david",
            "asdff",
            "asdsf",
            "aa"
        );
        for (String string : fails) {
            Assertions.assertFalse(ArrayProblems.allUniqueCharsWithSet(string));
            Assertions.assertFalse(ArrayProblems.allUniqueCharsWithoutSet(string));
        }
    }

    @Test
    public void isPermutationWithMap() {
        List<Map.Entry<String, String>> passes = List.of(
            new AbstractMap.SimpleEntry<>("asdf", "adsf"),
            new AbstractMap.SimpleEntry<>("asdf", "fdsa"),
            new AbstractMap.SimpleEntry<>("asdf", "dsfa"),
            new AbstractMap.SimpleEntry<>("asdf", "afds")
        );
        for (Map.Entry<String , String> entry : passes) {
            Assertions.assertTrue(ArrayProblems.isPermutationWithMap(entry.getKey(), entry.getValue()));
            Assertions.assertTrue(ArrayProblems.isPermutationWithoutMap(entry.getKey(), entry.getValue()));
        }

        List<Map.Entry<String, String>> fails = List.of(
            new AbstractMap.SimpleEntry<>("asdf", "adsff"),
            new AbstractMap.SimpleEntry<>("asdf", "asdfg"),
            new AbstractMap.SimpleEntry<>("asdf", "qwer"),
            new AbstractMap.SimpleEntry<>("asdf", "azxcv"),
            new AbstractMap.SimpleEntry<>("asdf", "asd"),
            new AbstractMap.SimpleEntry<>("asdf", "sdf")
        );
        for (Map.Entry<String , String> entry : fails) {
            Assertions.assertFalse(ArrayProblems.isPermutationWithMap(entry.getKey(), entry.getValue()));
            Assertions.assertFalse(ArrayProblems.isPermutationWithoutMap(entry.getKey(), entry.getValue()));
        }
    }

    @Test
    public void isPalindrome() {
        List<String> passes = List.of(
            "asdffdsa",
            "asdfdsa",
            "asddsa",
            "aa",
            "a"
        );
        for (String string : passes) {
            Assertions.assertTrue(ArrayProblems.isPalindrome(string));
        }

        List<String> fails = List.of(
            "asdfddsa",
            "asdsaa",
            "aasdfdsa",
            ""
        );
        for (String string : fails) {
            Assertions.assertFalse(ArrayProblems.isPalindrome(string));
        }
    }

    @Test
    public void isPalindromePermutation() {
        String input = "asd asd";

        List<String> passes = List.of(
            "as ddsa",
            "a sddsa",
            "sadda s",
            "sadd as",
            "das sad"
        );
        for (String string : passes) {
            Assertions.assertTrue(ArrayProblems.isPalindromePermutation(input, string), "Failed: " + string);
        }

        List<String> fails = List.of(
            "asdsda",
            "aassdd",
            "adsasd",
            "adds as",
            "asdsddsdsa",
            "a",
            ""
        );
        for (String string : fails) {
            Assertions.assertFalse(ArrayProblems.isPalindromePermutation(input, string));
        }
    }

    @Test
    public void isOneEditAway() {
        List<Map.Entry<String, String>> passes = List.of(
            new AbstractMap.SimpleEntry<>("asdf", "adf"),
            new AbstractMap.SimpleEntry<>("asdfg", "asdf"),
            new AbstractMap.SimpleEntry<>("asdf", "qsdf")
        );
        for (Map.Entry<String , String> entry : passes) {
            Assertions.assertTrue(ArrayProblems.isOneEditAway(entry.getKey(), entry.getValue()), "Failed: " + entry);
        }

        List<Map.Entry<String, String>> fails = List.of(
            new AbstractMap.SimpleEntry<>("asdf", "qsef"),
            new AbstractMap.SimpleEntry<>("asdf", "asdfgh"),
            new AbstractMap.SimpleEntry<>("asdf", "as")
        );
        for (Map.Entry<String , String> entry : fails) {
            Assertions.assertFalse(ArrayProblems.isOneEditAway(entry.getKey(), entry.getValue()), "Failed: " + entry);
        }
    }

    @Test
    public void isRotation() {
        String input = "asdfghjkl";

        List<String> passes = List.of(
            "fghjklasd",
            "sdfghjkla",
            "lasdfghjk",
            "ghjklasdf",
            input
        );
        for (String string : passes) {
            Assertions.assertTrue(ArrayProblems.isRotation(input, string), "Failed: " + string);
        }

        List<String> fails = List.of(
            "asdfdsa",
            "asdfasdfl",
            "asdfghjka",
            ""
        );
        for (String string : fails) {
            Assertions.assertFalse(ArrayProblems.isRotation(input, string), "Failed: " + string);
        }
    }

}
