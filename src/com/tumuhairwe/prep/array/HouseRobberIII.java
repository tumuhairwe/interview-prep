package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 337 (Medium) House Robber III
 *
 * A Thief has discovered a new neighborhood target .. where the house can be represented
 * as a binary tree. The money in the house is the data of the respective node.
 *
 * The thief can enter the neighborhood from a house represented as the root of a binary tree
 *
 * Each house only has 1 parent house. Thief wants to know the max amount of money he can
 * steal from the houses without getting caught
 *
 * The thief knows that if he robs two houses that are directly connected, the police will be notified.
 * i.e. can't rob to connected/adjacent nodes
 *
 * ref: https://leetcode.com/problems/house-robber-iii/description/
 * ref:https://github.com/neetcode-gh/leetcode/blob/main/java/0337-house-robber-iii.java
 * ref: https://www.youtube.com/watch?v=nHR8ytpzz7c
 */
public class HouseRobberIII {

    // TC = O( n ) == where n == number of nodes, because we have to visit all the nodes at least once
    // SC = O( N ) == because max_depth of recursion is the height of the tree & each call data must be stored
    // on the stack
    public static void main(String[] args) {
        List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList(
                Arrays.asList(new TreeNode<>(10), new TreeNode<>(9), new TreeNode<>(20), new TreeNode<>(15), new TreeNode<>(7)),
                Arrays.asList(new TreeNode<>(7), new TreeNode<>(9), new TreeNode<>(10), new TreeNode<>(15), new TreeNode<>(20)),
                Arrays.asList(new TreeNode<>(8), new TreeNode<>(2), new TreeNode<>(17), new TreeNode<>(1), new TreeNode<>(4), new TreeNode<Integer>(19), new TreeNode<Integer>(5)),
                Arrays.asList(new TreeNode<>(7), new TreeNode<>(3), new TreeNode<>(4), new TreeNode<>(1), new TreeNode<>(3)),
                Arrays.asList(new TreeNode<>(9), new TreeNode<>(5), new TreeNode<>(7), new TreeNode<>(1), new TreeNode<>(3)),
                Arrays.asList(new TreeNode<>(9), new TreeNode<>(7), null, null, new TreeNode<>(1), new TreeNode<>(8), new TreeNode<>(10), null, new TreeNode<Integer>(12))
        );

        List<BinaryTree<Integer>> inputTrees = new ArrayList<>();
        for (List<TreeNode<Integer>> ListOfNodes : listOfTrees) {
            BinaryTree<Integer> tree = new BinaryTree<>(ListOfNodes);
            inputTrees.add(tree);
        }

        int x = 1;
        for (BinaryTree<Integer> tree : inputTrees) {
            System.out.println(x + ".\tInput Tree:");
            //Print.displayTree(tree.root);
            x++;
            System.out.println("\n\tMaximum amount we can rob without getting caught: " + rob(tree.root));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static int rob(TreeNode<Integer> root) {
        int[] result = heist(root);
        return Math.max(result[0], result[1]);
    }

    // TC: O(n)
    // Algo : dFS
    private static int[] heist(TreeNode<Integer> root) {
        if(root == null){
            return new int[]{0, 0};
        }

        int[] leftPair = heist(root.left);
        int[] rightPair = heist(root.right);

        // if you include THIS rootValue, you can't include the root of the subtrees ...
        // i.e. total = (rootValue + left_without_root + right_without_root)
        int includeRoot = root.val + leftPair[1] + rightPair[1];

        // i.e. total =  max(left_pair) + max(right_pair) // since we don't know which 1 of the 2 options (in each) ....
        // we get the max of both and add them
        int maxOfLeftPair = Math.max(leftPair[0], leftPair[1]);
        int maxOfRightPair = Math.max(rightPair[0], rightPair[1]);
        int excludeRoot = maxOfLeftPair + maxOfRightPair;

        // 0 = index with root
        // 1 = index without root
        return new int[]{includeRoot, excludeRoot};
    }
}

class BinaryTree<T>{
    TreeNode<T> root;

    BinaryTree(List<TreeNode<T>> nodes){
        this.root = createBinaryTree(nodes);
    }

    private TreeNode<T> createBinaryTree(List<TreeNode<T>> listONodes) {
        if(listONodes.isEmpty()){
            return null;
        }

        // 0. create the root node
        TreeNode<T> root = new TreeNode<>(listONodes.get(0).val);

        // 1. create a queue and add the root node to it
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);

        // 2. start iterating over the listOfNodes starting from the 2nd one
        int i = 1;
        while (i< listONodes.size()){
            // 2a) get the next node from the queue
            TreeNode<T> current = q.remove();

            // 2b) if the node is not null, create a new TreeNode for its left child
            // set it as the left child of the current node
            if(listONodes.get(i) != null){
                current.left = new TreeNode<>(listONodes.get(i).val);
                q.add(current.left);
            }

            i++;

            // if there are more listOfNodes in the list and next node is not null
            // create a new TreeNode and set it as the right child of the current node ...
            // and add it to the q
            if(i < listONodes.size() && listONodes.get(i) != null){
                current.right = new TreeNode<>(listONodes.get(i).val);
                q.add(current.right);
            }

            i++;
        }

        return root;
    }
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
