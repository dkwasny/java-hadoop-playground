package net.kwas.graph.list;

public class LinkedList {

    private int size = 0;
    private LinkedListNode head;

    public LinkedListNode getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public LinkedList addValues(int... values) {
        for (int value : values) {
            addValue(value);
        }
        return this;
    }

    public LinkedList addValue(int value) {
        if (head == null) {
            head = new LinkedListNode(value);
        }
        else {
            LinkedListNode currNode = head;
            while (currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            currNode.withNext(new LinkedListNode(value));
        }
        size++;
        return this;
    }
}
