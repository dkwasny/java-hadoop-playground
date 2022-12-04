package net.kwas.trie.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleTrieTest {

    @Test
    public void contains_empty() {
        SimpleTrie trie = new SimpleTrie();
        Assertions.assertFalse(trie.contains("test"));
        Assertions.assertEquals(0, trie.getSize());
    }

    @Test
    public void contains_invalid() {
        SimpleTrie trie = new SimpleTrie()
            .addWord("test")
            .addWord("test!");
        Assertions.assertFalse(trie.contains("test!"));
    }

    @Test
    public void addWord_single() {
        SimpleTrie trie = new SimpleTrie().addWord("test");
        Assertions.assertTrue(trie.contains("test"));
        Assertions.assertFalse(trie.contains("other"));
        Assertions.assertFalse(trie.contains("tests"));
        Assertions.assertFalse(trie.contains("tes"));

        Assertions.assertEquals(1, trie.getSize());
    }

    @Test
    public void addWord_invalid() {
        SimpleTrie trie = new SimpleTrie().addWord("!@#");
        Assertions.assertEquals(0, trie.getSize());
    }

    @Test
    public void addWord_multiple_noOverlap() {
        String[] words = { "test", "bobby", "slime", "pop" };
        SimpleTrie trie = new SimpleTrie().addWords(words);

        for (String word : words) {
            Assertions.assertTrue(trie.contains(word));
        }
        Assertions.assertFalse(trie.contains("other"));
        Assertions.assertFalse(trie.contains("tests"));
        Assertions.assertFalse(trie.contains("tes"));

        Assertions.assertEquals(4, trie.getSize());
    }

    @Test
    public void addWord_multiple_overlap() {
        String[] words = { "test", "testers", "tester", "tree", "trie", "bob" };
        SimpleTrie trie = new SimpleTrie().addWords(words);

        for (String word : words) {
            Assertions.assertTrue(trie.contains(word));
        }
        Assertions.assertFalse(trie.contains("other"));
        Assertions.assertFalse(trie.contains("tests"));
        Assertions.assertFalse(trie.contains("tes"));
        Assertions.assertFalse(trie.contains("teste"));

        Assertions.assertEquals(6, trie.getSize());
    }

    @Test
    public void addWord_manyRandomWords() {
        int numWords = 10000;
        Random random = new Random(13);
        List<String> words = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
            words.add(randomWord(random));
        }

        SimpleTrie trie = new SimpleTrie();
        for (String word : words) {
            trie.addWord(word);
        }

        for (String word : words) {
            Assertions.assertTrue(trie.contains(word));
        }

        Assertions.assertFalse(trie.contains("kwasny"));
        Assertions.assertFalse(trie.contains("hiimkwasny"));
        Assertions.assertFalse(trie.contains("duck"));
    }

    private String randomWord(Random random) {
        int numChars = random.nextInt(100) + 10;

        int lowerBound = 'a';
        int upperBound = 'z' - lowerBound + 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numChars; i++) {
            char nextChar = (char)(random.nextInt(upperBound) + lowerBound);
            // Skip 'k' for negative testing
            if (nextChar != 'k') {
                builder.append(nextChar);
            }
        }

        return builder.toString();
    }

}
