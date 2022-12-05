package net.kwas.problems;

import net.kwas.graph.list.LinkedList;
import net.kwas.graph.list.LinkedListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListProblems {

    public static void removeDupesWithSet(LinkedList list) {
        Set<Integer> seenValues = new HashSet<>();
        LinkedListNode currNode = list.getHead();
        while (currNode != null) {
            int value = currNode.getId();
            if (seenValues.contains(value)) {
                currNode.getPrev().setNext(currNode.getNext());
                if (currNode.getNext() != null) {
                    currNode.getNext().setPrev(currNode.getPrev());
                }
                currNode = currNode.getPrev();
            }
            else {
                seenValues.add(value);
            }
            currNode = currNode.getNext();
        }
    }

}
