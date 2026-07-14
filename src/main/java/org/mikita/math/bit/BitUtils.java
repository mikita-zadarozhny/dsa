package org.mikita.math.bit;

public class BitUtils {

    public long leastSignificantBit(long value) {
        return value & (-value);
    }
}