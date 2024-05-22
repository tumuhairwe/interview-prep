package com.tumuhairwe.prep.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 230
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Solution Summary
 * - Implement recursive in-order traversal (while collecting all values in a collction)
 * - IN-ORDER: LEFT -> ROOT -> RIGHT
 * - return i-th element in list where i = k-1 (since k is 1-indexed)
 *
 * ref: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class SmallestElementInBST {
    public int kthSmallest(TreeNode<Integer> root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);
        if(list.size() <= k){
            return list.get(list.size() - 1);
        }
        return list.get(k - 1);
    }

    void inOrderTraversal(TreeNode<Integer> node, List<Integer> results){
        if(node.left != null){
            inOrderTraversal(node.left, results);
        }
        results.add(node.data);

        if(node.right != null){
            inOrderTraversal(node.right, results);
        }
    }
}
