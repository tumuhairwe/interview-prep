package com.tumuhairwe.prep.array;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string, return the number of occurrences of the words in the String. Assume that all letters are in English alphabet.
 * Get rid of punctuation and words should be case insensitive (Count and count are same). Return result in lower case
 * words with their occurrence count, sorted in decreasing order of occurrences.
 * If some words appear same number of times, the result should have them ordered in the order they appeared in the input String.
 *
 * Solution Summary
 *
 * 1. Strip out all punctuation
 * 2. Create a frequency map (key = word, value = number of occurrence)
 * 3. Order by number of occurrences
 * 4 if numOccurrence is same -> order by occurrence in original docu
 */
public class WordCountEngine {

  // TC = O(n log_n) -- because of sorting (n log_n) and traversing the array (O (n))
  static String[][] wordCountEngine_big_o_nlogn(String document) {
    Map<String, Integer> freqMap = new HashMap<>();
    
    // 1. strip all punctuation
    String[] words = document.toLowerCase().split("\\s");
    
    // 2. create a frequency Map
    for(int i=0; i<words.length; i++){
      // 3. replace all non-alphanumeric chars
      String word = words[i].replaceAll("[^a-zA-Z0-9 -]", "");
//      String word = words[i]
//          .replace('.', ' ')
//          .replace('.', ' ')
//          .replace('\'', ' ')
//              .replace('!', ' ')
//          .replace(',', ' ')
//              .trim();

      int existingCount = freqMap.getOrDefault(word, 0);
      freqMap.put(word, existingCount + 1);
    }
    
    // 3a. sort map by value
    //Comparator<Map.Entry<String, Integer>> comp = Map.Entry.comparingByValue(Comparator.reverseOrder());
    Comparator<Map.Entry<String, Integer>> comparator = Comparator.comparingInt(Map.Entry::getValue);

    // 3b. sorting in descending order of values
    comparator = comparator.reversed();

    //4 convert Hashmap values to 2D array.
    String[][] arrayString = freqMap
            .entrySet()
          .stream()
          .sorted(comparator)
            //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            .map(e -> new String[]{e.getKey(), String.valueOf(e.getValue())})
            .toArray(String[][]::new);

    // 5 if words have the same frequency, sort by order of appearance in original text/doc
    // 5a. create custom comparator
    Comparator<String[]> comp = (s1, s2) -> {
       int s1_frequency = freqMap.get(s1);
       int s2_frequency = freqMap.get(s2);

       int comparisonResult = Integer.compare(s1_frequency, s2_frequency);
      if(comparisonResult != 0){
        return comparisonResult;
      }
      else{ // if(s1_frequency == s2_frequency)
        String s1_word = s1[0]; //int s1_freq = Integer.parseInt(s1[1]);
        String s2_word = s1[0]; //int s2_freq = Integer.parseInt(s2[1]);
        int index_s1 = getIndexOf(words, s1_word);
        int index_s2 = getIndexOf(words, s2_word);
        return Integer.compare(index_s1,  index_s2);
      }
    };

    // 5b sort array
    Arrays.sort(arrayString, comp);

    return arrayString;
  }
  
  static int getIndexOf(String[] words, String w){
    for(int i=0; i<words.length; i++){
      if(words[i].equals(w)){
        return i;
      }
    }
    
    return -1;
  }


  // solution is not sort at all .. ust use List<List<String>> to store index + List of works at that index
  static String[][] wordCountEngine_big_O_n(String document) {

    // 1. strip all punctuation
    String[] words = document.toLowerCase().split("\\s");

    // 2. create a frequency Map
    Map<String, Integer> freqMap = new HashMap<>();
    for(int i=0; i<words.length; i++){
        // 3. replace all non-alphanumeric chars
        String word = words[i].replaceAll("[^a-zA-Z0-9 -]", "");
        int existingCount = freqMap.getOrDefault(word, 0);
        freqMap.put(word, existingCount + 1);
    }

    // 3. change storage to List<List<String>> (index = frequency --- call getIndexOf() to calc frequency
    // thus avoid sorting -> O ( n ) is better than nlog_n
    List<List<String>> result = new ArrayList<>(words.length);
    for(int i=0; i<words.length; i++){
      List<String> existingValues = (result.get(i) ==  null) ? new ArrayList<>(): result.get(i);

      int index = getIndexOf(words, words[i]);
      existingValues.add(words[i]);
      result.add(index, existingValues);
    }

    return  (String[][]) result.stream().toArray();
  }


  public static void main(String[] args) {
    String document = "Practice makes perfect. you'll only get Perfect by practice. just practice!";

    String[][] output = wordCountEngine_big_o_nlogn(document);

    Arrays.stream(output).forEach(arr -> {
      System.out.println(arr[0] + "->" + arr[1]);
    });
  }
}