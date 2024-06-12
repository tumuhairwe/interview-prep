package com.tumuhairwe.prep.array;

import java.util.Stack;

/**
 * LeetCpde 901 (medium)
 * Design an algo that collects daily price quotes for some stock and returns the span of that's stock's
 * price for the current day
 *
 * The span of the stock's price in one day is the maximum number of consecutive days (starting
 * from that day and going backward) for which the stock_price was <= price_of_that_date
 * ref: https://leetcode.com/problems/online-stock-span/description/
 */
public class StockSpanner {

    private Stack<Pair> monotonicStack; // stack that keeps elements in INCREASING (or decreasing) order

    public StockSpanner(){
        this.monotonicStack = new Stack<>();
    }

    public int getSpan(int price){
        int span = 1;

        // for every new price, if the top element in the stack is <= given_price
        // add its span the top of the element, and pop it off from the queue
        // goal is to have a monotonic stack (i.e. stack that maintains element in strict order descending or ascending)
        while (!monotonicStack.isEmpty() && monotonicStack.peek().price <= price){
            span += monotonicStack.peek().span;
            monotonicStack.pop();
        }

        monotonicStack.push(new Pair(price, span));
        return span;
    }

    static class Pair{
        int price;
        int span;
        public Pair(int price, int span){
            this.price = price;
            this.span = span;
        }
    }
}
