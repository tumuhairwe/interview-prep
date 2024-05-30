package com.tumuhairwe.prep.binary;

/**
 * Leetcode 236
 * Given a binary tree, find the lowest commons ancestor of 2 given nodes in the tree
 *
 * Solution Summary
 * - base-case: if root is null, return null
 * - Do dfs(root.left) && dfs(root.right)
 * - if both are not null, return root
 * - if either 1 is null, return that one
 *
 * TC: O(log n) == becoz we only visit 1 node every level of the tre (where n == number of nodes)
 * SC: O(1)
 * ref: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * ref: https://www.youtube.com/watch?v=gs2LMfuOR9k&list=TLPQMjIwNTIwMjQJITbpcQLnxg&index=2
 */
public class LowestCommonAncestor {
    // recursive
    public TreeNode<Integer> dfs_recursive(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q){
        // 1. base case: if root or any 1 of the 2 is null, return null
        if(root == null || q.data == null || p.data == null){
            return root;
        }

        // 2 dfs on left and right
        TreeNode<Integer> left = dfs_recursive(root.left, p, q);
        TreeNode<Integer> right = dfs_recursive(root.right, p, q);

        // 3. if both are not null, return parent (root)
        if(left != null && right != null){
            return root;
        }

        // 4. return which ever 1 isn't null
        if(left != null){
            return left;
        }
        if(right != null){
            return right;
        }

        return null;
    }

    // iterative:
    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        TreeNode<Integer> current = root;

        while (current != null){
            if(p.data > current.data && q.data > current.data){
                current = current.right;
            }
            else if (p.data < current.data && q.data < current.data){
                current = current.left;
            }
            else {
                return current;
            }
        }

        return current;
    }
}
