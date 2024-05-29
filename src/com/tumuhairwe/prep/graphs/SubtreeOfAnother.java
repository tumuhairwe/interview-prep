package com.tumuhairwe.prep.graphs;

/**
 * LeetCode 572 (easy)
 *
 * Given the roots of 2 binary trees and a subRoot, return true if there's a subtree of rooth
 * with the same structure and node values as subRoot. False otherwise
 *
 * ref: https://leetcode.com/problems/subtree-of-another-tree/
 * ref: https://www.youtube.com/watch?v=E36O5SWp-LE
 */
public class SubtreeOfAnother {

    public boolean isSubtree(TreeNode root, TreeNode subTree) {
        // 0. null sub-tree is always a subtree of another tree
        if(subTree == null || isSameTree(root, subTree)){
            return true;
        }

        // can not have a subtree that is NOT NULL but the tree its3lf is NULL
        if(root == null){
            return false;
        }

        // at this point both of the trees are NOT NULL
        if(isSameTree(root, subTree)){
            return true;
        }

        return isSameTree(root.left, subTree) || isSameTree(root.right, subTree);
    }

    boolean isSameTree(TreeNode s, TreeNode t){
        if(s == null && t== null) return true;

        // 2. 1 is empty and 1 is not
        if(s == null || t == null || s.val != t.val){
            return false;
        }

        // 1.value is same + left & right are same
        boolean isLeftSame = isSameTree(s.left, t.left);
        boolean isRightSame = isSameTree(s.right, t.right);
        return isLeftSame && isRightSame;
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
