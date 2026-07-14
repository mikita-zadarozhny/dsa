package org.mikita.algorithm.twopointer.dutchnationalflag;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DutchNationalFlagTest {

    public static Stream<Arguments> dutchNationalFlagPositiveTestCases() {
        return Stream.of(
                Arguments.of(new int[] {0, 2, 1, 2, 1, 1, 0, 0}, new int[] {0, 0, 0, 1, 1, 1, 2, 2}),
                Arguments.of(new int[] {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}, new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}),
                Arguments.of(new int[] {0, 1, 2, 0, 1, 2}, new int[] {0, 0, 1, 1, 2, 2})
        );
    }

    public static Stream<Arguments> dutchNationalFlagNegativeTestCases() {
        return Stream.of(
                Arguments.of(new int[] {0, 2, 1, 2, 1, 1, 0, 8}, 8),
                Arguments.of(new int[] {0, 2, 1, 4, 1, 1, 0, 0}, 4),
                Arguments.of(new int[] {-4, 2, 1, 0, 1, 1, 0, 1}, -4)
        );
    }

    @ParameterizedTest
    @MethodSource("dutchNationalFlagPositiveTestCases")
    void shouldSort(int[] array, int[] expected) {
        // given
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag();

        // when
        dutchNationalFlag.sort(array);

        // then
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("dutchNationalFlagPositiveTestCases")
    void shouldSafeSort(int[] array, int[] expected) {
        // given
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag();

        // when
        int[] actual = dutchNationalFlag.safeSort(array);

        // then
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("dutchNationalFlagNegativeTestCases")
    void shouldThrowIllegalArgumentException_whenArrayWithIllegalElementsIsPassed(int[] array, int expected) {
        // given
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag();

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            dutchNationalFlag.safeSort(array);
        });

        // then
        assertEquals(illegalArgumentException.getMessage(),
                "Dutch National Flag algorithm supports only [0, 1, 2] elements, but met '%d'".formatted(expected));
    }
}