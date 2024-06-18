package com.tumuhairwe.prep.binary;

/**
 * LeetCode 278
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all
 * the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 *
 * ref: https://leetcode.com/problems/first-bad-version/
 *
 * Example 1:
     * Input: n = 5, bad = 4
     * Output: 4
     * Explanation:
     * call isBadVersion(3) -> false
     * call isBadVersion(5) -> true
     * call isBadVersion(4) -> true
     * Then 4 is the first bad version.
 *
 * Example 2:
 *  Input: n = 1, bad = 1
 *  Output: 1
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int low = 0;
        int high = n;
        int mid = n / 2;

        int goodVersion = mid;

//        while(low <= high){
//            mid = low + (high - low) / 2;
//            if(isBadVersion(mid)){
//                high = mid;
//            }
//            else{
//                low = mid + 1;
//                goodVersion = mid;
//            }
//        }
//
//        if(low == high && isBadVersion(low)){
//            return low;
//        }

        return goodVersion;
    }
}
