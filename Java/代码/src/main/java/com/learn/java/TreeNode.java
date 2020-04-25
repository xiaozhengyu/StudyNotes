package com.learn.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author xzy
 * @date 2020-04-01 00:03
 * 说明：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 先序遍历二叉树（迭代方式）
     *
     * @param root - 树根结点
     */
    public static void preOrderTraversal(TreeNode root) {
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
     * 中序遍历二叉树（迭代方式）
     *
     * @param root - 树根结点
     */
    public static void inOrderTraversal(TreeNode root) {

    }

    /**
     * 后续遍历二叉树（迭代方式）
     *
     * @param root - 树根节点
     */
    public static void postOrderTraversal(TreeNode root) {

    }

    /**
     * 获取镜像二叉树（递归实现）
     *
     * @param root - 原二叉树根节点
     * @return - 镜像二叉树
     */
    public static TreeNode mirror(TreeNode root) {
        if (root == null) {
            return null;
        }
        //1.当前根节点左右子树互换
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = right;
        //2.对左右子树的根节点重复上述过程
        mirror(left);
        mirror(right);
        return root;
    }

    /**
     * 获取镜像二叉树（迭代实现）
     *
     * @param root - 原二叉树根节点
     * @return - 镜像二叉树
     */
    public static TreeNode mirror2(TreeNode root) {
        if (root == null) {
            return null;
        }
        /*
         * 遍历二叉树的每个节点，交换其左右子树
         */
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode readNow;
        TreeNode leftChild;
        TreeNode rightChild;
        while (!stack.isEmpty()) {
            readNow = stack.pop();
            leftChild = readNow.right;
            rightChild = readNow.left;
            readNow.left = leftChild;
            readNow.right = rightChild;
            if (rightChild != null) {
                stack.push(rightChild);
            }
            if (leftChild != null) {
                stack.push(leftChild);
            }
        }
        return root;
    }

    /**
     * 层次遍历二叉树（迭代实现）
     *
     * @param root - 二叉树根节点
     */
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //利用队列先进先出的特性，在遍历当前层时将下一层的元素入队。
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode readNow;
        while (!queue.isEmpty()) {
            readNow = queue.remove();
            result.add(readNow.val);
            if (readNow.left != null) {
                queue.add(readNow.left);
            }
            if (readNow.right != null) {
                queue.add(readNow.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        TreeNode treeNode = new TreeNode(0);
    }
}
