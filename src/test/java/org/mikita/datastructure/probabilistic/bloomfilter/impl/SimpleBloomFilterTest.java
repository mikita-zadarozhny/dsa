package org.mikita.datastructure.probabilistic.bloomfilter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBloomFilterTest {

    public static Stream<Arguments> bloomFilterTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 1, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 4, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 7, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 9, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 10, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 12, true),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 321, true),
                
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, -1, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 0, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 2, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 312, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, 400, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, Integer.MIN_VALUE, false),
                Arguments.of(new int[]{1, 4, 7, 9, 10, 12, 321}, Integer.MAX_VALUE, false)
        );
    }

    @ParameterizedTest
    @MethodSource("bloomFilterTestCases")
    void contains(int[] items, int targetItem, boolean expected) {
        // given
        SimpleBloomFilter<Integer> simpleBloomFilter = new SimpleBloomFilter<>(10_000, 5);

        for (int item : items) {
            simpleBloomFilter.add(item);
        }

        // when
        boolean actual = simpleBloomFilter.contains(targetItem);

        // then
        assertEquals(expected, actual);
    }
}