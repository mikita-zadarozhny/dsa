package org.mikita.datastructure.bit.bitarray;

public interface BitArray {

    void set(int index);

    boolean flip(int index);

    void clear(int index);

    boolean get(int index);
}
