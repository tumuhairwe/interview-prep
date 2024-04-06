package com.tumuhairwe.prep.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an m number of sorted lists in ascending order and integer k,
 * find the K-th smallest number among the given lists
 * - There can be repeating values in the lists, each elements is considered unique and therefore contributes
 * to calculating the k-th smallest element
 * - If k is greater than the total number of elements in the input lists, return the greatest element from all lists
 * - If there are no elements in the input lists, return 0
 */
public class KthSmallestNumberInMSortedLists {

    public static int KSmallestNumber(List<List<Integer>> lists, int k){
        int listLength = lists.size();

        // these are the same
        Comparator<int[]> comp = (int[] a, int[] b) -> a[0] - b[0];
        //Comparator<int[]> comp = Comparator.comparingInt((int[] a) -> a[0]);

        PriorityQueue<int[]> kSmallest_heap = new PriorityQueue<>(comp);
        for (int index = 0; index < listLength; index++) {
            if(/* lists.get(index).size() == 0 ||  */ lists.get(index).isEmpty()){
                continue;
            }
            else {
                Integer firstElementInList = lists.get(index).get(0);
                kSmallest_heap.offer(new int[]{firstElementInList, index, 0});  // 1stElementInList, list_index, indexOfSmallest_nm_inarr
            }
        }

        int numbersChecked = 0, smallestNumber = 0;
        while (!kSmallest_heap.isEmpty()){
            int[] arr_with_smallest_first_element = kSmallest_heap.poll();
            smallestNumber = arr_with_smallest_first_element[0];
            int listIndex = arr_with_smallest_first_element[1];
            int numIndex = arr_with_smallest_first_element[2];
            numbersChecked++;

            if(numbersChecked == k){
                break;
            }

            if(numIndex + 1 < lists.get(listIndex).size()){ // if we haven't reached the end of that array yet
                int secondNumberInList = lists.get(listIndex).get(numIndex + 1);
                kSmallest_heap.offer(new int[]{secondNumberInList, listIndex, numIndex});
            }
        }

        return smallestNumber;
    }

    // Driver code
    public static void main(String[] args) {

        List<List<List<Integer>>> lists = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(2, 6, 8),
                        Arrays.asList(3, 6, 10),
                        Arrays.asList(5, 8, 11)
                ),
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(4, 5),
                        Arrays.asList(6, 7, 8, 15),
                        Arrays.asList(10, 11, 12, 13),
                        Arrays.asList(5, 10)
                ),
                Arrays.asList(
                        Arrays.asList(),
                        Arrays.asList(),
                        Arrays.asList()
                ),
                Arrays.asList(
                        Arrays.asList(1, 1, 3, 8),
                        Arrays.asList(5, 5, 7, 9),
                        Arrays.asList(3, 5, 8, 12)
                ),
                Arrays.asList(
                        Arrays.asList(5, 8, 9, 17),
                        Arrays.asList(),
                        Arrays.asList(8, 17, 23, 24)
                )
        );

        int[] k = {5, 50, 7, 4, 8};

        for (int i = 0; i < k.length; i++) {
            System.out.println(i + 1 + ".\t Input lists: " + lists.get(i) +
                    "\n\t K = " + k[i] +
                    "\n\t " + k[i] + "th smallest number from the given lists is: " +
                    KSmallestNumber(lists.get(i), k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
