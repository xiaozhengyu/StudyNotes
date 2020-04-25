package com.learn.java;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author xzy
 * @date 2020-04-06 20:53
 * 说明：
 */
public class Main2 {
    public static void main(String[] args) {
        int[] sequence = new int[]{2, 9, 5, 13, 17, 15, 19, 18, 12};

        Integer[] postRootTraversalSequence = IntStream.of(sequence).boxed().toArray(Integer[]::new);
        Boolean result = isPostRootTraversalSequence(postRootTraversalSequence);
    }

    /**
     * determine whether the given sequence is a post-root traversal sequence of a binary
     * search tree。
     *
     * @param postRootTraversalSequence - the given sequence.
     * @return true or false.
     */
    public static boolean isPostRootTraversalSequence(Integer[] postRootTraversalSequence) {
        if (postRootTraversalSequence.length == 1) {
            return true;
        }
        int rootNode = postRootTraversalSequence[postRootTraversalSequence.length - 1];
        int rootIndex = postRootTraversalSequence.length - 1;
        List<Integer> leftSubTree = new ArrayList<>();
        List<Integer> rightSubTree = new ArrayList<>();
        int i = 0;
        while (i < rootIndex && postRootTraversalSequence[i] < rootNode) {
            leftSubTree.add(postRootTraversalSequence[i]);
            i++;
        }
        while (i < rootIndex && postRootTraversalSequence[i] >= rootNode) {
            rightSubTree.add(postRootTraversalSequence[i]);
            i++;
        }

        if (i != rootIndex) {
            return false;
        }

        if (!leftSubTree.isEmpty() && !rightSubTree.isEmpty()) {
            return isPostRootTraversalSequence(leftSubTree.toArray(new Integer[]{}))
                    && isPostRootTraversalSequence(rightSubTree.toArray(new Integer[]{}));
        } else if (!leftSubTree.isEmpty()) {
            return isPostRootTraversalSequence(leftSubTree.toArray(new Integer[]{}));
        } else {
            return isPostRootTraversalSequence(rightSubTree.toArray(new Integer[]{}));
        }

    }
}
