package com.tumuhairwe.prep.binary;

/**
 * LeetCode 101 easy
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * ref: https://leetcode.com/problems/symmetric-tree/description/
 * ref: https://www.youtube.com/watch?v=BooilJIjNHc
 */
public class IsSymmetrical {

    public boolean isSymmetric(TreeNode<Integer> root){
        return isSame(root.left, root.right);
    }

    /**
     * TC: O(n) because we are visiting all the nodes
     * SC: O(h) where h == height of the tree (or O(n) in the worst case)
     */
    boolean isSame(TreeNode<Integer> root1, TreeNode<Integer> root2){
        if(root1 == null && root2 == null){
            return true;    // both are null
        }
        else if(root1 == null || root2 == null){
            return false;   // 1 is null and 1 is not
        }
        else if(root1.data != root2.data){
            return false;   // the node.data is different
        }

        return isSame(root1.left, root2.right) && isSame(root1.right, root2.left);
    }
}
