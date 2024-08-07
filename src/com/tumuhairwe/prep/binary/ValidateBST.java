package com.tumuhairwe.prep.binary;

/**
 * LeetCode 98
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 * ref: https://leetcode.com/problems/validate-binary-search-tree/
 * ref: https://www.youtube.com/watch?v=s6ATEkipzow
 */
public class ValidateBST {
    public boolean isValidBST(TreeNode<Integer> root) {
        //0. base case: null tree -> valid
        if(root == null){
            return true;
        }

        //1. recursive validation
        return isValid(root, null, null);
    }

    /**
     * Perform recursive DFS
     * - if node is null -> tree is valid (return true)
     * - if there's a violation, return false
     *      - node's data can't be less than min
     *      - node's data can be greater than max
     * - return recusive call validate(left, min, node.val) && validate(right, data max)
     */
    boolean isValid(TreeNode<Integer> node, Integer min, Integer max){
        if (node == null){
            return true;
        }

        // a node's data cannot be less that min && node's data cannot be greater than max
        if((min != null && node.data <= min) || (max != null && node.data >= max)){
            return false;
        }

        return isValid(node.left, min, node.data) && isValid(node.right, node.data, max);
    }
}
