package com.tumuhairwe.prep.binary;

/**
 * LeetCode 226 (Easy)
 *
 * Given the root of a binary tree, invert the tree and return its root
 *
 * Solution Summary
 * - Base Case: if root == null, return null
 * - Swap left and right
 *      - put root.left into a temp variable
 *      - set root.left = root.right
 *      - set root.right = temp
 *  - Recursively call invert(root.left) and invert(root.right)
 */
public class InvertBinaryTree {

    public TreeNode<Integer> invert(TreeNode<Integer> root){
        if(root == null){
            return null;
        }

        TreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);

        return root;
    }
}
