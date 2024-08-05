package com.tumuhairwe.prep.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //0. collect[] into List
        List<Integer> elements = Arrays.stream(arr).boxed().collect(Collectors.toList());

        //1 sort with custom comparator
        Comparator<Integer> comp = Comparator.comparingInt((Integer a) -> Math.abs(a - x));
//        Comparator<Integer> comp = (Integer a, Integer b) -> {
//            return Math.abs(a - x)- Math.abs(b - x);
//        };

        Collections.sort(elements, comp);

        //2. get sublist up to x
        List<Integer> results = elements.subList(0, k);

        // sort with natural order
        Collections.sort(results);

        return results;
    }
}
