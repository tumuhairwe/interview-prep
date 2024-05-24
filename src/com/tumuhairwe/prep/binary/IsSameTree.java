package com.tumuhairwe.prep.binary;

/**
 * LeetCode 100 (easy)
 * Given 2 root of binary tree (p and q), write a function that checks if they're the same
 * Two trees are the same if they are structurally identical and the nodes have the same value
 *
 * Solution Summary
 * - Check base case (if both nodes are null, return true)
 * - If either 1 is not null but the other is, return false
 * - return true if node.value match
 *      - AND recursively call isSameTree(tree.left)
 *      - AND recursively call isSamTree(tree.right)
 *
 * ref: https://leetcode.com/problems/same-tree/description/
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
        // 0. base case
        if(p == null && q == null){
            return true;
        }

        // 1. if either is null
        if(p == null || q == null){
            return false;
        }

        // 2.
        if(p.data == q.data && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)){
            return true;
        }

        return false;
    }
}
