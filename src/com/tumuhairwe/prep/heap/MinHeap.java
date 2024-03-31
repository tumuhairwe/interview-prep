package com.tumuhairwe.prep.heap;

/**
 * definition: A Min-Heap is a complete binary tree
 * in which each internal node is smaller than or equal to the values of the children
 *
 * Typically represented as a anrray
 * - with root element arr[0]
 * - parentNode = arr[(index-1) / 2]
 * - leftChild node = arr[ (2 * index) + 1]
 * - rightChild node = arr[ (2 * index) + 2]
 *
 * ref: https://www.geeksforgeeks.org/min-heap-in-java/
 */
public class MinHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    // Initializing front as static
    private static final int FRONT = 1;

    public MinHeap(int initialCapacity){
        this.heap = new int[initialCapacity * 2];
        this.heap[0] = Integer.MIN_VALUE;
        this.size = 0;
    }
    public int getLeftChild(int position){
       int index = position * 2;
       return this.heap[index];
    }
    public int getRightChild(int position){
        int index = (2 * position) + 1;
        return this.heap[index];
    }
    public int getParent(int position){
        int index = position / 2;
        return this.heap[index];
    }
    /**
     * @return The root of the MinHeap ( TC = O(1) )
     */
    public int getMin(){
        return this.heap[0];
    }
    public boolean isLeaf(int position){
        return position > (size/2);
    }

    /**
     * Remove the mininum element from the Heap.
     * TC = O (log_n) -> bcoz it needs to call heapify/rebalanc
     */
    public int extractMin(){
        int popped = heap[FRONT];
        heap[FRONT] = heap[size--];
        minHeapify(FRONT);

        return popped;
    }

    public int remove(){
        return this.extractMin();
    }
    public void swap(int nodeA, int nodeB){
        int temp = this.heap[nodeA];
        this.heap[nodeB] = nodeA;
        this.heap[nodeA] = temp;
    }
    private void minHeapify(int position){
        if(!isLeaf(position)){
            int positionToSwap;

            if(getRightChild(position) <= size){
                if(this.getLeftChild(position) < this.getRightChild(position)){
                    positionToSwap = getLeftChild(position);
                }
                else {
                    positionToSwap = getRightChild(position);
                }

                if(heap[position] > getLeftChild(position)
                    || heap[position] > getRightChild(position)
                ){
                    swap(position, positionToSwap);
                    minHeapify(position);
                }
            }
        }
    }

    // takes O (log_n)
    public void insert(int element){
        if(size >= maxSize){
            return;
        }

        // a) add new key to end of tree
        heap[++size] = element;
        int current = size;

        // b) if new key > parent ... do nothing
        // c) if new key is < parent, traverse uup and fix violated property
        while (heap[current] < heap[getParent(current)]){
            swap(current, getParent(current));
            current = getParent(current);
        }
    }

    public static void main(String[] args) {
        System.out.println("The min heap is ");

        int[] vals = new int[]{5, 3, 17, 10, 84, 19, 6, 22, 9};
        MinHeap minHeap = new MinHeap(vals.length);
        for (int i = 0; i < vals.length; i++) {
            minHeap.insert(i);
        }

        minHeap.print();
    }
    public void print(){
        for (int i = 0; i <= size; i++) {
            System.out.println("PARENT -> " + heap[i] +" leftChild -> " + heap[2 * i] + " rightChild -> " + heap[(2 * i) + 1]);
        }

        System.out.println("The minVal == " + this.extractMin());
    }
}
