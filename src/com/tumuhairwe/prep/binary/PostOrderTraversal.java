package com.tumuhairwe.prep.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Recursive and iterative implementation of post order traversal
 */
public class PostOrderTraversal {

    // iterative
    public List<Integer> postOrderTraversal(TreeNode<Integer> root){
        Stack<TreeNode> stack = new Stack<>();  // stack to track the actual nodes
        stack.add(root);

        Stack<Boolean> visit = new Stack<>();   // stack to track what's been visited/printed/processed
        visit.add(false);

        List<Integer> res = new ArrayList<>();

        while (!stack.empty()){
            TreeNode<Integer> current = stack.pop();
            boolean visited = visit.pop();

            if(current != null){
                if(!visited){
                    res.add(current.data);
                }
                else {
                    stack.add(current);
                    visit.add(true);

                    stack.add(current.right);
                    visit.add(false);

                    stack.add(current.left);
                    visit.add(false);
                }
            }
        }

        return res;
    }

    void postOrder(TreeNode<Integer> root, List<Integer> result){
        if(root == null){
            return;
        }

        if(root.left != null){
            postOrder(root.left, result);
        }
        if(root.right != null){
            postOrder(root.right, result);
        }
        result.add(root.data);
    }
}
