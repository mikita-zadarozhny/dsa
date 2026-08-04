package org.mikita.datastructure.probabilistic.bloomfilter.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBloomFilterTest {

    public static final int[] STORED_ITEMS = {1, 4, 7, 9, 10, 12, 321};

    public static Stream<Arguments> storedItems() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(4),
                Arguments.of(7),
                Arguments.of(9),
                Arguments.of(10),
                Arguments.of(12),
                Arguments.of(321)
        );
    }

    @ParameterizedTest
    @MethodSource("storedItems")
    void shouldContainItem_whenItemIsStored(int targetItem) {
        // given
        SimpleBloomFilter<Integer> simpleBloomFilter = new SimpleBloomFilter<>(10_000, 5);

        for (int item : STORED_ITEMS) {
            simpleBloomFilter.add(item);
        }

        // when
        boolean actual = simpleBloomFilter.contains(targetItem);

        // then
        assertTrue(actual);
    }

    public static Stream<Arguments> absentItemsWithoutHashCollisions() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0),
                Arguments.of(2),
                Arguments.of(312),
                Arguments.of(400),
                Arguments.of(Integer.MIN_VALUE),
                Arguments.of(Integer.MAX_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource("absentItemsWithoutHashCollisions")
    void shouldNotContainItem_whenItemWasNotAddedAndNoHashCollisionOccurs(int targetItem) {
        // given
        SimpleBloomFilter<Integer> simpleBloomFilter = new SimpleBloomFilter<>(10_000, 5);

        for (int item : STORED_ITEMS) {
            simpleBloomFilter.add(item);
        }

        // when
        boolean actual = simpleBloomFilter.contains(targetItem);

        // then
        assertFalse(actual);
    }
}
