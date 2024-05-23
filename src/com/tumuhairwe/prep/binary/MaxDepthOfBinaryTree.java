package com.tumuhairwe.prep.binary;

import java.util.*;

/**
 * LeetCode 104 (Easy)
 * Given the root of a tree, return its maximum depth
 *
 * MaxDepth:the number of nodes along the longest path from the root node to the farthest leaf node.
 *
 * ref: https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * ref: https://github.com/neetcode-gh/leetcode/blob/main/java/0104-maximum-depth-of-binary-tree.java
 */
public class MaxDepthOfBinaryTree {

    public static void main(String[] args) {

    }
    // TC: O(n) -- where n = depth of tree
    // SC: O(n)
    /**
     * Solution Summary:
     * - Create base case of if root = null -> return 0;
     * - recursively get depth of left
     * - recursively get depth of right
     * return 1 + max(depth_left, depth_right)  -- which ever is larger
     */
    public int maxDepth_recursive(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        int depthOfLeft = maxDepth_recursive(root.left);
        int depthOfRight = maxDepth_recursive(root.right);
        return 1 + Math.max(depthOfLeft, depthOfRight);
    }

    // BFS = Level-by-level traversal (uses Queue/Deque)
    // TC = O(n)
    // SC = O(n)
    /**
     * Solution Summary:
     * - Create base case of if root = null -> return 0;
     * - See Queue with root node
     * - while !q.isEmpty() iterate over queue.size ->
     *      - add node.left to queue
     *      - add node.right to queue
     *      - increment level by 1
     * return 1 + max(depth_left, depth_right)  -- which ever is larger
     */
    public int maxDepth_iterative_bfs(TreeNode<Integer> root){
        // base case
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int level = 0;
        while (!queue.isEmpty()){

            for (int i = 0; i < queue.size(); i++) {
                TreeNode<Integer> node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }

                level++;
            }
        }

        return level;
    }

    // DFS = Stack

    /**
     * Solution Summary (DFS = Stack)
     * - Create stack + seed with root (key=node, value=depth) with depth of 1
     * - while !stack.isEmpty()
     *      - pop value from stack
     *      - if (poppedNode != null)   -> update the treeDepth to max(nodeDepth, treeDepth)
     *      - create left & add to stack (key=node.left, value=nodeDeptg  + 1)
     *      - create right & add to stack (key=node.right, value=nodeDeptg  + 1)
     *      - repeat while loop
     *  - When while loop exits, return treeDepth;
     */
    public int maxDepth_iterative_dfs(TreeNode<Integer> root){
        /// base case
        if(root == null) {
            return 0;
        }

        int treeDepth = 1;

        // seed stack with root (with value =1)
        Stack<Map.Entry<TreeNode, Integer>> stack = new Stack<>();  // key=node, value=depth
        Map.Entry<TreeNode, Integer> entry = new AbstractMap.SimpleEntry<>(root, Integer.valueOf(1));
        stack.add(entry);

        while (!stack.isEmpty()){
            Map.Entry<TreeNode, Integer> e = stack.pop();
            int nodeDepth = e.getValue();
            if(e != null){
                treeDepth = Math.max(treeDepth, nodeDepth);
            }

            Map.Entry<TreeNode, Integer> left = new AbstractMap.SimpleEntry<>(root.left, nodeDepth + 1);
            Map.Entry<TreeNode, Integer> right = new AbstractMap.SimpleEntry<>(root.right, nodeDepth + 1);
            stack.push(left);
            stack.push(right);
        }

        return treeDepth;
    }
}
