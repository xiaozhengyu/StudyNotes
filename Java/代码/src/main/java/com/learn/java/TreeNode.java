package com.learn.java;

import java.util.Stack;

/**
 * @author xzy
 * @date 2020-04-01 00:03
 * 说明：
 */
public class TreeNode {
    public char val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(char val) {
        this.val = val;
    }

    /**
     * 迭代遍历二叉树（先序遍历）
     *
     * @param root - 树根结点
     */
    public static void rootFirstRead(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode readNow;
        //栈顶的元素就是要读的元素
        while (!stack.isEmpty()) {
            readNow = stack.pop();
            System.out.println(readNow.val);
            //注意栈的特点：先进后出
            if (readNow.right != null) {
                stack.push(readNow.right);
            }
            if (readNow.left != null) {
                stack.push(readNow.left);
            }
        }
    }

    /**
     * 迭代遍历二叉树（中序遍历）
     *
     * @param root - 根结点
     */
    public static void rootSecondRead(TreeNode root) {

    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode('A');
        TreeNode b = new TreeNode('B');
        TreeNode c = new TreeNode('C');
        TreeNode d = new TreeNode('D');
        TreeNode e = new TreeNode('E');
        TreeNode f = new TreeNode('F');
        TreeNode g = new TreeNode('G');
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        /**
         *      A
         *   B    C
         * D  E  F  G
         * 先序遍历：A B D E C F G
         * 中序遍历：D B E A F C G
         * 后序遍历：D E B F G C A
         */
    }
}
