package net.kwas.graph.tree.binary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class BaseBinaryTree<T extends BinaryTreeNode<T>> implements BinaryTree<T> {

    private T head;
    private int size = 0;

    @Override
    public T getHead() {
        return head;
    }

    @Override
    public void setHead(T head) {
        this.head = head;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public BinaryTree<T> addValues(int... values) {
        for (int value : values) {
            addValue(value);
        }
        return this;
    }

    @Override
    public boolean contains(int value) {
        boolean retVal = false;
        T currNode = head;
        while (!retVal && currNode != null) {
            if (value == currNode.getId()) {
                retVal = true;
            }
            else if (value < currNode.getId()) {
                currNode = currNode.getLeftChild();
            }
            else {
                currNode = currNode.getRightChild();
            }
        }
        return retVal;
    }

    @Override
    public List<Integer> preOrderTraversal() {
        List<Integer> retVal = new ArrayList<>(getSize());

        Deque<T> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(getHead());
        while (!nodesToVisit.isEmpty()) {
            T currNode = nodesToVisit.pollFirst();
            retVal.add(currNode.getId());
            if (currNode.getRightChild() != null) {
                nodesToVisit.addFirst(currNode.getRightChild());
            }
            if (currNode.getLeftChild() != null) {
                nodesToVisit.addFirst(currNode.getLeftChild());
            }
        }

        return retVal;
    }

    @Override
    public List<Integer> inOrderTraversal() {
        List<Integer> retVal = new ArrayList<>(getSize());

        Deque<T> nodesToVisit = new ArrayDeque<>();
        T currNode = getHead();
        while (!nodesToVisit.isEmpty() || currNode != null) {

            while (currNode != null) {
                nodesToVisit.addFirst(currNode);
                currNode = currNode.getLeftChild();
            }

            currNode = nodesToVisit.pollFirst();

            if (currNode != null) {
                retVal.add(currNode.getId());
                currNode = currNode.getRightChild();
            }
        }

        return retVal;
    }

    @Override
    public List<Integer> postOrderTraversal() {
        List<Integer> retVal = new ArrayList<>(getSize());

        Deque<T> nodesToVisit = new ArrayDeque<>();
        T currNode = getHead();
        T prevNode = null;
        while (!nodesToVisit.isEmpty() || currNode != null) {
            while (currNode != null) {
                nodesToVisit.addFirst(currNode);
                currNode = currNode.getLeftChild();
            }

            currNode = nodesToVisit.peekFirst();

            if (currNode != null) {
                if (currNode.getRightChild() == null || currNode.getRightChild() == prevNode) {
                    retVal.add(currNode.getId());
                    nodesToVisit.pollFirst();
                    prevNode = currNode;
                    currNode = null;
                }
                else {
                    currNode = currNode.getRightChild();
                }
            }
        }

        return retVal;
    }

    @Override
    public List<Integer> preOrderTraversalRecursive() {
        List<Integer> retVal = new ArrayList<>(getSize());
        preOrderTraversalRecursive(getHead(), retVal);
        return retVal;
    }

    private void preOrderTraversalRecursive(T node, List<Integer> values) {
        if (node != null) {
            values.add(node.getId());
            preOrderTraversalRecursive(node.getLeftChild(), values);
            preOrderTraversalRecursive(node.getRightChild(), values);
        }
    }

    @Override
    public List<Integer> inOrderTraversalRecursive() {
        List<Integer> retVal = new ArrayList<>(getSize());
        inOrderTraversalRecursive(getHead(), retVal);
        return retVal;
    }

    private void inOrderTraversalRecursive(T node, List<Integer> values) {
        if (node != null) {
            inOrderTraversalRecursive(node.getLeftChild(), values);
            values.add(node.getId());
            inOrderTraversalRecursive(node.getRightChild(), values);
        }
    }

    @Override
    public List<Integer> postOrderTraversalRecursive() {
        List<Integer> retVal = new ArrayList<>(getSize());
        postOrderTraversalRecursive(getHead(), retVal);
        return retVal;
    }

    private void postOrderTraversalRecursive(T node, List<Integer> values) {
        if (node != null) {
            postOrderTraversalRecursive(node.getLeftChild(), values);
            postOrderTraversalRecursive(node.getRightChild(), values);
            values.add(node.getId());
        }
    }

    @Override
    public void invert() {
        T head = getHead();
        if (head == null) {
            return;
        }

        Deque<T> nodesToVisit = new ArrayDeque<>();
        nodesToVisit.add(head);

        while (!nodesToVisit.isEmpty()) {
            T currNode = nodesToVisit.pollFirst();
            swapChildren(currNode);
            if (currNode.getRightChild() != null) {
                nodesToVisit.addFirst(currNode.getRightChild());
            }
            if (currNode.getLeftChild() != null) {
                nodesToVisit.addFirst(currNode.getLeftChild());
            }
        }
    }

    @Override
    public void invertRecursive() {
        invertRecursive(getHead());
    }

    private void invertRecursive(T node) {
        if (node != null) {
            swapChildren(node);
            invertRecursive(node.getLeftChild());
            invertRecursive(node.getRightChild());
        }
    }

    private void swapChildren(T node) {
        T tmp = node.getLeftChild();
        node.setLeftChild(node.getRightChild());
        node.setRightChild(tmp);
    }

    @Override
    public boolean isBalanced() {
        boolean retVal = true;
        T head = getHead();
        if (head != null) {
            int leftHeight = getHeight(getHead().getLeftChild());
            int rightHeight = getHeight(getHead().getRightChild());
            int balance = leftHeight - rightHeight;
            retVal = balance >= -1 && balance <= 1;
        }
        return retVal;
    }

    @Override
    public int getHeight() {
        return getHeight(getHead());
    }

    private int getHeight(T node) {
        int retVal = 0;

        if (node != null) {
            Deque<T> nodesToVisit = new ArrayDeque<>();
            nodesToVisit.push(node);
            while (!nodesToVisit.isEmpty()) {
                int levelSize = nodesToVisit.size();
                for (int i = 0; i < levelSize; i++) {
                    T currNode = nodesToVisit.pollFirst();
                    if (currNode.getLeftChild() != null) {
                        nodesToVisit.addLast(currNode.getLeftChild());
                    }
                    if (currNode.getRightChild() != null) {
                        nodesToVisit.addLast(currNode.getRightChild());
                    }
                }
                retVal++;
            }
        }

        return retVal;
    }

}
