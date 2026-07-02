package org.mikita.datastructure.tree.segment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedNodeSegmentTreeTest {

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

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        LinkedNodeSegmentTree linkedNodeSegmentTree = new LinkedNodeSegmentTree(data);

        // when
        int actual = linkedNodeSegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndSetTestCases")
    void shouldSet_thenQuery(int[] data, int targetIndex, int targetValue,
                             int qLeft, int qRight, int expectedResult) {
        // given
        LinkedNodeSegmentTree linkedNodeSegmentTree = new LinkedNodeSegmentTree(data);

        // when
        linkedNodeSegmentTree.set(targetIndex, targetValue);
        int actual = linkedNodeSegmentTree.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }
}