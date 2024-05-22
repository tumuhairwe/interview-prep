package com.tumuhairwe.prep.binary;

/**
 * ref: https://www.youtube.com/watch?v=gs2LMfuOR9k&list=TLPQMjIwNTIwMjQJITbpcQLnxg&index=2
 */
public class LowestCommonAncestor {
    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        TreeNode<Integer> current = root;

        while (current != null){
            if(p.data > current.data && q.data > current.data){
                current = current.right;
            }
            else if (p.data < current.data && q.data < current.data){
                current = current.left;
            }
            else {
                return current;
            }
        }

        return current;
    }
}
