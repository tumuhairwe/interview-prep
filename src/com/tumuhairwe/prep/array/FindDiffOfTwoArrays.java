package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LeetCode 2215
 * Find the difference of 2 arrays
 *
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 */
public class FindDiffOfTwoArrays {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2){
        Set<Integer> numsSet1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> numsSet2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        List<Integer> notInSet1 = numsSet2.stream().filter(e -> !numsSet1.contains(e)).collect(Collectors.toList());
        List<Integer> notInSet2 = numsSet2.stream().filter(e -> !numsSet2.contains(e)).collect(Collectors.toList());

        List<List<Integer>> result = new ArrayList<>();
        result.add(notInSet2);
        result.add(notInSet1);
        return result;
    }
}
