package org.mikita.datastructure.array.sparsetable.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.datastructure.array.sparsetable.impl.MaxValueSparseTable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxValueSparseTableTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 0, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 1, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 2, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 3, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 0, 4, 7),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 4, 7, 12),
                Arguments.of(new int[] {7, 2, 3, 0, 5, 10, 3, 12, 18}, 7, 8, 18)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        MaxValueSparseTable maxValueSparseTable = new MaxValueSparseTable(data);

        // when
        int actual = maxValueSparseTable.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        // given
        int[] data = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        MaxValueSparseTable maxValueSparseTable = new MaxValueSparseTable(data);

        // when
        String actual = maxValueSparseTable.toString();

        // then
        assertEquals("""
                [7, 7, 7, 12]
                [2, 3, 5, 18]
                [3, 3, 10, 12]
                [0, 5, 10, 10]
                [5, 10, 12, 12]
                [10, 10, 18, -2147483648]
                [3, 12, 12, -2147483648]
                [12, 18, -2147483648, -2147483648]
                [18, -2147483648, -2147483648, -2147483648]
                """, actual);
    }
}