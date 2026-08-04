package org.mikita.datastructure.bit.bitarray.impl;

import org.mikita.datastructure.bit.bitarray.BitArray;

public class FixedSizeArrayBasedBitArray implements BitArray {

    private final int size;
    private final long[] words;

    public FixedSizeArrayBasedBitArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be more than 0");
        }

        this.size = size;
        int numberOfWords = (int)Math.ceil((double)size / Long.SIZE);
        this.words = new long[numberOfWords];
    }

    @Override
    public void set(int index) {
        validate(index);

        // division by 64
        int targetWord = index >>> 6;

        // index modulo 64
        int targetBit = index & 63;

        words[targetWord] = words[targetWord] | (1L << targetBit);
    }

    @Override
    public boolean flip(int index) {
        validate(index);

        // division by 64
        int targetWord = index >>> 6;

        // index modulo 64
        int targetBit = index & 63;

        words[targetWord] = words[targetWord] ^ (1L << targetBit);

        return (words[targetWord] & (1L << targetBit)) != 0;
    }

    @Override
    public void clear(int index) {
        validate(index);

        // division by 64
        int targetWord = index >>> 6;

        // index modulo 64
        int targetBit = index & 63;

        words[targetWord] = words[targetWord] & (~(1L << targetBit));
    }

    @Override
    public boolean get(int index) {
        validate(index);

        // division by 64
        int targetWord = index >>> 6;

        // index modulo 64
        int targetBit = index & 63;

        return (words[targetWord] & (1L << targetBit)) != 0;
    }

    public long[] getWords() {
        return words.clone();
    }

    private void validate(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
