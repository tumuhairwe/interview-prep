package com.tumuhairwe.prep.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1166 Design File System
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 * bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
 * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 * ref: https://leetcode.com/problems/design-file-system/description/
 * ref: https://algo.monster/liteproblems/1166
 */
public class DesignFileSystem {
    class Trie {
        private Map<String, Trie> children;
        private int value;

        public Trie(int value){
            this.children = new HashMap<>();
            this.value = value;
        }
        public boolean insert(String path, int value){
            Trie parent = this;

            // return false if already exists
            String[] folders = path.split("/");

            // 1. check if parents exist
            // iterate over folders excluding 1st empty/root folder
            for(int i=1; i < folders.length - 1; ++i){
                String part = folders[i];
                if(!parent.children.containsKey(part)){
                    return false;
                }
                parent = parent.children.get(part);
            }

            // 2. check if last part/segment already exists
            String lastFolder = folders[folders.length - 1];
            if(parent.children.containsKey(lastFolder)){
                return false;
            }

            //3. set value
            parent.children.put(lastFolder, new Trie(value));
            return true;
        }

        public int search(String path){
            Trie parent = root;
            String[] parts = path.split("/");
            for (int i = 0; i < parts.length; i++) {
                if(!parent.children.containsKey(parts[i])){
                    return -1;
                }
                parent = parent.children.get(parts[i]);
            }

            return parent.value;
        }
    }

    private Trie root;
    public DesignFileSystem() {
        root = new Trie(-1);
    }
    public boolean createPath(String path, int value) {
        return root.insert(path, value);
    }

    public int get(String path) {
        return root.search(path);
    }
    public static void main(String[] args) {
        String[] paths = new String[]{"FileSystem", "createPath", "get"};
        DesignFileSystem fileSystem = new DesignFileSystem();

        System.out.println(fileSystem.createPath("/a", 1));
        System.out.println(fileSystem.get("a/"));
    }
}
