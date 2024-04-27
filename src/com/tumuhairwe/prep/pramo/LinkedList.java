package com.tumuhairwe.prep.pramo;

public class LinkedList<T>  {
    private LinkedListNode head;
    private LinkedListNode tail;
    private int length;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public int size() {
        return this.length;
    }

    public void insertAtHead(int key, int value) {
        LinkedListNode newNode = new LinkedListNode(key, value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.length++;
    }

    public void insertAtTail(int key, int value) {
        LinkedListNode newNode = new LinkedListNode(key, value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public void addFirst(LinkedListNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.length++;
    }

    public void addLast(LinkedListNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.length++;
    }

    public void remove(int data) {
        LinkedListNode tmp = this.head;
        while (tmp != null) {
            if (tmp.val == data) {
                this.remove(tmp);
                return;
            }
            tmp = tmp.next;
        }
    }

    public void remove(LinkedListNode node) {
        if (node == null)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (node == this.head)
            this.head = this.head.next;
        if (node == this.tail) {
            this.tail = this.tail.prev;
            if (this.tail != null)
                this.tail.next = null;
        }
        this.length--;
        node = null;
    }

    public void removeFirst() {
        this.remove(this.head);
    }

    public void removeLast() {
        this.remove(this.tail);
    }

    public LinkedListNode getFirst() {
        return this.head;
    }

    public LinkedListNode getLast() {
        return this.tail;
    }

    public LinkedListNode reverse(LinkedListNode node){
        LinkedListNode current = node;
        LinkedListNode previous = null;

        while (current != null){
            LinkedListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        //int[] ints = {1,2,3};
        //List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());

        return previous;
    }

    public static void main(String[] args) {

    }
}
