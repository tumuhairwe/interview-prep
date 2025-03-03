package com.tumuhairwe.prep.array;

/**
 * Given a 2D int array logs where each entry[i] = [birthYear, deathYear]
 * The population of some year X is the number of people alive during that year.
 * The i-th person is counted in year X's population if x is in the inclusive range [bYear, dYear - 1]
 * Note that the person is not counted in the year they die.
 *
 * Return the earliest year with the maximum population
 * ref: https://leetcode.com/problems/maximum-population-year/
 */
public class MaximumPopulationYear {
    public static void main(String[] args) {

    }

    /**
     * ref: https://leetcode.com/problems/maximum-population-year/solutions/1198978/java-o-n-solution-with-explanation-range-addition/
     *
     * Solution:
     * - create a prefix map
     */
    public int maximumPopulation(int[][] logs){
        //0. create population prefix map
        int[] population = new int[2051];

        for (int[] log : logs){
            int birthYear = log[0];
            int deathYear = log[1];

            population[birthYear]++;
            population[deathYear]--;
        }

        int earliestYear = 0;
        for (int i = 1950; i < 2050; i++) {
            population[i] += population[i - 1];
            earliestYear = population[i] > population[earliestYear] ? i : earliestYear;
        }

        return earliestYear;
    }
}
