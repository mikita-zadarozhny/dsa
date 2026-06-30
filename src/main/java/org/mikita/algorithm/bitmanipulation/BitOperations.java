package org.mikita.algorithm.bitmanipulation;

public class BitOperations {

    public long leastSignificantBit(long value) {
        return value & (-value);
    }
}
