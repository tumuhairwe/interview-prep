package com.tumuhairwe.prep.array;

// Array elements are store in sequential memory locatoins
public class FindMaxSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, -2, -5, 10, -1};

        //int result = maxContiguousSubArray(arr);
        int result = findMaxSubArray(arr);
        System.out.println("The result is " + result);
    }

    // Runtime complexity: O(n)
    // Space complexity: O(1)
    public static int findMaxSubArray(int[] arr){
        if(arr.length < 1){
            return 0;
        }

        int currentMax = arr[0];
        int globalMax = arr[0];
        int arrayLength = arr.length;
        int beginningPointer = 0;
        int endingPointer = beginningPointer + 1;

        for (int i=0; i<arrayLength; i++){
            if(currentMax < 0){
                currentMax = arr[i];
                beginningPointer = i;
            }
            else {
                currentMax += arr[i];
                endingPointer = i;
            }

            if(globalMax < currentMax){
                globalMax = currentMax;
            }
        }

        return globalMax;
    }
    // integer returned must be the maximum sub-array of arr
    public static int maxContiguousSubArray(int[] arr){
        int beginningPointer = 0;
        int endingPointer = beginningPointer + 1;
        int largestSum = 0;

        //for (int i = 0; i < arr.length; i++) {
        while (endingPointer < arr.length){

            int sum = arr[beginningPointer] + arr[endingPointer];
            if(sum > largestSum){
                largestSum = sum;
                System.out.println("From " + beginningPointer+ " to "  + endingPointer + "=" + sum);
            }

            beginningPointer++;
            endingPointer++;
        }

        return largestSum;
    }
}
