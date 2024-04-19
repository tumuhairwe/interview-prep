package com.tumuhairwe.prep.array;

import java.util.*;

/**
 * LeetCode 337 (Medium) House Robber III
 *
 * A theif has discovered a new neighborhood target .. where the house can be represented
 * as a binary tree. The money in the house is the data of the respective node.
 *
 * The thief can enter the neighborhood from a house represented as the root pf a binary tree
 *
 * Each house only has 1 parent house. Thief wants to know the max amount of money he can
 * steal from the houses without getting caught
 *
 * The thief knows that if he robs two houses that are directly connected, the police will be notified.
 * i.e. can't rob to connected/adjacent nodes
 *
 * ref: https://leetcode.com/problems/house-robber-iii/description/
 */
public class HouseRobberIII {
}

class BinaryTree<T>{
    TreeNode<T> root;

    BinaryTree(List<TreeNode<T>> nodes){
        this.root = createBinaryTree(nodes);
    }

    // TC = O( n ) == where n == number of nodes, because we have to visit all the nodes at least once
    // SC = O( N ) == because max_depth of recursion is the height of the tree & each call data must be stored
    // on the stack
    public static void main(String[] args) {
        List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList(
                Arrays.asList( new TreeNode<Integer>(10), new TreeNode<Integer>(9), new TreeNode<Integer>(20), new TreeNode<Integer>(15), new TreeNode<Integer>(7)),
                Arrays.asList( new TreeNode<Integer>(7), new TreeNode<Integer>(9), new TreeNode<Integer>(10), new TreeNode<Integer>(15), new TreeNode<Integer>(20)),
                Arrays.asList( new TreeNode<Integer>(8), new TreeNode<Integer>(2), new TreeNode<Integer>(17), new TreeNode<Integer>(1), new TreeNode<Integer>(4), new TreeNode<Integer>(19), new TreeNode<Integer>(5)),
                Arrays.asList( new TreeNode<Integer>(7), new TreeNode<Integer>(3), new TreeNode<Integer>(4), new TreeNode<Integer>(1), new TreeNode<Integer>(3)),
                Arrays.asList( new TreeNode<Integer>(9), new TreeNode<Integer>(5), new TreeNode<Integer>(7), new TreeNode<Integer>(1), new TreeNode<Integer>(3)),
                Arrays.asList( new TreeNode<Integer>(9), new TreeNode<Integer>(7), null, null, new TreeNode<Integer>(1), new TreeNode<Integer>(8), new TreeNode<Integer>(10), null, new TreeNode<Integer>(12))
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

    private static int[] heist(TreeNode<Integer> root) {
        if(root == null){
            return new int[]{0, 0};
        }

        int[] leftSubtree = heist(root.left);
        int[] rightSubtree = heist(root.right);

        int includeRoot = root.data + leftSubtree[1] + rightSubtree[1];
        //        int excludeRoot = Math.max(leftSubtree[0], leftSubtree[1]) + Math.max(rightSubtree[0], rightSubtree[1]);

        int excludeRoot = Math.max(leftSubtree[0], leftSubtree[1] + Math.max(rightSubtree[0], rightSubtree[1]));

        return new int[]{includeRoot, excludeRoot};
    }

    private TreeNode<T> createBinaryTree(List<TreeNode<T>> listONodes) {
        if(listONodes.isEmpty()){
            return null;
        }

        // 0. create the root node
        TreeNode<T> root = new TreeNode<>(listONodes.get(0).data);

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
                current.left = new TreeNode<>(listONodes.get(i).data);
                q.add(current.left);
            }

            i++;

            // if there are more listOfNodes in the list and next node is not null
            // create a new TreeNode and set it as the right child of the current node ...
            // and add it to the q
            if(i < listONodes.size() && listONodes.get(i) != null){
                current.right = new TreeNode<>(listONodes.get(i).data);
                q.add(current.right);
            }

            i++;
        }

        return root;
    }
}

class TreeNode<T>{
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
