package net.kwas.graph.tree.binary.avl;

import java.util.function.BiFunction;

public class AVLTree {

    private AVLTreeNode head;

    private int size = 0;

    public AVLTreeNode getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(int value) {
        boolean retVal = false;
        AVLTreeNode currNode = head;
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

    public AVLTree addValues(int... values) {
        for (int value : values) {
            addValue(value);
        }
        return this;
    }

    public AVLTree addValue(int value) {

        if (!contains(value)) {
            if (head == null) {
                head = new AVLTreeNode(value);
            } else {
                AVLTreeNode newNode = addChild(value);
                AVLTreeNode newRoot = rebalance(newNode);
                if (newRoot != null) {
                    head = newRoot;
                }
            }
            size++;
        }

        return this;
    }

    private AVLTreeNode addChild(int value) {
        AVLTreeNode retVal = null;
        AVLTreeNode currNode = head;

        while (retVal == null) {
            if (value < currNode.getId()) {
                if (currNode.getLeftChild() == null) {
                    retVal = new AVLTreeNode(value);
                    currNode.withLeftChild(retVal);
                } else {
                    currNode = currNode.getLeftChild();
                }
            } else {
                if (currNode.getRightChild() == null) {
                    retVal = new AVLTreeNode(value);
                    currNode.withRightChild(retVal);
                } else {
                    currNode = currNode.getRightChild();
                }
            }
        }

        return retVal;
    }

    private AVLTreeNode rebalance(AVLTreeNode node) {
        AVLTreeNode retVal = null;

        AVLTreeNode currNode = node;
        AVLTreeNode parent = null;
        while (currNode != null) {
            parent = currNode.getParent();
            if (parent != null) {
                boolean leftChild = parent.getLeftChild() == currNode;

                // TODO Is this actually correct?
                if (leftChild) {
                    parent.setBalance(parent.getBalance() - 1);
                } else {
                    parent.setBalance(parent.getBalance() + 1);
                }

                if (parent.getBalance() == 0) {
                    break;
                }
                else if (parent.getBalance() > 1) {
                    if (currNode.getBalance() < 0) {
                        retVal = doRotate(parent, currNode, AVLTree::rotateRightLeft);
                        break;
                    }
                    else if (currNode.getBalance() > 0) {
                        retVal = doRotate(parent, currNode, AVLTree::rotateLeft);
                        break;
                    }
                    else {
                        throw new RuntimeException("Invalid State");
                    }
                }
                else if (parent.getBalance() < -1) {
                    if (currNode.getBalance() < 0) {
                        retVal = doRotate(parent, currNode, AVLTree::rotateRight);
                        break;
                    }
                    else if (currNode.getBalance() > 0) {
                        retVal = doRotate(parent, currNode, AVLTree::rotateLeftRight);
                        break;
                    }
                    else {
                        throw new RuntimeException("Invalid State");
                    }
                }
            }
            currNode = parent;
        }

        if (parent != head) {
            retVal = null;
        }

        return retVal;
    }

    private AVLTreeNode doRotate(AVLTreeNode x, AVLTreeNode z, BiFunction<AVLTreeNode, AVLTreeNode, AVLTreeNode> rotation) {
        AVLTreeNode oldParent = x.getParent();

        AVLTreeNode retVal = rotation.apply(x, z);

        if (oldParent != null) {
            if (oldParent.getLeftChild() == x) {
                oldParent.setLeftChild(retVal);
                retVal.setParent(oldParent);
            } else {
                oldParent.setRightChild(retVal);
                retVal.setParent(oldParent);
            }
        }

        x.setBalance(0);
        z.setBalance(0);

        return retVal;
    }

    public static AVLTreeNode rotateLeft(AVLTreeNode x, AVLTreeNode z) {
        x.setRightChild(z.getLeftChild());
        z.setLeftChild(x);

        if (x.getRightChild() != null) {
            x.getRightChild().setParent(x);
        }
        z.setParent(x.getParent());
        x.setParent(z);
        return z;
    }

    public static AVLTreeNode rotateRight(AVLTreeNode x, AVLTreeNode z) {
        x.setLeftChild(z.getRightChild());
        z.setRightChild(x);

        if (x.getLeftChild() != null) {
            x.getLeftChild().setParent(x);
        }
        z.setParent(x.getParent());
        x.setParent(z);
        return z;
    }

    public static AVLTreeNode rotateLeftRight(AVLTreeNode x, AVLTreeNode z) {
        AVLTreeNode y = z.getRightChild();

        y = rotateLeft(z, y);
        z.setParent(y);
        y.setParent(x);

        y = rotateRight(x, y);
        x.setParent(y);

        return y;
    }

    public static AVLTreeNode rotateRightLeft(AVLTreeNode x, AVLTreeNode z) {
        AVLTreeNode y = z.getLeftChild();

        y = rotateRight(z, y);
        z.setParent(y);
        y.setParent(x);

        y = rotateLeft(x, y);
        x.setParent(y);

        return y;
    }

}
