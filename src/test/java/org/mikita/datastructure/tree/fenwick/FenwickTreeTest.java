package org.mikita.datastructure.tree.fenwick;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FenwickTreeTest {

    public static Stream<Arguments> queryTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 5, 21),
                Arguments.of(new int[] {1, 2, 3, 4, 1, 6, 7, 8, 9}, 5, 17),
                Arguments.of(new int[] {1, 2, 3, 4, 10, 6, 7, 8, 9}, 5, 26),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 8, 45),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2, 6),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 1, 3),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 1),
                Arguments.of(new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80}, 0, 5),
                Arguments.of(new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80}, 1, 15),
                Arguments.of(new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80}, 3, 50),
                Arguments.of(new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80}, 15, 680)
        );
    }

    public static Stream<Arguments> queryAndUpdateTestCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 1, 5, 22),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 10, 5, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("queryTestCases")
    void shouldQuery(int[] data, int index, int expectedResult) {
        // given
        FenwickTree fenwickTree = new FenwickTree(data);

        // when
        int actual = fenwickTree.query(index);

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("queryAndUpdateTestCases")
    void shouldUpdate_thenQuery(int[] data, int updateIndex, int updateValue,
                                int index, int expectedResult) {
        // given
        FenwickTree fenwickTree = new FenwickTree(data);

        // when
        fenwickTree.update(updateIndex, updateValue);
        int actual = fenwickTree.query(index);

        // then
        assertEquals(expectedResult, actual);
    }
}