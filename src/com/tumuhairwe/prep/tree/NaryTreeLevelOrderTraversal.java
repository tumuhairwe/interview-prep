package com.tumuhairwe.prep.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 429 (medium)
 * Given an n-ary tree, return the level order traversla of its nodes' values
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value
 * ref: https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NaryTreeLevelOrderTraversal {
    class Node{
        int val;
        List<Node> children;
    }

    /**
     * Solution summary
     * - init que and seed with non-null root
     * - perform level-by-level BFS
     * - for every level, add children to the queu and then add the int value
     * - add subList to results (after level iteration is done)
     * - return collected list of values
     *
     * TC: O(n)
     * sc: O(n)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> results = new ArrayList<>();

        if(root == null){
            return new ArrayList<>();
        }

        //0. create que and seed with non-null root
        Queue<Node> que = new LinkedList<>();
        que.offer(root);

        //1. perform level-by-level bfs
        while (!que.isEmpty()){
            int qDepth = que.size();

            List<Integer> subList = new ArrayList<>();
            while (qDepth-- > 0){
                Node node = que.poll();

                for (Node child : node.children) {
                    que.offer(child);
                }
                subList.add(node.val);
            }

            results.add(subList);
        }
        return results;
    }
}
