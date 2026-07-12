package org.mikita.algorithm.dp.binarylifting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryLiftingTest {

    public static Stream<Arguments> binaryLiftingTestCases() {
        return Stream.of(
                Arguments.of(new int[]{-1, 0, 0, 1, 1, 2, 2}, 5, 0, 5),
                Arguments.of(new int[]{-1, 0, 0, 1, 1, 2, 2}, 5, 1, 2),
                Arguments.of(new int[]{-1, 0, 0, 1, 1, 2, 2}, 5, 2, 0),
                Arguments.of(new int[]{-1, 0, 0, 1, 1, 2, 2}, 5, 3, -1),
                Arguments.of(new int[]{-1, 2, 3, 0}, 2, 1, 3),
                Arguments.of(new int[]{-1, 2, 3, 0}, 2, 3, -1),
                Arguments.of(new int[]{-1, 2, 3, 0}, 2, 2, 0),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 8, 6, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 9, 7, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 1, 1, 0),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 2, 5, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 4, 2, 0),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 7, 3, 0),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 3, 7, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 9, 6, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 3, 5, -1),
                Arguments.of(new int[]{-1, 0, 0, 1, 2, 0, 1, 3, 6, 1}, 8, 8, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("binaryLiftingTestCases")
    void shouldGetKthAncestor(int[] rootedTree, int node, int k, int expected) {
        // given
        BinaryLifting binaryLifting = new BinaryLifting(rootedTree);

        // when
        int actual = binaryLifting.getKthAncestor(node, k);

        // then
        assertEquals(expected, actual);
    }
}