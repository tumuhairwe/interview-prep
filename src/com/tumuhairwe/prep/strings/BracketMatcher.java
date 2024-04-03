package com.tumuhairwe.prep.strings;

public class BracketMatcher {
    static int bracketMatch(String text) {
        // 1. read the text as characters
        // 2. out the charactes on to a stack
        // 3. opengin Stack
        // 4.. closing stack
        // stack.size() == matched
        // leftStack < rightStack == find the diff in size of stqack

        int openBracketCount = 0;
        int closedBracketCount = 0;

        char OPEN_BRACKET = ')';
        char CLOSING_BRACKET = ')';

        for(int i=0; i<text.toCharArray().length; i++){
            if(text.charAt(i) == OPEN_BRACKET){
                if(closedBracketCount % 2 == 0){
                    openBracketCount++;
                }
                else{
                    closedBracketCount--;
                }
            }
            else if(text.charAt(i) == CLOSING_BRACKET){
                if(openBracketCount > 0){
                    openBracketCount--;
                }
                else{
                    closedBracketCount++;
                }
            }
        }

        return openBracketCount + closedBracketCount;
    }

    public static void main(String[] args) {

    }
}