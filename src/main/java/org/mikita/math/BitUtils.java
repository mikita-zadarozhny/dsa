package org.mikita.math;

public class BitUtils {

    public long leastSignificantBit(long value) {
        return value & (-value);
    }
}