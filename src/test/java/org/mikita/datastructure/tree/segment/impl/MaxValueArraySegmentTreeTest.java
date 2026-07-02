package org.mikita.datastructure.tree.segment.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxValueArraySegmentTreeTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 5, 6),
                Arguments.of(new int[] {-1, -2, -3, -4, -5, -6, -7, -8, -9}, 2, 5, -3),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 9),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 100, 1000, -2147483648)
        );
    }

    public static Stream<Arguments> queryAndSetTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 2, 5, 10),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 2, 5, 6)
        );
    }

    public static Stream<Arguments> queryAndAddTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 2, 5, 15),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 2, 5, 6)
        );
    }

    public static Stream<Arguments> queryAndOnRangeAddTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 5, 10, 2, 5, 16),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 1, 2, 5, 7),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 4, 1, 2, 5, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        MaxValueArraySegmentTree maxValueArraySegmentTree = new MaxValueArraySegmentTree(data);

        // when
        int actual = maxValueArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndSetTestCases")
    void shouldSet_thenQuery(int[] data, int targetIndex, int targetValue,
                             int qLeft, int qRight, int expectedResult) {
        // given
        MaxValueArraySegmentTree maxValueArraySegmentTree = new MaxValueArraySegmentTree(data);

        // when
        maxValueArraySegmentTree.set(targetIndex, targetValue);
        int actual = maxValueArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndAddTestCases")
    void shouldAdd_thenQuery(int[] data, int targetIndex, int targetValue,
                             int qLeft, int qRight, int expectedResult) {
        // given
        MaxValueArraySegmentTree maxValueArraySegmentTree = new MaxValueArraySegmentTree(data);

        // when
        maxValueArraySegmentTree.add(targetIndex, targetValue);
        int actual = maxValueArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndOnRangeAddTestCases")
    void shouldAddOnRange_thenQuery(int[] data, int targetLeft, int targetRight, int targetValue,
                                    int qLeft, int qRight, int expectedResult) {
        // given
        MaxValueArraySegmentTree maxValueArraySegmentTree = new MaxValueArraySegmentTree(data);

        // when
        maxValueArraySegmentTree.addOnRange(targetLeft, targetRight, targetValue);
        int actual = maxValueArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }
}