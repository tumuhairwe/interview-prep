package com.tumuhairwe.prep.array;

import java.util.*;
import java.util.stream.Collectors;

public class RepeatedDnaSequence {

    public static void main(String[] args) {
        String s = "AAAAAAAAAA";
        List<String> result = findRepeatedDnaSequences(s);
        System.out.println(result);
    }
    public static List<String> findRepeatedDnaSequences(String s) {
        //0. init vars
        Set<String> seqSet = new HashSet<>();
        Set<String> result = new HashSet<>();

        //1. traverse string with 2 pointers
        int start = 0;
        for(int end = 10; end <= s.toCharArray().length; end++){
            String dna = s.substring(start, end);

            if(seqSet.contains(dna)){
                result.add(dna);
            }

            seqSet.add(dna);
            start++;
        }

        return new ArrayList<>(result);
    }
    public static List<String> findRepeatedDnaSequences_map_based(String s){
        //0. init vars
        Map<String, Integer> freqCount = new HashMap<>();   // key = dna_string, val = frequency

        //1. traverse string with 2 pointers
        int start = 0;
        for(int end = 10; end <= s.toCharArray().length; end++){
            String dna = s.substring(start, end);

            int existingCount = freqCount.getOrDefault(dna, 0);
            freqCount.put(dna, existingCount + 1);
            start++;
        }

        System.out.println(freqCount);

        List<String> dnaThatOccursMoreThanOnce = freqCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        return dnaThatOccursMoreThanOnce;
    }
}
