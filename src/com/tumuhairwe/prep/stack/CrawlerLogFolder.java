package com.tumuhairwe.prep.stack;

import java.util.Stack;

/**
 * LeetCode 1598 (medium) Crawler Log Folder
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 *
 * The operations are described below:
 *
 * "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
 * "./" : Remain in the same folder.
 * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 *
 * The file system starts in the main folder, then the operations in logs are performed.
 *
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 *
 */
public class CrawlerLogFolder {
    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();

        for(String cmd : logs){
            if(cmd.equals("../")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else if(cmd.startsWith("./")){
                // do nothing
                System.out.println("Doing nothing");
            }
            else{
                System.out.println("cd " + cmd.substring(0, cmd.indexOf("/")));
                stack.push(cmd.substring(0, cmd.indexOf("/")));
            }
        }

        return stack.size();
    }
}
