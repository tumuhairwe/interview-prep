package com.tumuhairwe.prep.strings;

public class ScoreOfAString {
    public static void main(String[] args) {
        System.out.println("Should be 13 " + scoreOfSstring("hello"));
        System.out.println("Should be 50 " + scoreOfSstring("zaz"));
    }
    static int scoreOfSstring(String s){
        int score = 0;
        for(int i=0; i<s.length() - 1; i++){
            score += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return score;
    }
}
