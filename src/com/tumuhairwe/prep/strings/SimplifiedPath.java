package com.tumuhairwe.prep.strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * LeetCode 71
 *
 * Given an absolute path for a Unix-style file system, which begins with a slash '/',
 * transform this path into its simplified canonical path.
 *
 * ref: https://leetcode.com/problems/simplify-path
 * ref: https://www.youtube.com/watch?v=qYlHrAKJfyA
 */
public class SimplifiedPath {

    public static void main(String[] args) {
        String[] arr = {
                "/home/",
                "/home//foo/",
                "/home/user/Documents/../Pictures",
                "/.../a/../b/c/../d/./",
                "/../"
        };
        Arrays.stream(arr).forEach(line -> {
            System.out.println("before: " + line + "     after: " + simplifyPath(line));
        });
    }

    /**
     * Solution
     * - split string into parts (by /)
     * - if part == '..' -> pop from LinkedList (if !stack.isEmpty())
     * - if part is '.', skip
     * - put part onto LinkedList
     * - reconstruct path from LinkedList (using / as separator)
     * - return reconstructed path
     */
    public static String simplifyPath(String input){
        Deque<String> linkedList = new LinkedList<>();
        Set<String> skippable = new HashSet<>(Arrays.asList("..", ".", ""));

        // build stack
        for (String dir : input.split("/")){
            if (dir.equals("..") && !linkedList.isEmpty()){
                linkedList.pop();
            }
            else if(!skippable.contains(dir)){
                linkedList.push(dir);
            }
        }

        // build return value
        String result = "";
        for(String dir  : linkedList){
            result = "/" + dir + result;
        }

        return result.isEmpty() ? "/" : result;
    }

    public String simplifyPath_impl2_linkedList(String path) {
        //0. create vars
        Deque<String> linkedList = new LinkedList<>();
        List<String> skippable = List.of("..", ".", "");

        //1 translate path into linked List
        String[] parts = path.split("/");
        for(String dir : parts){
            if(!linkedList.isEmpty() && dir.equals("..")){
                linkedList.pop();
            }
            else if(!skippable.contains(dir)){
                linkedList.push(dir);
            }
        }

        //2. reconstruct path from linkedList
        String result = "";
        while (!linkedList.isEmpty()) {
            result = result + "/" + linkedList.pollLast();
        }

        return result == "" ? "/" : result;
    }
}
