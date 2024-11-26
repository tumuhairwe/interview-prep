package com.tumuhairwe.prep.pramp;

/**
 * Pramp
 * Given: find the minimal Sales Path cost in its distribution tree. Given a node rootNode,
 * ref: https://www.pramp.com/challenge/15oxrQx6LjtQj9JK9XqA
 */
public class SalesPath {

    static class Node {

        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = new Node[0];
            parent = null;
        }

        @Override
        public String toString() {
            return cost +"";
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n5 = new Node(5);
        n5.children = new Node[]{new Node(4)};

        Node n3 = new Node(3);

        // l1
        Node n2 = new Node(2);
        Node n1 = new Node(1);
        n1.children = new Node[]{new Node(1)};
        n2.children = new Node[]{n1};

        // l3
        Node n0_2 = new Node(0);
        n0_2.children = new Node[]{new Node(10)};

        n3.children = new Node[]{n2, n0_2};

        // l3
        Node n6 = new Node(6);
        n6.children = new Node[]{new Node(1), new Node(5)};

        n0.children = new Node[]{n5, n3, n6};

        System.out.println("The shortest path is " + getCheapestCost(n0));
    }
    static int getCheapestCost(Node rootNode) {

            if (rootNode.children.length == 0) {
                return rootNode.cost;
            }

            int minChildCost = Integer.MAX_VALUE;
            for(Node child : rootNode.children){
                int curChildMinCost = getCheapestCost(child);
                minChildCost = Math.min(minChildCost, curChildMinCost);
            }

            return rootNode.cost + minChildCost;
        }
}
