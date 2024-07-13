package com.tumuhairwe.prep.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 111 (easy)
 * Given a binary tree, find its minimum depth
 *
 * The tree depth is the number of nodes along the shortest path from the node
 * to the NEAREST leaf node
 * Note: A lead if a node with no children
 */
public class MinimumDepthOfTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.right = right;
            this.left = left;
        }
    }

    /**
     * Solution summary
     * - create and seed que with root node
     * - DO BFS: while que.size() is != 0
     *      - pull node from queue
     *      - if isLeafNode -> return minDepth
     *      - if node.left != null > add to que
     *      - if node.right != null -> add to que
     *   - increment minDepth
     *  - when que is empty -> return minDepth
     */
    public int minDepth_bfs(TreeNode root){
        //0. base case
        if(root == null){
            return 0;
        }

        // 1. init & seed queue
        int minDepth = 1;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        // 2. do BFS on Queue
        while(!que.isEmpty()){
            int qDepth = que.size();

            while(qDepth-- > 0){
                TreeNode current = que.poll();

                if(current.left == null && current.right == null){
                    return minDepth;
                }

                if(current.left != null){
                    que.offer(current.left);
                }
                if(current.right != null){
                    que.offer(current.right);
                }
            }
            minDepth++;
        }

        return minDepth;
    }

    /**&
     * Solution summary
     * - call dfs recursively passing in node.left -> assign to leftVal
     * - call dfs recursively passing in node.right -> assign to rightVal
     * - if either left or right is 0 -> return right+left+1
     * - else return min(left, right) + 1;
     */
    public int minDepth_dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = minDepth_dfs(root.left);
        int right = minDepth_dfs(root.right);

        if(left == 0 || right == 0){
            return left + right + 1;
        }

        return Math.min(left, right) + 1;
    }
}
