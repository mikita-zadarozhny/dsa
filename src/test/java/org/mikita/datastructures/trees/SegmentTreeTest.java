package org.mikita.datastructures.trees;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTreeTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 5, 18),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 45),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 100, 1000, 0)
        );
    }

    public static Stream<Arguments> queryAndUpdateTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 2, 5, 23),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 2, 5, 14)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        SegmentTree segmentTree = new SegmentTree(data);

        // when
        int actual = segmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndUpdateTestCases")
    void shouldUpdate_thenQuery(int[] data, int updateIndex, int updateValue,
                                int qLeft, int qRight, int expectedResult) {
        // given
        SegmentTree segmentTree = new SegmentTree(data);

        // when
        segmentTree.update(updateIndex, updateValue);
        int actual = segmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }
}