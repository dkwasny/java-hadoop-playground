package net.kwas.graph.tree.binary.simple;

import net.kwas.graph.tree.binary.BaseBinaryTree;
import net.kwas.graph.tree.binary.BinaryTree;

public class SimpleBinaryTree extends BaseBinaryTree<SimpleBinaryTreeNode> {

    @Override
    public BinaryTree<SimpleBinaryTreeNode> addValue(int value) {
        if (getHead() == null) {
            setHead(new SimpleBinaryTreeNode(value));
        }
        else {
            addChild(value);
        }
        setSize(getSize() + 1);
        return this;
    }

    private SimpleBinaryTreeNode addChild(int value) {
        SimpleBinaryTreeNode retVal = null;
        SimpleBinaryTreeNode currNode = getHead();
        while (retVal == null) {
            int currValue = currNode.getId();
            if (value == currValue) {
                break;
            }
            else if (value < currValue) {
                if (currNode.getLeftChild() == null) {
                    retVal = currNode.withLeftChild(new SimpleBinaryTreeNode(value));
                }
                else {
                    currNode = currNode.getLeftChild();
                }
            }
            else {
                if (currNode.getRightChild() == null) {
                    retVal = currNode.withRightChild(new SimpleBinaryTreeNode(value));
                }
                else {
                    currNode = currNode.getRightChild();
                }
            }
        }

        return retVal;
    }
}
