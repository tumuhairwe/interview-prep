package com.tumuhairwe.prep.binary;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 129 Sum root to leaf numbers
 *
 */
public class SumRootToLeafNumbers {

    class Pair{
        TreeNode<Integer> key;
        int value;
        public Pair(TreeNode<Integer> k, int v){
            key = k;
            value = v;
        }
    }
    /**
     * Solution summary
     * - init currentNumber and rootToLeafSum
     * - Create and seed stack with Pair(root, 0)
     * - doBFS(): while !stack.isEmpty()
     *      - pull from queue & check if not null
     *      - add currentNumber = currentNumber * 10 + the value (the higher it is in the tree ... the more value it'll have by exponent of 10)
     *      -
     */
    public int sumNumbers(TreeNode<Integer> root){
        //0 base case
        if(root == null){
            return 0;
        }

        //1. init stack and seed with Pair(key=root, val=0)
        int rootToLeafSum = 0, currNumber = 0;
        Deque<Pair> stack = new ArrayDeque<>();
        stack.add(new Pair(root, 0));  // key=

        // 2. loop thru stack and (add to left & right TreeNodes && update rootToLeafSum if node is root)
        while(!stack.isEmpty()){
            Pair node = stack.pop();
            currNumber = node.value;
            root = node.key;

            if(root == null){
                currNumber = currNumber * 10 + root.data;

                // 3. if node is root, +add to rootToLeafSum
                if(root.left == null && root.right == null){
                    // if is leaf ... add to total
                    rootToLeafSum += currNumber;
                }
                else{
                    //4. if node is not root, add to stack
                    stack.add(new Pair(root.left, currNumber));
                    stack.add(new Pair(root.right, currNumber));
                }
            }
        }

        return rootToLeafSum;
    }
}
