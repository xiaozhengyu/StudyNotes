package com.learn.datastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xzy
 * @date 2020-04-22 21:39
 * 说明：二叉树结点
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinaryTreeNode {
    /**
     * The value stored by the node.
     */
    private Integer value;
    /**
     * The root node of the left subtree.
     */
    private BinaryTreeNode left;
    /**
     * The root node of the right subtree.
     */
    private BinaryTreeNode right;
    /**
     * Parent node.
     */
    private BinaryTreeNode previous;

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", previous=" + previous +
                '}';
    }
}
