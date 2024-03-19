package com.tumuhairwe.prep.tree;

// order: Left always comes before right
public class Traversals {
    static class Node {
        int value;
        Node left;
        Node right;
    }

    // root comes first
    public void preorder(Node root){
        if(root == null)    return;;

        System.out.println(root.value);
        preorder(root.left);
        preorder(root.right);
    }

    // root come 2ne
    public void inorder(Node root){
        if(root == null)    return;;

        preorder(root.left);
        System.out.println(root.value);
        preorder(root.right);
    }

    // root comes later
    public void postorder(Node root){
        if(root == null)    return;;

        preorder(root.left);
        preorder(root.right);
        System.out.println(root.value);

    }
}
