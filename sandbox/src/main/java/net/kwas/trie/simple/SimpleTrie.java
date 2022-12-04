package net.kwas.trie.simple;

public class SimpleTrie {

    private static final char lowerBound = 'a';
    private static final char upperBound = 'z';

    private final SimpleTrieNode root = newNode();
    private int size = 0;

    public SimpleTrieNode getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public SimpleTrie addWords(String... words) {
        for (String word : words) {
            addWord(word);
        }
        return this;
    }

    public SimpleTrie addWord(String word) {
        String lower = word.toLowerCase();

        if (isValid(lower)) {
            SimpleTrieNode currNode = getRoot();
            for (int i = 0; i < lower.length(); i++) {
                int idx = getIdx(lower.charAt(i));
                if (currNode.getChildren()[idx] != null) {
                    currNode = currNode.getChildren()[idx];
                } else {
                    SimpleTrieNode newNode = newNode();
                    currNode.getChildren()[idx] = newNode;
                    currNode = newNode;
                }
            }
            currNode.setTerminal(true);

            size++;
        }
        return this;
    }

    public boolean contains(String word) {
        String lower = word.toLowerCase();

        SimpleTrieNode currNode = null;
        if (isValid(lower)) {
            currNode = getRoot();
            for (int i = 0; i < lower.length() && currNode != null; i++) {
                int idx = getIdx(lower.charAt(i));
                currNode = currNode.getChildren()[idx];
            }
        }

        return currNode != null && currNode.isTerminal();
    }

    private boolean isValid(String word) {
        boolean retVal = true;
        for (int i = 0; i < word.length() && retVal; i++) {
            retVal = isValid(word.charAt(i));
        }
        return retVal;
    }

    private boolean isValid(char character) {
        return character >= lowerBound && character <= upperBound;
    }

    private int getIdx(char character) {
        return character - lowerBound;
    }

    private SimpleTrieNode newNode() {
        SimpleTrieNode[] children = new SimpleTrieNode[upperBound - lowerBound + 1];
        return new SimpleTrieNode(children);
    }

}
