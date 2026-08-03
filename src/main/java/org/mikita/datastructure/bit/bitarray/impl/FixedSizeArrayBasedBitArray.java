package org.mikita.datastructure.bit.bitarray.impl;

import org.mikita.datastructure.bit.bitarray.BitArray;

public class FixedSizeArrayBasedBitArray implements BitArray {

    private final int size;
    private final long[] bits;
    private final int arraySize;

    public FixedSizeArrayBasedBitArray(int size) {
        this.size = size;
        this.arraySize = (int)Math.ceil((double)size / Long.SIZE);
        this.bits = new long[arraySize];
    }

    @Override
    public void set(int index) {
        validate(index);

        int targetArray = index / Long.SIZE;
        int targetBit = index % Long.SIZE;
        bits[targetArray] = bits[targetArray] | (1L << targetBit);
    }

    @Override
    public boolean flip(int index) {
        validate(index);

        int targetArray = index / Long.SIZE;
        int targetBit = index % Long.SIZE;
        bits[targetArray] = bits[targetArray] ^ (1L << targetBit);

        return (bits[targetArray] & (1L << targetBit)) != 0;
    }

    @Override
    public void clear(int index) {
        validate(index);

        int targetArray = index / Long.SIZE;
        int targetBit = index % Long.SIZE;
        bits[targetArray] = bits[targetArray] & (~(1L << targetBit));
    }

    @Override
    public boolean get(int index) {
        validate(index);

        int targetArray = index / Long.SIZE;
        int targetBit = index % Long.SIZE;

        return (bits[targetArray] & (1L << targetBit)) != 0;
    }

    private void validate(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
