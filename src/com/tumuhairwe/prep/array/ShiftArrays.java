package com.tumuhairwe.prep.array;

import java.util.Arrays;

public class ShiftArrays {
    public static void main(String[] args) {
        int [] ss = new int[]{3, 8, 9, 7, 6};

        System.out.println(Arrays.toString(solution(ss, 3)));
    }

    public static int[] solution(int[] A, int K) {
        //int[] shifted = A;
        // write your code in Java SE 8
        for(int i=0; i< K; i++){
            shift(A, A[A.length - 1]);
        }

        return A;
    }
    private static void shift(int[] original, int valueToPrepend){
        //original[original.length - 1] = 0;

        for(int i=original.length - 2; i >= 0; i--){
            original[i + 1] = original[i];
        }
        original[0]= valueToPrepend;

        //return original;
    }
}
