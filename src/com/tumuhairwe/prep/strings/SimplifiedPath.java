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

    public static String simplifyPath(String input){
        Deque<String> stack = new LinkedList<>();
        Set<String> skippable = new HashSet<>(Arrays.asList("..", ".", ""));

        // build stack
        for (String dir : input.split("/")){
            if (dir.equals("..") && !stack.isEmpty()){
                stack.pop();
            }
            else if(!skippable.contains(dir)){
                stack.push(dir);
            }
        }

        // build return value
        String result = "";
        for(String dir  : stack){
            result = "/" + dir + result;
        }

        return result.isEmpty() ? "/" : result;
    }
}
