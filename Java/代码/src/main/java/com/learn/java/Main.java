package com.learn.java;


import java.util.ArrayList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {
    public static void main(String[] args) {
        int[] preOrderTree = new int[]{1, 2, 4, 7, 5, 8, 9, 3, 6};
        int[] inOrderTree = new int[]{7, 4, 2, 8, 5, 9, 1, 6, 3};
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode4.left = treeNode7;
        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;
        Main.preOrder(treeNode1);
    }

    /**
     * 先序遍历
     *
     * @param treeNode - 二叉树
     */
    public static void preOrder(TreeNode treeNode) {
        System.out.print(treeNode.val + " ");
        if (treeNode.left != null) {
            preOrder(treeNode.left);
        }
        if (treeNode.right != null) {
            preOrder(treeNode.right);
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return null;
    }

}
