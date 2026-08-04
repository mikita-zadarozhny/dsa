package org.mikita.datastructure.probabilistic.bloomfilter;

public interface BloomFilter<T> {

    void add(T value);

    boolean contains(T value);
}
