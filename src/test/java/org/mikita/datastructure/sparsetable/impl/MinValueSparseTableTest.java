package org.mikita.datastructure.sparsetable.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinValueSparseTableTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 0, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 1, 2),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 2, 2),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 3, 0),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 4, 0),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 4, 7, 3),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 7, 8, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        MinValueSparseTable minValueSparseTable = new MinValueSparseTable(data);

        // when
        int actual = minValueSparseTable.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        // given
        int[] data = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        MinValueSparseTable minValueSparseTable = new MinValueSparseTable(data);

        // when
        String actual = minValueSparseTable.toString();

        // then
        assertEquals("""
                [7, 2, 0, 0]
                [2, 2, 0, 0]
                [3, 0, 0, 0]
                [0, 0, 0, 0]
                [5, 5, 3, 3]
                [10, 3, 3, 2147483647]
                [3, 3, 3, 2147483647]
                [12, 12, 2147483647, 2147483647]
                [18, 2147483647, 2147483647, 2147483647]
                """, actual);
    }
}