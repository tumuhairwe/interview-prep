package com.tumuhairwe.prep.binary;

/**
 * LeetCode 33 (medium)
 * Search in a rotated Array
 *
 * ref: NeetCode:: https://www.youtube.com/watch?v=QdVrY3stDD4
 * ref: NickWhite: https://www.youtube.com/watch?v=U8XENwh8Oy8
 */
public class SearchRotatedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        //List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int target = 0;
        System.out.println("Searching a rotated array for " + target + " results (should be 4)=> " + binarySearchRotated(nums, 0));

        nums = new int[]{4,5,6,7,0,1,2};
        //list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        target = 0;
        System.out.println("Searching a rotated array for " + target + " results (should be -1)=> " + binarySearchRotated(nums, 3));
    }
    // iterative
    // Time complexity = O(log_n)
    // Space complexity = O (1) since no new data structure is created
    public static int binarySearchRotated(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (right + left)/2;

            if(nums[mid] == target){
                return mid;
            }
            // check left (sorted)
            else if(nums[left] < nums[mid]){
                if(target > nums[mid] || target < nums[left]){
                    left = mid + 1;
                }
                else{
                    right = mid - 1;
                }
            }
            // check right (sorted)
            else{
                if(target < nums[mid] || target > nums[right]){
                    right = mid - 1;
                }
                else{
                    right = mid + 1;
                }
            }
        }

        return -1;
    }
}
