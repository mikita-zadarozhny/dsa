package org.mikita.algorithm.dp.kadane;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class KadaneTest {

    public static Stream<Arguments> arrayAndMaxSubArraySum() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 36),
                Arguments.of(new int[] {1, 2, 3, 4, 6, -40, 7, 8}, 16),
                Arguments.of(new int[] {1, 2, 3, 4, 5, -40, 7, 8}, 15),
                Arguments.of(new int[] {1, 2, 3, 4, 4, -40, 7, 8}, 15),
                Arguments.of(new int[] {1, 3, -6, 7, -1, 2, 2}, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("arrayAndMaxSubArraySum")
    void shouldFindMaxSubArraySum(int[] array, int expected) {
        // given
        Kadane kadane = new Kadane();

        // when
        int actual = kadane.findMaxSubArraySum(array);

        // then
        assertEquals(expected, actual);
    }
}
