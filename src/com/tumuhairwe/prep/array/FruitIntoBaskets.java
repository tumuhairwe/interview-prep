package com.tumuhairwe.prep.array;

/**
 * Given 2 baskets
 * You can add 1 fruit to the basket & the basket can have at most 2 type of fruit
 *
 *  basket can only have 2 types of fruit
 * Where values of array are a type of fruit
 * ref: https://leetcode.com/problems/fruit-into-baskets/description/
 *
 * In otherwords .. find the longest contiguous sub-array of only 2 numbers
 */
public class FruitIntoBaskets {

    public int totalFruit(int[] tree){
        int last_fruit = -1;
        int second_last_fruit =0;
        int last_fruit_count = 0;
        int current_max = 0;
        int max  = 0;

        for(Integer fruit : tree){
            if(fruit == last_fruit || fruit == second_last_fruit){
                current_max++;
            }
            else {
                current_max = last_fruit_count + 1;
            }

            if(fruit == last_fruit){
                last_fruit++;
            }
            else {
                last_fruit = 1;
            }

            if(fruit != last_fruit){
                second_last_fruit = last_fruit;
                last_fruit = fruit;
            }
            max = Math.max(current_max, max);
        }

        return  max;
    }
}
