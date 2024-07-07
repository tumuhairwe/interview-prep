package com.tumuhairwe.prep.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1233. Remove Sub-Folders from the Filesystem
 *
 * ref: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description/
 * ref: https://www.youtube.com/watch?v=sdsEF6L06ho
 */
public class RemoveSubfolders {

    public static class TrieNode{
        Map<String, TrieNode> children = new HashMap<>();
        String folderName = null;
    }

    public static void main(String[] args) {
        String[] folders = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        System.out.println(removeSubfolder(folders));

        folders = new String[]{"/a","/a/b/c","/a/b/d"};
        System.out.println(removeSubfolder(folders));

        folders = new String[]{"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(removeSubfolder(folders));
    }

    /**
     * Solution summary
     * 1. Create implementation of Trie & create root node
     * 2. for each path, split into subFolders and populate Trie
     *      - (if currentTrie doesn't have child subFolder with that name, create a new one)
     *      - repeat until all subFolders/children are represented in Trie data structure
     * - set folderName on each node
     * 3.Recursively collect folderNames (starting with rootNode trie)
     *  - i.e. for each child in children,
     *      - if folderName is not null, add it to list of results
     *      - recursively collect folder name for each child
     *  TC: O(n x m) where n == number of paths, and m = average depth of each path
     *  SC: O(n) where n == number of paths (worst case, there's no intersection af sub-paths and all are entirely unique)
     */
    public static List<String> removeSubfolder(String[] paths) {
        //0. define root Trie
        TrieNode root = new TrieNode();

        //1. set up tree of folders
        for(String folder : paths){
            TrieNode curr = root;
            String[] parts = folder.substring(1).split("/");    // get all sub-folders (starting from root)

            for(String subFolder : parts){
                if(!curr.children.containsKey(subFolder)){
                    curr.children.put(subFolder, new TrieNode());
                }
                curr = curr.children.get(subFolder);
            }

            curr.folderName = folder;
        }

        // collect sub folders
        List<String> results = new ArrayList<>();
        collectFolderNames(root, results);
        return results;
    }

    static void collectFolderNames(TrieNode root, List<String> parentFolders){
        for(Map.Entry<String, TrieNode> entry : root.children.entrySet()){
            if(entry.getValue().folderName != null){
                parentFolders.add(entry.getValue().folderName);
            }
            else{
                collectFolderNames(entry.getValue(), parentFolders);
            }
        }
    }
}
