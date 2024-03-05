package com.tumuhairwe.prep;

public class BinaryGap {
    public static void main(String[] args) {
        Integer N = 32;
        System.out.println(solution(N));
    }
    public static int solution(int N) {
        String binaryForm = Integer.toBinaryString(N).trim();
        final char ZERO = '0';
        final char ONE = '1';

        int maxBinCount = 0;
        Long binCount = binaryForm.chars().filter(c -> c == ONE).count();
        if(binCount < 1){
            return 0;
        }

        char[] charArray = binaryForm.toCharArray();
        boolean isOpened = false;

        for(int c =0; c<charArray.length; c++){
            if(charArray[c] == ONE){
                isOpened = true;
                // could be closing
                maxBinCount = binCount > maxBinCount ? binCount.intValue() : maxBinCount;

                binCount = 0L;   // reset
                continue;
            }
            if(isOpened){
                if(charArray[c] == ZERO){
                    binCount++;
                }
                else{
                    isOpened = false;
                    maxBinCount = binCount > maxBinCount ? binCount.intValue() : maxBinCount;
                }
            }
        }
        return maxBinCount;
    }
}
