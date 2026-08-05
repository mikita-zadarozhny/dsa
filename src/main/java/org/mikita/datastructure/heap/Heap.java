package org.mikita.datastructure.heap;


public interface Heap <T> {

    void add(T value);

    void addAll(T[] values);

    T peek();

    T poll();

    boolean isEmpty();
}
