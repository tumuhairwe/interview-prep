package com.tumuhairwe.prep.array;

import com.tumuhairwe.prep.lists.ListNode;

import java.util.List;
import java.util.Stack;

public class AddTwoNumber {
    public static void main(String[] args) {
        // 2,4,3
        ListNode l1 = new ListNode();
        l1.val = 2;
        l1.next = new ListNode();
        l1.next.val = 4;
        l1.next.next = new ListNode();
        l1.next.next.val = 3;

        //564
        ListNode l2 = new ListNode();
        l2.val = 5;
        l2.next = new ListNode();
        l2.next.val = 6;
        l2.next.next = new ListNode();
        l2.next.next.val = 4;

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }

    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //0. create 2 nodes (head: to be returned) && (nextResult to be traversed and appended to)
        ListNode nextResult = new ListNode(-1);
        ListNode head = nextResult;

        int carry = 0;
        //1. while both are not null
        //  a) calculate the sum
        //  b) set result.next = carry % 10
        //  c) move nextResult forward
        //  d) update carry to have the number int he 10s position
        while (l1 != null || l2 != null){
            if(l1 != null){
                carry += l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                carry += l2.val;
                l2 = l2.next;
            }

            nextResult.next = new ListNode(carry % 10);
            nextResult = nextResult.next;
        }

        //3. if the carry is > 0 ... append it to the last one
        if(carry >0 ){
            nextResult.next = new ListNode(carry);
        }

        // 4. return head.next (head itself is a dummy node)
        return head.next;
    }
    // Uses 2 stacks (space) to keep the numbers

    /**
     * Solution Summary
     * - Use 2 stacks to de-construct the numbers in list 1 & list 2
     * - Recompose the 2 numbers using a stringBuilder
     * - parse the 2 strings into numbers, add them and push the chars into a strack (one by one)
     * - Convert totalStack into ListNode
     * - Return head.next (head is just a dummy node to hold the head of the list)
     */
    public static ListNode addTwoNumbers_wrong(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        //1. create stack of l1
        ListNode current1 = l1;
        while (current1 != null) {
            s1.push(current1.val);
            current1 = current1.next;
        }

        //1.1. get total of l1
        StringBuilder sb1 = new StringBuilder();
        while (!s1.isEmpty()) {
            sb1.append(s1.pop());
        }

        //1. create stack of l2
        ListNode current2 = l2;
        while (current2 != null) {
            s2.push(current2.val);
            current2 = current2.next;
        }

        //2.1. get total of l1
        StringBuilder sb2 = new StringBuilder();
        while (!s2.isEmpty()) {
            sb2.append(s2.pop());
        }

        //3 calc sum + put in stack
        int i1 = Integer.parseInt(sb1.toString());
        int i2 = Integer.parseInt(sb2.toString());
        String total = Integer.toString( i1 + i2);
        System.out.println("Sum: " + i1 + " + " + i2 + " total: " + total);
        Stack<Integer> totalStack = new Stack<>();
        for(char c : total.toCharArray()){
            int num = Integer.parseInt(String.valueOf(c));
            totalStack.add(num);
        }

        //4. create result
        if(totalStack.isEmpty()){
            return null;
        }

        ListNode head = new ListNode(-1);;
        ListNode current = null;
        do{
            if(head == null){
                System.out.println("Initializing List: " + totalStack.peek());
                head = new ListNode(totalStack.pop());
            }
            else{
                System.out.println("Adding to List: " + totalStack.peek());
                current = new ListNode(totalStack.pop());
                //head.next = current;
                head = current;
                current = current.next;
            }
        } while(!totalStack.isEmpty());

        return head.next;
    }
}
