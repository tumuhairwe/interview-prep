package com.tumuhairwe.prep.lists;

/**
 * LeetCode 876 (Easy)
 *
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 * Solution summary
 * - Create 2 pointers (fast & slow)
 * - iterate the LinkedList and move the fast pointer twice as fast
 * - Exit the iteration when fast_pointer.next == null
 * - Return slow_pointer
 */
public class MIddleOfALinkedList {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{1,2,3,4,5,6};
    }
    public static ListNode middleNode(ListNode head) {
        ListNode slow_pointer = head;
        ListNode fast_pointer = head;

        while(fast_pointer != null && fast_pointer.next != null){
            slow_pointer = slow_pointer.next;
            fast_pointer = fast_pointer.next.next;
        }

        return slow_pointer;
    }
}
