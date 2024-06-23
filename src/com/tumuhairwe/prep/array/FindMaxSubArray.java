package com.tumuhairwe.prep.array;

// Array elements are store in sequential memory locations
public class FindMaxSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 7, -2, -5, 10, -1};

        //int result = maxContiguousSubArray(arr);
        int result = findMaxSubArray(arr);
        System.out.println("The result is " + result);
    }

    // Runtime complexity: O(n)
    // Space complexity: O(1)

    /**
     * LeetCode 58 (medium)
     * Loop over the array
     * - If currentMax is less than 0 (from previous iteration), reset to 0
     * - cumulatively add to currentMax
     * - Udate global max is currentMax is larger
     */
    public static int findMaxSubArray(int[] arr){
        if(arr.length < 1){
            return 0;
        }

        int currentMax = arr[0];
        int globalMax = arr[0];

        for (int i=0; i<arr.length; i++){
            if(currentMax < 0){ // reset
                currentMax = arr[i];
            }
            else {
                currentMax += arr[i];
            }

            if(globalMax < currentMax){
                globalMax = currentMax;
            }
        }

        return globalMax;
    }

    /**
     * Solution summary
     * - Iterate over the array and
     *      - calculate currentSum
     *      - update maxSum if the current Sum is large
     */
    public int findMaxSubArray_alternateImpl(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for(int i=1; i<nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
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
