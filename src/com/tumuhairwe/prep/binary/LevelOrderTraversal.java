package com.tumuhairwe.prep.binary;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102 (medium)
 * Binary Tree Level Order Traversal
 *
 * Given the root of binary tree, return the level order traversla of its node value (i.e. from left to right, level by level
 *
 * Solution Summary
 * - Create queue and seed it with root node
 * - while !q.isEmpty()
 *      - Create new List of levelValues to add to result
 *      - for que.depth() ...
 *          - poll() from the queue -> if pulled-node !null -> add to levelValues
 *          - add node.left to queue (if not null)
 *          - add node.right to queue (if not null)
 *      - when done with the que.depth() -> add levelValues to global list (if not empty)
 *
 * ref: https://www.youtube.com/watch?v=6ZnyEApgFYg
 * ref: https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 */
public class LevelOrderTraversal {

    // level-order: visit nodes level by level and left-to-right at the same level
    public List<List<Integer>> levelOrder(TreeNode<Integer> root){
        List<List<Integer>> result = new ArrayList<>();

        if(root == null){
            return result;
        }

        Queue<TreeNode<Integer>> que = new ArrayDeque<>();
        que.add(root);

        while (!que.isEmpty()){
            List<Integer> levelValues = new ArrayList<>();
            int q_depth = que.size();;
            for (int i = 0; i < q_depth; i++) {
                TreeNode<Integer> current = que.poll();;

                if(current != null){
                    levelValues.add(current.data);
                    if(current.left != null){
                        que.add(current.left);
                    }
                    if(current.right != null){
                        que.add(current.right);
                    }
                }
            }

            if(!levelValues.isEmpty()){
                result.add(levelValues);
            }
        }

        return result;
    }
}
