package com.tumuhairwe.prep.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an input string, return all possible permutations of the string
 *
 * The order of the permutations does not matter
 * ref: https://leetcode.com/problems/permutations/description/
 */
public class PermutationsIterative {

    public static void main(String[] args) {
        System.out.println(Math.pow(2, 4));
    }

    static List<List<Integer>> iteratively(int[] nums){
        LinkedList<List<Integer>> result = new LinkedList<>();
        int rSize;
        result.add(new ArrayList<>());

        for(int num: nums){
            rSize = result.size();
            
            while (rSize >0){   // for as many entries as there are
                List<Integer> permuation = result.pollFirst();
                for (int i = 0; i < permuation.size(); i++) {
                    List<Integer> newPermutation = new ArrayList<>(permuation);
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
