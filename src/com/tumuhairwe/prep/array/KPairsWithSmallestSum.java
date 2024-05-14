package com.tumuhairwe.prep.array;

import java.util.*;


/**
 * LeetCode 373  (medium)
 * Given 2 arrays nums1 and nums2 sorted in non-decreasing order (i.e. ascending)
 * and an integer K
 *
 * - define a pair (u, v) which consists of one element from each array
 * - return the K pairs with the smallest sums
 *
 * ref: https://www.youtube.com/watch?v=Youk8DDnLU8
 * ref: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * ref: https://walkccc.me/LeetCode/problems/373/#__tabbed_1_2
 */
public class KPairsWithSmallestSum {

    private static class Pair implements Comparable<Pair> {
        int i;
        int j;
        int sum;

        Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.sum, o.sum);
        }
    }

    public static void main(String[] args) {
        int[][] list1 = {{2, 8, 9},
                {1, 2, 300},
                {1, 1, 2},
                {4, 6},
                {4, 7, 9},
                {1, 1, 2}
        };

        int[][] list2 = {
                {1, 3, 6},
                {1, 11, 20, 35, 300},
                {1, 2, 3},
                {2, 3},
                {4, 7, 9},
                {1}
        };
        int[] k = {9, 30, 1, 2, 5, 4};
        for (int i = 0; i < k.length; i++) {
            List<List<Integer>> result = kSmallestPairs(list1[i], list2[i], k[i]);
            System.out.print(i + 1);
            System.out.println(".\tInput lists: " + Arrays.toString(list1[i]) + ", " + Arrays.toString(list2[i]));
            System.out.println("\tK = " + k[i]);
            System.out.print("\tPairs with smallest sum are: " + result);
            System.out.println("\n");
            System.out.println(repeat("-", 100));
        }
    }

    private static String repeat(String s, int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 100; j++) {
            sb.append(s);
        }
        return sb.toString();
    }

    static List<List<Integer>> kSmallestPairs(int[] arr1, int[] arr2, int k) {
        List<List<Integer>> results = new ArrayList<>();

        // 0. create min-heap PQ
        // these are the same
        Comparator<Pair> comp = Comparator.comparingInt((Pair a) -> a.sum); // this is not necessary when Pair implements Comparable
        //Comparator<Pair> comp2 = (Pair a, Pair b) -> a.sum - b.sum;

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(comp);
        //PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        //PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // 1. fill PQ with the {i, Zeroth-entry-of-list2} pairs -- PQ will order by sum & keep
        // lowest sum at the .top()
        for (int i = 0; i < Math.min(k, arr1.length); i++) {
            int sum = arr1[i] + arr2[0];
            Pair p = new Pair(sum, i, 0);
            minHeap.add(p);
        }

        while (!minHeap.isEmpty() && results.size() <= k) {
            Pair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;

            List<Integer> pairToAdd = Arrays.asList(arr1[i], arr2[j]);
            results.add(pairToAdd);

            int nextElement = j + 1;
            if (arr2.length > nextElement) {
                int sum = arr1[i] + arr2[nextElement];
                Pair p = new Pair(sum, i, nextElement);
                minHeap.add(p);
            }
        }
        return results;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        //PriorityQueue<Pair> minHeap = new PriorityQueue<>((Pair a, Pair b) -> a.sum - b.sum);
        Comparator<Pair> comp = Comparator.comparingInt((Pair a) -> a.sum);
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(comp);
        for (int i = 0; i < Math.min(k, nums2.length); i++) {
            minHeap.offer(new Pair(nums1[i] + nums2[0], nums1[i], nums2[0]));
        }


        while (!minHeap.isEmpty()){
            int i = minHeap.peek().i;
            int j = minHeap.poll().j;
            ans.add(Arrays.asList(nums1[i], nums2[j]));

            if(j + 1 < nums2.length){
                minHeap.offer(new Pair(i, j+1, nums1[i] + nums2[j + 1]));
            }
        }
        return ans;
    }
}
