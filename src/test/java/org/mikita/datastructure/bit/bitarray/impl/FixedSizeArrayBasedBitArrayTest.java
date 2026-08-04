package org.mikita.datastructure.bit.bitarray.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FixedSizeArrayBasedBitArrayTest {

    public static Stream<Arguments> initializationHappyTestCases() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(63, 1),
                Arguments.of(64, 1),
                Arguments.of(65, 2),
                Arguments.of(128, 2),
                Arguments.of(100, 2),
                Arguments.of(129, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("initializationHappyTestCases")
    void shouldInitializeBitArray(int size, int expected) {
        // when
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // then
        assertEquals(expected, bitArray.getWords().length);
    }

    public static Stream<Arguments> initializationFailureTestCases() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(0)
        );
    }

    @ParameterizedTest
    @MethodSource("initializationFailureTestCases")
    void shouldInitializeBitArray(int size) {
        // when
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> new FixedSizeArrayBasedBitArray(size));

        // then
        assertEquals("Size should be more than 0", actual.getMessage());
    }

    public static Stream<Arguments> setHappyTestCases() {
        return Stream.of(
                Arguments.of(100, new int[]{32, 16, 20, 1, 99}, new long[]{
                        0b00000000_00000000_00000000_00000001_00000000_00010001_00000000_00000010L,
                        0b00000000_00000000_00000000_00001000_00000000_00000000_00000000_00000000L
                })
        );
    }

    @ParameterizedTest
    @MethodSource("setHappyTestCases")
    void shouldSet_whenIndexesAreWithinBoundaries_thenBitsAreSet(int size, int[] setIndexes, long[] expectedWords) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        for (int setIndex : setIndexes) {
            bitArray.set(setIndex);
        }

        // then
        assertArrayEquals(expectedWords, bitArray.getWords());
    }

    public static Stream<Arguments> setFailureTestCases() {
        return Stream.of(
                Arguments.of(100, -1),
                Arguments.of(100, 100),
                Arguments.of(100, 101)
        );
    }

    @ParameterizedTest
    @MethodSource("setFailureTestCases")
    void shouldThrowException_whenSetBits_andIndexesAreOutOfBoundaries(int size, int index) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        IndexOutOfBoundsException actual = assertThrows(IndexOutOfBoundsException.class, () -> bitArray.set(index));

        // then
        assertEquals("Index out of range: %d".formatted(index), actual.getMessage());
    }

    public static Stream<Arguments> flipHappyTestCases() {
        return Stream.of(
                Arguments.of(100, new int[]{32, 16, 20, 1, 99, 16}, new long[]{
                        0b00000000_00000000_00000000_00000001_00000000_00010000_00000000_00000010L,
                        0b00000000_00000000_00000000_00001000_00000000_00000000_00000000_00000000L
                })
        );
    }

    @ParameterizedTest
    @MethodSource("flipHappyTestCases")
    void shouldFlip_whenIndexesAreWithinBoundaries_thenBitsAreFlipped(int size, int[] flipIndexes, long[] expectedWords) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        for (int flipIndex : flipIndexes) {
            bitArray.flip(flipIndex);
        }

        // then
        assertArrayEquals(expectedWords, bitArray.getWords());
    }

    public static Stream<Arguments> flipFailureTestCases() {
        return Stream.of(
                Arguments.of(100, -1),
                Arguments.of(100, 100),
                Arguments.of(100, 101)
        );
    }

    @ParameterizedTest
    @MethodSource("flipFailureTestCases")
    void shouldThrowException_whenFlipBits_andIndexesAreOutOfBoundaries(int size, int index) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        IndexOutOfBoundsException actual = assertThrows(IndexOutOfBoundsException.class, () -> bitArray.flip(index));

        // then
        assertEquals("Index out of range: %d".formatted(index), actual.getMessage());
    }

    public static Stream<Arguments> clearHappyTestCases() {
        return Stream.of(
                Arguments.of(100, new int[]{32, 16, 20, 1, 99}, new int[]{32, 16},
                        new long[]{
                                0b00000000_00000000_00000000_00000000_00000000_00010000_00000000_00000010L,
                                0b00000000_00000000_00000000_00001000_00000000_00000000_00000000_00000000L
                        })
        );
    }

    @ParameterizedTest
    @MethodSource("clearHappyTestCases")
    void shouldClear_whenIndexesAreWithinBoundaries_thenBitsAreCleared(int size, int[] setIndexes, int[] clearIndexes, long[] expectedWords) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        for (int setIndex : setIndexes) {
            bitArray.set(setIndex);
        }

        for (int clearIndex : clearIndexes) {
            bitArray.flip(clearIndex);
        }

        // then
        assertArrayEquals(expectedWords, bitArray.getWords());
    }

    public static Stream<Arguments> clearFailureTestCases() {
        return Stream.of(
                Arguments.of(100, -1),
                Arguments.of(100, 100),
                Arguments.of(100, 101)
        );
    }

    @ParameterizedTest
    @MethodSource("clearFailureTestCases")
    void shouldThrowException_whenClearBits_andIndexesAreOutOfBoundaries(int size, int index) {
        // given
        FixedSizeArrayBasedBitArray bitArray = new FixedSizeArrayBasedBitArray(size);

        // when
        IndexOutOfBoundsException actual = assertThrows(IndexOutOfBoundsException.class, () -> bitArray.clear(index));

        // then
        assertEquals("Index out of range: %d".formatted(index), actual.getMessage());
    }
}
