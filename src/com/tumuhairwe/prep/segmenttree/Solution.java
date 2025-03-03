package com.tumuhairwe.prep.segmenttree;

import java.io.*;

/**
 * ref: https://leetcode.com/discuss/general-discussion/1128655/introduction-to-segment-tree-a-complete-guide
 */
class Solution {

    private static int[] segment;
    private static int buildSegmentTree (int[] arr, int[] segment, int index, int start, int end) {
        
        if (start == end) {
            return arr[start];
        }
        
        int mid = (start + end) / 2;
        segment[2 * index + 1] = buildSegmentTree (arr, segment, 2 * index + 1, start, mid);
        segment[2 * index + 2] = buildSegmentTree (arr, segment, 2 * index + 2, mid + 1, end);
        
        return segment[index] = segment[2 * index + 1] + segment[2 * index + 2];
    }
    
    private static int update (int index, int val, int idx, int start, int end) {
        
        if (index < start || index > end) {
            return segment[idx];
        }
        else if (start == index && end == index) {
            return val;
        }
        
        int mid = (start + end) / 2;
        segment[2 * idx + 1] = update (index, val, 2 * idx + 1, start, mid);
        segment[2 * idx + 2] = update (index, val, 2 * idx + 2, mid + 1, end);
        
        return segment[idx] = segment[2 * idx + 1] + segment[2 * idx + 2];
    }
    
    private static int findMinimumFromRange (int[] segment, int index, int leftIndex, int rightIndex, int start, int end) {
        
        if (leftIndex > end || rightIndex < start) {
            return 0;
        }
        else if (leftIndex <= start && rightIndex >= end) {
            return segment[index];
        }
        
        int mid = (start + end) / 2;
        int x = findMinimumFromRange (segment, 2 * index + 1, leftIndex, rightIndex, start, mid);
        int y = findMinimumFromRange (segment, 2 * index + 2, leftIndex, rightIndex, mid + 1, end);
        
        return x + y;
    }
    
    public static void main (String[] args) throws IOException {
        
        try (BufferedReader reader = new BufferedReader (new InputStreamReader (System.in))) {
            
            int testCases = Integer.parseInt (reader.readLine ());
            
            for (int testCase = 1; testCase <= testCases; testCase++) {
                reader.readLine ();
            
            	String[] s = reader.readLine ().split (" ");
                int n = Integer.parseInt (s[0]);
                int q = Integer.parseInt (s[1]);

                s = reader.readLine ().split (" ");
                int[] arr = new int[n];

                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt (s[i]);
                }

                int[] segment = new int[4 * n];
                segment[0] = buildSegmentTree (arr, segment, 0, 0, n - 1);
                
                System.out.println ("Case " + testCase + ":");

                while (q-- != 0) {
                    s = reader.readLine ().split (" ");
                    int leftIndex = Integer.parseInt (s[0]) - 1;
                    int rightIndex = Integer.parseInt (s[1]) - 1;
                    System.out.println (findMinimumFromRange (segment, 0, leftIndex, rightIndex, 0, n - 1)); 
                }
            }
        }
    }
}
