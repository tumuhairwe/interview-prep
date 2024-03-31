package com.tumuhairwe.prep.heap;

/**
 * Max Heap is a complete binary tree in which each internal node is greater than or equal to the values of
 * its children
 *
 * - If node is stored in index-k, the leftChild = array[(2 * k) + 1]
 * - If node is stored in index-k, the rightChild = array[(2 * k) + 2]
 *
 *  * Typically represented as a anrray
 *  * - with root element arr[0]
 *  * - parentNode = arr[(index-1) / 2]
 *  * - leftChild node = arr[ (2 * index) + 1]
 *  * - rightChild node = arr[ (2 * index) + 2]
 *
 * ref: https://www.geeksforgeeks.org/max-heap-in-java/
 */
public class MaxHeap {

    private int[] heap;
    private int size;
    private int maxSize;

    private static final int FRONT = 0;
    public MaxHeap(int initialCapacity){
        this.heap = new int[initialCapacity * 2];
        this.heap[0] = Integer.MAX_VALUE;
        this.size = 0;
    }

    public int getParent(int position){
        return  (position - 1)/2;
    }
    public int getLeftChild(int position){
        return (position * 2) + 1;
    }
    public int getRightChild(int position){
        return (position * 2) + 2;
    }
    public boolean isLeaf(int position){
        return position > (size / 2) && position <= size;
    }
    /**
     * @return The root of the MinHeap ( TC = O(1) )
     */
    public int getMax(){
        return this.heap[0];
    }
    private void swap(int left, int right){
        int temp = heap[left];
        heap[left] = heap[right];
        heap[right] = temp;
    }
    public void maxHeapify(int position){
        if(isLeaf(position)){
            return;
        }

        if(heap[position] < heap[getLeftChild(position)]
        || heap[position] < heap[getRightChild(position)]){
            if(heap[getLeftChild(position)] > heap[getRightChild(position)]){
                swap(position, getLeftChild(position));
                maxHeapify(getLeftChild(position));
            }
            else {
                swap(position, getRightChild(position));
                maxHeapify(position);
            }
        }
    }

    public void insert(int element){
        heap[size] = element;

        // traverse up and fix the violated property if any
        int current = size;
        while (heap[current] > heap[getParent(current)]){
            swap(current, getParent(current));
            current = getParent(current);
        }
        size++;
    }
    public int extractMax(){
        int popped = heap[0];
        heap[0] = heap[--size];
        maxHeapify(0);
        return popped;
    }

    public void print(){
        for (int i = 0; i < size/2; i++) {
            System.out.println("Parent node  -> " + heap[i]);

            // if the child is out of the bound of the array
            if(getLeftChild(i) < size){
                System.out.println("Left Child -> " + heap[getLeftChild(i)]);
            }

            // the right child index must not be out of the index of the array
            if(getRightChild(i) < size){
                System.out.println("Right Child -> " + heap[getRightChild(i)]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("The min heap is ");

        int[] vals = new int[]{5, 3, 17, 10, 84, 19, 6, 22, 9};
        MaxHeap maxHeap = new MaxHeap(vals.length);
        for (int i = 0; i < vals.length; i++) {
            maxHeap.insert(i);
        }

        maxHeap.print();

        System.out.println("The maxVal == " + maxHeap.extractMax());
    }
}
