package com.tumuhairwe.prep.other;

import java.util.Random;

public class Dice {
    public int roll(){
        Random r = new Random();
        return r.nextInt(4*5);
    }
}
