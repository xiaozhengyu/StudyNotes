package com.learn.java.genericclass;

/**
 * @author xzy
 * @date 2020-04-10 13:25
 * 说明：
 */
public class TreeNode<T> {
    private T value = null;
    private TreeNode<T> leftChild = null;
    private TreeNode<T> rightChild = null;

    public TreeNode() {
    }

    public TreeNode(T value, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
