package org.mikita.math.modular;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModularUtilsTest {

    public static Stream<Arguments> modularExponentiationTestCases() {
        return Stream.of(
                Arguments.of(3, 4, 5, 1),
                Arguments.of(2, 10, 1000, 24),
                Arguments.of(7, 256, 13, 9),
                Arguments.of(5, 0, 7, 1),
                Arguments.of(0, 5, 11, 0),
                Arguments.of(12345, 1, 10, 5),
                Arguments.of(5, 3, 1_000_000_007, 125),
                Arguments.of(59, 41, 1_000_000_007, 44723187)
        );
    }

    @ParameterizedTest
    @MethodSource("modularExponentiationTestCases")
    void modularExponentiation(long base, long exponent, long mod, long expected) {

        // given
        ModularUtils modularUtils = new ModularUtils();

        // when
        long actual = modularUtils.modularExponentiation(base, exponent, mod);

        // then
        assertEquals(expected, actual);
    }
}