package org.mikita.datastructure.heap;

import java.util.Comparator;

public class Heap {

    private int[] nodes;
    private int capacity = 10;
    private int size = 0;
    private final Comparator<Integer> comparator;

    public Heap(Comparator<Integer> comparator) {
        this.comparator = comparator;
        nodes = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int value) {
        if(size == capacity) {
            resize();
        }
        nodes[size] = value;
        bubbleUp(size);
        size++;
    }

    private void resize() {
        if(capacity >= Integer.MAX_VALUE / 2 - 1) {
            capacity = Integer.MAX_VALUE;
        } else {
            capacity *= 2;
        }

        int[] newNodes = new int[capacity];
        System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
        nodes = newNodes;
    }

    public void addAll(int[] values) {
        for (int value : values) {
            add(value);
        }
    }

    public int peek() {
        return nodes[0];
    }

    public int poll() {
        int result = nodes[0];
        size--;
        nodes[0] = nodes[size];
        nodes[size] = 0;
        bubbleDown(0);
        return result;
    }

    /**
     * if node is 0, then it is root
     * if node is odd, then it is left child
     * if node is even, then it is right child
     * left child is = parent_node * 2 + 1
     * right child is = parent_node * 2 + 2
     */
    private void bubbleUp(int node) {
        if(node == 0) {
            return;
        }
        int parent;
        if(node % 2 == 0) {
            parent = (node - 2) / 2;
        } else {
            parent = (node - 1) / 2;
        }

        int comparison = comparator.compare(nodes[node], nodes[parent]);

        if(comparison > 0) {
            int tmp = nodes[parent];
            nodes[parent] = nodes[node];
            nodes[node] = tmp;
            bubbleUp(parent);
        }
    }

    private void bubbleDown(int node) {
        int leftChild = node * 2 + 1;
        int rightChild = node * 2 + 2;

        if(leftChild >= size) {
            return;
        }

        if(rightChild >= size) {
            int comparison = comparator.compare(nodes[leftChild], nodes[node]);
            if(comparison > 0) {
                int tmp = nodes[leftChild];
                nodes[leftChild] = nodes[node];
                nodes[node] = tmp;
            }
            return;
        }

        int comparison = comparator.compare(nodes[leftChild], nodes[rightChild]);
        if (comparison > 0) {
            comparison = comparator.compare(nodes[leftChild], nodes[node]);
            if(comparison > 0) {
                int tmp = nodes[leftChild];
                nodes[leftChild] = nodes[node];
                nodes[node] = tmp;
            }
            bubbleDown(leftChild);
        } else {
            comparison = comparator.compare(nodes[rightChild], nodes[node]);
            if(comparison > 0) {
                int tmp = nodes[rightChild];
                nodes[rightChild] = nodes[node];
                nodes[node] = tmp;
            }
            bubbleDown(rightChild);
        }
    }
}
