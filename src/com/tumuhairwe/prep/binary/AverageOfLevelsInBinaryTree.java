package com.tumuhairwe.prep.binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 637 (easy)
 *
 * Average of Levels in Binary Tree
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * ref: https://www.youtube.com/watch?v=92zdLCeiumk
 * ref: https://leetcode.com/problems/diameter-of-binary-tree
 */
public class AverageOfLevelsInBinaryTree {

    /**
     * Solution summary (do BFS on a que seeded with root)
     * - Create queue and seed it with root
     * - while !q.isEmpty()
     *      - initialize sum
     *      - get depth of queue
     *      - for each entry in q
     *          - pop() val & add data to sum
     *          - if left != null -> add to que
     *          - if right != null -> add to que
     *      - compute average and add to result list
     *  - return resultList
     */
    public List<Double> average(TreeNode<Integer> root){
        List<Double> averages = new ArrayList<>();
        Queue<TreeNode<Integer>> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()){
            double sum = 0d;
            int qDepth = que.size();

            for (int i = 0; i < qDepth; i++) {
                TreeNode<Integer> node = que.poll();
                sum += node.data;

                if(node.left != null){
                    que.offer(node.left);
                }
                if(node.right != null){
                    que.offer(node.right);
                }
            }

            averages.add(sum/qDepth);
        }
        return averages;
    }
}
