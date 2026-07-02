package org.mikita.datastructure.tree.segment.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RangeSumArraySegmentTreeTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 5, 18),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 45),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 100, 1000, 0)
        );
    }

    public static Stream<Arguments> queryAndSetTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 2, 5, 23),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 2, 5, 14)
        );
    }

    public static Stream<Arguments> queryAndAddTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 2, 5, 28),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 2, 5, 19)
        );
    }

    public static Stream<Arguments> queryAndOnRangeAddTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 5, 10, 2, 5, 48),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 1, 2, 5, 22),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 4, 1, 2, 5, 19)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        RangeSumArraySegmentTree rangeSumArraySegmentTree = new RangeSumArraySegmentTree(data);

        // when
        int actual = rangeSumArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndSetTestCases")
    void shouldSet_thenQuery(int[] data, int targetIndex, int targetValue,
                             int qLeft, int qRight, int expectedResult) {
        // given
        RangeSumArraySegmentTree rangeSumArraySegmentTree = new RangeSumArraySegmentTree(data);

        // when
        rangeSumArraySegmentTree.set(targetIndex, targetValue);
        int actual = rangeSumArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndAddTestCases")
    void shouldAdd_thenQuery(int[] data, int targetIndex, int targetValue,
                             int qLeft, int qRight, int expectedResult) {
        // given
        RangeSumArraySegmentTree rangeSumArraySegmentTree = new RangeSumArraySegmentTree(data);

        // when
        rangeSumArraySegmentTree.add(targetIndex, targetValue);
        int actual = rangeSumArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndOnRangeAddTestCases")
    void shouldAddOnRange_thenQuery(int[] data, int targetLeft, int targetRight, int targetValue,
                                    int qLeft, int qRight, int expectedResult) {
        // given
        RangeSumArraySegmentTree rangeSumArraySegmentTree = new RangeSumArraySegmentTree(data);

        // when
        rangeSumArraySegmentTree.addOnRange(targetLeft, targetRight, targetValue);
        int actual = rangeSumArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }
}