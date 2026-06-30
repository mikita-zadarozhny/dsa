package org.mikita.datastructure.tree.segment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LazyArraySegmentTreeTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 5, 18),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 45),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 100, 1000, 0)
        );
    }

    public static Stream<Arguments> queryAndOnRangeAddTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 3, 5, 10, 2, 5, 48),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 1, 2, 5, 22),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 4, -1, 2, 5, 15),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 0, 1, 2, 5, 18),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 4, 1, 2, 5, 19)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        LazyArraySegmentTree lazyArraySegmentTree = new LazyArraySegmentTree(data);

        // when
        int actual = lazyArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndOnRangeAddTestCases")
    void shouldAddOnRange_thenQuery(int[] data, int targetLeft, int targetRight, int targetValue,
                                    int qLeft, int qRight, int expectedResult) {
        // given
        LazyArraySegmentTree lazyArraySegmentTree = new LazyArraySegmentTree(data);

        // when
        lazyArraySegmentTree.addOnRange(targetLeft, targetRight, targetValue);
        int actual = lazyArraySegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }
}