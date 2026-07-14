package org.mikita.datastructure.array.sparsetable.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.datastructure.array.sparsetable.impl.RangeSumSparseTable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RangeSumSparseTableTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 5, 18),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 45),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 10, 100, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int qLeft, int qRight, int expectedResult) {
        // given
        RangeSumSparseTable rangeSumSparseTable = new RangeSumSparseTable(data);

        // when
        int actual = rangeSumSparseTable.query(qLeft, qRight);

        // then
        assertEquals(expectedResult, actual);
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        // given
        int[] data = {7, 2, 3, 0, 5, 10, 3, 12, 18};
        RangeSumSparseTable rangeSumSparseTable = new RangeSumSparseTable(data);

        // when
        String actual = rangeSumSparseTable.toString();

        // then
        assertEquals("""
                [7, 9, 12, 42]
                [2, 5, 10, 53]
                [3, 3, 18, 33]
                [0, 5, 18, 18]
                [5, 15, 30, 30]
                [10, 13, 43, 0]
                [3, 15, 15, 0]
                [12, 30, 0, 0]
                [18, 0, 0, 0]
                """, actual);
    }
}