package com.tumuhairwe.prep.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * TC: O (2^n) -- when is the size of the array
 * SC: O(2^n) -- bcoz we need store all the subsets, we'll need 2^n memory to store each subset
 */
public class Subsets {
    public static void main(String[] args) {
        List<Integer> array = List.of(1, 2, 3);
        List<List<Integer>> result = subsets(array);
    }

    private static List<List<Integer>> subsets(List<Integer> superset) {
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        int index = 0;
        calcSubset(superset, result, subset, index);
        return result;
    }

    private static void calcSubset(List<Integer> superset, List<List<Integer>> result, List<Integer> subset, int index) {
        // 0. add the current subset to the list
        result.add(new ArrayList<>(subset));

        // 1. generate subsets by recursively including ad excluding elements
        for (int i = index; i < superset.size(); i++) {
            // 2..include the current element in the subset
            subset.add(superset.get(i));

            // 3. recursively generate subsets with the current element
            calcSubset(superset, result, subset, i+1);

            // 4. exclude the current element from the subset (backtracking
            subset.remove(subset.size() - 1);
        }
    }
}
