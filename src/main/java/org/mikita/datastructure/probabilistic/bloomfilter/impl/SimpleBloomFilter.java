package org.mikita.datastructure.probabilistic.bloomfilter.impl;

import org.mikita.datastructure.bit.bitarray.BitArray;
import org.mikita.datastructure.bit.bitarray.impl.FixedSizeArrayBasedBitArray;
import org.mikita.datastructure.probabilistic.bloomfilter.BloomFilter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimpleBloomFilter<T> implements BloomFilter<T> {

    private final int size;
    private final BitArray bitArray;
    private final int hashIterations;

    public SimpleBloomFilter(int size, int hashIterations) {
        this.size = size;
        this.bitArray = new FixedSizeArrayBasedBitArray(size);
        this.hashIterations = hashIterations;
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        for(int i = 0; i < hashIterations; i++) {
            String seededItem = "%d:%d".formatted(hashCode, i);
            int bitIndex = convertToMD5(seededItem).mod(BigInteger.valueOf(size)).intValue();
            bitArray.set(bitIndex);
        }
    }

    @Override
    public boolean contains(T value) {
        int hashCode = value.hashCode();
        for(int i = 0; i < hashIterations; i++) {
            String seededItem = "%d:%d".formatted(hashCode, i);
            int bitIndex = convertToMD5(seededItem).mod(BigInteger.valueOf(size)).intValue();
            if(!bitArray.get(bitIndex)) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger convertToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
