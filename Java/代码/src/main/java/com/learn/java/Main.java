package com.learn.java;

import java.util.Stack;

/**
 * @author xzy
 * @date 2020-03-25 18:56
 * 说明：
 */
public class Main {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        System.out.println(Main.preOrderBinaryTreeGeneric(treeNode1));
    }

    /**
     * 获取二叉树的前序遍历序列
     *
     * @param root - 待遍历二叉树根节点
     * @return - 二叉树前序遍历序列
     */
    public static String preOrderBinaryTreeGeneric(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode readNow;
        StringBuilder result = new StringBuilder();
        stack.push(root);
        while (!stack.isEmpty()) {
            readNow = stack.pop();
            if (readNow.right != null) {
                stack.push(readNow.right);
            }
            if (readNow.left != null) {
                stack.push(readNow.left);
            }
            result.append(readNow.val);
        }
        return result.toString();
    }
}
