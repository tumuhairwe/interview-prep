package com.tumuhairwe.prep.lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Given 2 LinkedLists, find their intersection
 * ref: https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 */
public class IntersectionOf22LinkedLists {

    public static void main(String[] args) {
        LinkedList<String> listA = new LinkedList<>();
        listA.add("a1");
        listA.add("a2");
        listA.add("c1");
        listA.add("c2");
        listA.add("c3");

        LinkedList<String> listB = new LinkedList<>();
        listA.add("b1");
        listA.add("b2");
        listA.add("b3");
        listA.add("c1");
        listA.add("c2");
        listA.add("c3");
    }

    private List getIntersection(LinkedList a, LinkedList b){
        if(a == null || b == null){
            return null;
        }
        LinkedList a_pointer = a;
        LinkedList b_pointer = b;

        while (a_pointer != b_pointer){
            if(a_pointer != b_pointer){
                a_pointer = b_pointer;
            }
            else {
                //a_pointer = a.descendingIterator().next();
            }
        }
        return null;
    }
}
