package com.tumuhairwe.prep.map;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

class Solution {
    // arr: 'a','b'
    //
    // Str: 'azzzzzzzzbzzzzba'
    //match options:azzzzzzzzb
    //ba (from the very back of the string)

    static String getShortestUniqueSubstring(char[] arr, String str) {

        List<Character> arrCharacters  = new ArrayList<>();;
        List<Character> foundCharacters  = new ArrayList<>();

        for(char c : arr){
            arrCharacters.add(c);
        }

        int windowStart = -1;
        int windowEnd = -1;

        String toReturn = "";

        for(int i=0;
            i<str.length() && foundCharacters.size() < arr.length;
            i++){
            char c = str.charAt(i);

            if(arrCharacters.contains(c)){
                if(windowStart == -1){
                    windowStart = i;
                }
                else{
                    windowStart = i;
                }
            }
            else if(!foundCharacters.contains(c)){
                foundCharacters.add(c);
                windowEnd++;
            }


            if((windowStart  - windowEnd) == arr.length){
                break;
            }
        }

        if(foundCharacters.size() < arr.length){
            toReturn = "";
        }
        else{
            for(Character c : foundCharacters){
                toReturn = toReturn + c;
            }
        }

        return toReturn;
    }

    public static void main(String[] args) {
    }

}