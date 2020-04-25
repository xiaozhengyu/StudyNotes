package com.learn.datastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020-04-22 21:51
 * 说明：二叉搜索树
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinarySearchTree {
    private BinaryTreeNode root = null;
    private Integer size = 0;

    /**
     * insert into a node.
     *
     * @param newNode - node to be inserted.
     */
    public void insert(BinaryTreeNode newNode) {
        this.size++;
        if (isEmpty()) {
            this.root = newNode;
            return;
        }
        BinaryTreeNode previousNode = null;
        BinaryTreeNode readNow = this.root;
        while (readNow != null) {
            previousNode = readNow;
            if (newNode.getValue() < readNow.getValue()) {
                readNow = readNow.getLeft();
            } else {
                readNow = readNow.getRight();
            }
        }
        newNode.setPrevious(previousNode);
        if (newNode.getValue() < previousNode.getValue()) {
            previousNode.setLeft(newNode);
        } else {
            previousNode.setRight(newNode);
        }
    }

    /**
     * delete a node.
     *
     * @param deletedNode - node to be deleted.
     */
    public void delete(BinaryTreeNode deletedNode) {
        BinaryTreeNode previousNode = deletedNode.getPrevious();
        //1.没有孩子

        //2.左右孩子都有

        //3.只有一个孩子

    }

    /**
     * replace the old node by new node.
     *
     * @param oldNode - old node
     * @param newNode - new node
     */
    private void replace(BinaryTreeNode oldNode, BinaryTreeNode newNode) {
        //1. 接收oldNode的父结点
        if (oldNode.getPrevious() == null) {
            //1.1 oldNode没有父结点（oldNode是根节点）
            this.root = newNode;
        } else if (oldNode == oldNode.getPrevious().getLeft()) {
            //1.2 oldNode是父结点的左结点
            oldNode.getPrevious().setLeft(newNode);
        } else {
            //1.3 oldNode是父结点的右结点
            oldNode.getPrevious().setRight(newNode);
        }
        //2. 安置newNode的父结点和子结点
        
        //2. 接收oldNode子结点

    }

    /**
     * find the node that stores the largest keyword in the tree.
     *
     * @param binaryTreeNode - the root node of the tree.
     * @return - the node that stores the largest keyword in the tree.
     */
    public BinaryTreeNode minimum(BinaryTreeNode binaryTreeNode) {
        while (binaryTreeNode.getLeft() != null) {
            //根据二叉搜索树的特点，最左结点存储最小关键字。
            binaryTreeNode = binaryTreeNode.getLeft();
        }
        return binaryTreeNode;
    }

    /**
     * Returns true if this tree contains no node.
     *
     * @return true if this tree contains no node.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * return the number of node in the tree.
     *
     * @return - the number of node in the tree.
     */
    public Integer size() {
        return this.size;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] values = new int[]{15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
        for (int value : values) {
            BinaryTreeNode binaryTreeNode = new BinaryTreeNode(value);
            binarySearchTree.insert(binaryTreeNode);
        }
    }
}
