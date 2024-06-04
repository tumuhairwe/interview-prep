package com.tumuhairwe.prep.graphs;



/**
 * LeetCode 543 (easy)
 *
 * Given the root of a binary tree, return the length of the diameter of a tree.
 * Definition:
 *  Diameter: The diameter of a binary tree is the length of the longest path between any 2 nodes in a tree.
 *  Length: Thw length of a path between 2 nodes is represented by the number of edges between them
 *
 * Solution Summary
 * - Account for base case (return -1 when node == null)
 * - Add 1 + (Get HEIGHT of both sub-tree)
 * - Update max_result (because parentNod may be left e.g. it has a shorter-height child)
 * - have get() return
 * - return this.treeHeight
 *
 * ref: https://leetcode.com/problems/diameter-of-binary-tree/
 * ref: https://www.youtube.com/watch?v=6lJZ_xj1mEo
 */
public class DiameterOfATree {
    int treeHeight = -1;

    // TC: O(n) -- where n = height of tree
    // SC: O(h) -- where h == height of tree
    public int getDiameterOfBinaryTree(TreeNode<Integer> root){
        getDFS_height(root);

        return treeHeight;
    }

    int getDFS_height(TreeNode<Integer> node){
        // 0. base case
        if(node == null){
            return -1;
        }

        // 1. get height of both children
        int leftHeight = 1 + getDFS_height(node.left);
        int rightHeight = 1 + getDFS_height(node.right);

        // 2. update result to be max of (right + left) if larger
        treeHeight = Math.max(treeHeight, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight);
    }

    class TreeNode<T>{
        T val;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode(T data){
            this.val = data;
            this.left = null;
            this.right = null;
        }
    }
}
