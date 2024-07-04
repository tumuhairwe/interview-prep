package com.tumuhairwe.prep.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * LeetCode 297 (hard)
 *
 *  Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class Codec {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * Solution summary
     * -
     */
    public static void main(String[] args) {

    }
    //Encodes a tree to a single string

    /**
     * Solution summary
     * - if root == null, return NUL_IDENTIFIER
     * - else return preorder form recursively
     * i.e. val + DELIMITER, + serialize(root.left) + DELIMITER + serialize(root.right)
     */
    public String serialize(TreeNode root){
        if(root == null){
            return "#";
        }

        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    //decodes your encoded data to a tree

    /**
     * Solution summary
     * - split data by DELIMITER and place list in Queue
     * - return recursive-deserialize()
     */
    public TreeNode deserialize(String data){
        Queue<String> que = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return dfsDeserialize(que);
    }

    /**
     * Solution
     * - poll() from que ... if DELIMITER ... return
     * - parse pulled value to string -> set va root.val
     * - recursively build preorder (root, left, right)
     *      - call dfsDeserialize(que) -> assign to left
     *      - clll dfsDeserialize(que) -> assign to right
     */
    TreeNode dfsDeserialize(Queue<String> que){
        String str = que.poll();

        assert str != null;
        if(str.equals("#")){
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = dfsDeserialize(que);
        root.right = dfsDeserialize(que);
        return root;
    }
}
