package org.mikita.math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.math.bit.BitUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitUtilsTest {

    public static Stream<Arguments> leastSignificantBitTestCases() {
        return Stream.of(
                Arguments.of(0b0, 0b0),
                Arguments.of(0b0100, 0b0100),
                Arguments.of(0b0110, 0b0010),
                Arguments.of(0b0111, 0b0001)
        );
    }

    @ParameterizedTest
    @MethodSource("leastSignificantBitTestCases")
    void leastSignificantBit(long value, long expected) {
        // given
        BitUtils bitUtils = new BitUtils();

        // when
        long actual = bitUtils.leastSignificantBit(value);

        // then
        assertEquals(expected, actual);
    }
}