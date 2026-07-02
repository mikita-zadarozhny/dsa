package org.mikita.datastructure.sparsetable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
}