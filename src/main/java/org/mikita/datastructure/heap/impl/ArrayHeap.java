package org.mikita.datastructure.heap.impl;

import org.mikita.datastructure.heap.Heap;

import java.util.Comparator;

public class ArrayHeap<T> implements Heap<T> {

    private enum Mode {
        MIN_HEAP(-1),
        MAX_HEAP(1);

        private final int modifier;

        Mode(int modifier) {
            this.modifier = modifier;
        }

        private int apply(int comparison) {
            return comparison * modifier;
        }
    }

    /**
     * if node is 0, then it is root
     * if node is odd, then it is left child
     * if node is even, then it is right child
     * left child is = parent_node * 2 + 1
     * right child is = parent_node * 2 + 2
     */
    private T[] nodes;
    private int capacity = 10;
    private int size = 0;
    private final Comparator<T> comparator;
    private final Mode mode;

    private ArrayHeap(Comparator<T> comparator, Mode mode) {
        this.comparator = comparator;
        this.mode = mode;
        nodes = newArray(capacity);
    }

    public static <T> ArrayHeap<T> maxHeap(Comparator<T> comparator) {
        return new ArrayHeap<T>(comparator, Mode.MAX_HEAP);
    }

    public static <T> ArrayHeap<T> minHeap(Comparator<T> comparator) {
        return new ArrayHeap<T>(comparator, Mode.MIN_HEAP);
    }

    @SuppressWarnings("unchecked")
    private T[] newArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
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

        T[] newNodes = newArray(capacity);
        System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
        nodes = newNodes;
    }

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

        int comparison = mode.apply(comparator.compare(nodes[node], nodes[parent]));

        if(comparison > 0) {
            T tmp = nodes[parent];
            nodes[parent] = nodes[node];
            nodes[node] = tmp;
            bubbleUp(parent);
        }
    }

    @Override
    public void addAll(T[] values) {
        for (T value : values) {
            add(value);
        }
    }

    @Override
    public T peek() {
        return nodes[0];
    }

    @Override
    public T poll() {
        T result = nodes[0];
        size--;
        nodes[0] = nodes[size];
        nodes[size] = null;
        bubbleDown(0);
        return result;
    }

    private void bubbleDown(int node) {
        int leftChild = node * 2 + 1;
        int rightChild = node * 2 + 2;

        if(leftChild >= size) {
            return;
        }

        if(rightChild >= size) {
            int comparison = mode.apply(comparator.compare(nodes[leftChild], nodes[node]));
            if(comparison > 0) {
                T tmp = nodes[leftChild];
                nodes[leftChild] = nodes[node];
                nodes[node] = tmp;
            }
            return;
        }

        int comparison = mode.apply(comparator.compare(nodes[leftChild], nodes[rightChild]));
        if (comparison > 0) {
            comparison = mode.apply(comparator.compare(nodes[leftChild], nodes[node]));
            if(comparison > 0) {
                T tmp = nodes[leftChild];
                nodes[leftChild] = nodes[node];
                nodes[node] = tmp;
            }
            bubbleDown(leftChild);
        } else {
            comparison = mode.apply(comparator.compare(nodes[rightChild], nodes[node]));
            if(comparison > 0) {
                T tmp = nodes[rightChild];
                nodes[rightChild] = nodes[node];
                nodes[node] = tmp;
            }
            bubbleDown(rightChild);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
