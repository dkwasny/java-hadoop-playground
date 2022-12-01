package net.kwas.graph.tree.binary.avl;

import net.kwas.graph.tree.binary.BaseBinaryTreeNode;

public class AVLTreeNode extends BaseBinaryTreeNode<AVLTreeNode> {

    private int balance = 0;

    public AVLTreeNode(int id) {
        super(id);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
            "balance=" + balance +
            ", id=" + getId() +
            '}';
    }
}
