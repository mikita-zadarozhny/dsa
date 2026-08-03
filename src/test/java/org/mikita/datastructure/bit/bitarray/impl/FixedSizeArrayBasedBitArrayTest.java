package org.mikita.datastructure.bit.bitarray.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FixedSizeArrayBasedBitArrayTest {

    public static Stream<Arguments> setHappyTestCases() {
        return Stream.of(
                Arguments.of(100, 32, new long[] {
                        0b00000000_00000000_00000000_00000001_00000000_00000000_00000000_00000000L,
                        0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L
                }),
                Arguments.of(100, 96, new long[] {
                        0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L,
                        0b00000000_00000000_00000000_00000001_00000000_00000000_00000000_00000000L
                })
        );
    }

    @ParameterizedTest
    @MethodSource("setHappyTestCases")
    void set(int size, int index, long[] expectedWords) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        boolean initial = bitArray.get(index);
        bitArray.set(index);
        boolean actual = bitArray.get(index);

        // then
        assertFalse(initial);
        assertTrue(actual);
        assertArrayEquals(expectedWords, bitArray.getWords());
    }
}