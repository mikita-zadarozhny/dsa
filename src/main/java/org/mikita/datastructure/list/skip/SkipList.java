package org.mikita.datastructure.list.skip;

public interface SkipList <T> {

    void add(T value);

    T get(int index);

    boolean delete(int index);

    boolean contains(T value);
}
