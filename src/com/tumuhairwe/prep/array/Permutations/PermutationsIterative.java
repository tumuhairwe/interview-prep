package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 46
 * Given an input string, return all possible permutations of the string
 *
 * The order of the permutations does not matter
 * ref: https://leetcode.com/problems/permutations/description/
 */
public class PermutationsIterative {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        List<List<Integer>> results = iteratively(arr);

        System.out.println("These are the permutations");
        System.out.println(results);
    }

    static List<List<Integer>> iteratively(int[] nums){
        LinkedList<List<Integer>> result = new LinkedList<>();
        int rSize;
        result.add(new ArrayList<>());

        for(int num: nums){
            rSize = result.size();
            
            while (rSize >0){   // for as many entries as there are
                List<Integer> permutations = result.pollFirst();
                for (int i = 0; i < permutations.size(); i++) {
                    List<Integer> newPermutation = new ArrayList<>(permutations);
                    newPermutation.add(i, num);
                    result.add(newPermutation);
                }
                rSize--;
            }
            System.out.println(result);
        }

        return result;
    }
}
