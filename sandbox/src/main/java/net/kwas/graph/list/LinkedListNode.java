package net.kwas.graph.list;

import net.kwas.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class LinkedListNode implements Node {

    private final int id;
    private LinkedListNode next;
    private LinkedListNode prev;

    public LinkedListNode(int id) {
        this.id = id;
    }

    public void withNext(LinkedListNode nextNode) {
        setNext(nextNode);
        nextNode.setPrev(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Node> getNeighbors() {
        List<Node> retVal = new ArrayList<>();
        if (next != null) {
            retVal.add(next);
        }
        if (prev != null) {
            retVal.add(prev);
        }
        return retVal;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode prev) {
        this.prev = prev;
    }
}
