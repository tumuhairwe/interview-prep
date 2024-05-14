package com.tumuhairwe.prep.lists;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {

    static int shortestWordEditPath(String source, String target, String[] words) {
        // your code goes here
        // make character array of source
        char[] sourceArr = source.toCharArray();
        int pointer = 0;

        //0. find the word (in words) that most closely matches sourceArr[i] = word[i]
        // distance =
        // bit -> but -> put -> pot -> dog
        words = new String[]{"but", "put", "big", "pot", "pog", "dog", "lot"};
        Set<String> wordsSet = Set.of(words);
        //put in set
        Queue<String> queue = new LinkedList<>();
        // loop thru words
        // put word into Que if it has a 1 character transformation
        //
        queue.offer(source);
        int steps = 0;
        while (!queue.isEmpty()){
            String word = queue.poll();

            if (word.equals(target)){
                return steps;
            }

            // transform
            for (int i = 0; i < word.length(); i++){

                //bit;  // bat // bbt // bct....bzt

                //ait
                //cit
                for (int j = 0; j < 26; j++){
                    word.toCharArray()[i] = (char)(j + 'a');

//                    String str = new String(word[i])// but;
//
//                    if (wordsSet.contains(str)){
//                        queue.offer(str);
//                    }
                }
            }
            steps++;
        }
        String currentTransformation = null;
        for(int wordIndex =0; wordIndex<words.length; wordIndex++){

            for(int characterIndex = 0; characterIndex < sourceArr.length ; characterIndex++){

                if(sourceArr[characterIndex] != words[wordIndex].toCharArray()[characterIndex]){
                    int diffBetweenCharacters = sourceArr[characterIndex] -  words[wordIndex].toCharArray()[characterIndex];

                    // find the
                }
            }
        }
        return steps;
    }

    public static void main(String[] args) {

    }
}