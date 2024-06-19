package com.tumuhairwe.prep.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a 4 x 5 board game:
 *  a dice, Map of starting position-x and landing position-y, redirects you to position-Y,
 *  write a function that returns all possible positions you can land on a single turn.
 *  Assume you have a Map for position-X and position-Y
 */
public class BoardGame {

    public static void main(String[] args) {
        Map<Integer, Position>  positions = new BoardGame().getAllPositions(new Dice(), 3);
        for (Map.Entry<Integer, Position> p : positions.entrySet()){
            System.out.println("dice roll outcome: " + p.getKey() + " value=> " + p.getValue().getDestination());
        }
    }
    public Map<Integer, Position>  getAllPositions(Dice dice, int numberOfTurns){
        Map<Integer, Position> positions = new HashMap<>();

        for (int i = 0; i < numberOfTurns; i++) {
            int outcome = dice.roll();
            MapFunction destinationCalculator = new MapFunction();
            positions.put(outcome, destinationCalculator.apply(outcome));
        }

         return positions;
    }
}
