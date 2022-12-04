package net.kwas.trie.simple;

public class SimpleTrieNode {
    private boolean isTerminal = false;
    private final SimpleTrieNode[] children;

    public SimpleTrieNode(SimpleTrieNode[] children) {
        this.children = children;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public SimpleTrieNode[] getChildren() {
        return children;
    }
}
