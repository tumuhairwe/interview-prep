package com.tumuhairwe.prep.binary;

import java.util.*;

/**
 * LeetCode 107 (medium)
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 *
 * ref: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTreeLevelOrderTraversalII {
    /**
     * Solution summary
     * - Create results list t& pass to recursive function levelOrderTraversalBottom()
     * - reverse results
     * - return results
     *
     * TC: O(n) where n == # of nodes in tree
     * SC: O(n)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode<Integer> root) {
        List<List<Integer>> results = new ArrayList<>();
        levelOrderTraversalBottom(root, results);

        Collections.reverse(results);
        return results;
    }

    //iterative ollect values using DFS like
    void levelOrderTraversalBottom(TreeNode<Integer> root, List<List<Integer>> results){
        if(root == null){
            return;
        }

        Queue<TreeNode<Integer>> que = new ArrayDeque<>();
        que.offer(root);

        while (!que.isEmpty()) {
            int qDepth = que.size();

            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < qDepth; i++) {
                TreeNode<Integer> curr = que.poll();
                levelValues.add(curr.data);

                if(curr.left != null){
                    que.offer(curr.left);
                }
                if(curr.right != null){
                    que.offer(curr.right);
                }
            }
            results.add(levelValues);
        }
    }
}
