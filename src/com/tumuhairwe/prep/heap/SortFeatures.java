package com.tumuhairwe.prep.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 1772 Sort Features by popularity (medium)
 *
 * You are given an array (features) where each element i is a single word that
 * represents the name of a feature of the latest product you are working on
 * You have made a survey where users have reported which features they like
 * You are given a string array responses where each resp[i] is a string containing
 * space-separated words
 *
 * The popularity of a feature is the number of responses[i] that contain the feature. You want to sort the features in non-increasing order by their popularity. If two features have the same popularity, order them by their original index in features. Notice that one response could contain the same feature multiple times; this feature is only counted once in its popularity.
 *
 * Return the features in sorted order.
 */
public class SortFeatures {

    public static void main(String[] args) {
        String[] features = {"cooler","lock","touch"};
        String[] responses = {"i like cooler cooler","lock touch cool","locker like touch"};
        String[] result = sortFeatures(features, responses);;
        System.out.println(Arrays.toString(result));
    }
    /**
     * Solution summary
     * - create a freqMap & init with value=0, for all features in features[]
     * - collect responses, (space-split) and populate frequency in freqMap
     * - create comparator (based on frequency) && sort features[] by frequency
     * - sort features[] by custom comparator
     * - return sorted array of features
     */
    public static String[] sortFeatures(String[] features, String[] responses){
        //0 create initial freqMap of features
        Map<String, Integer> freqMap = new HashMap<>();
        for (String f : features){
            freqMap.put(f, 0);
        }

        //1. collect responses, (space-split) and populate frequency in freqMap
        for(String resp : responses){
            Set<String> myWordSet = Arrays.asList(resp.split("\\s"))
                    .stream()
                    .collect(Collectors.toSet());

            for (String word: myWordSet){
                if(freqMap.containsKey(word)){
                    freqMap.put(word, freqMap.get(word) + 1);
                }
            }
        }

        //2. create freq-comparator && sort features[] by frequency
        Comparator<String> comp = (String s1, String s2) -> freqMap.get(s2) - freqMap.get(s1);
        Arrays.sort(features, comp);

        return features;
    }
}
