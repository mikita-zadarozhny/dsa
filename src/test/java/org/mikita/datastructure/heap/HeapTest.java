package org.mikita.datastructure.heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mikita.datastructure.heap.Heap;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    public static Stream<Arguments> addAndPeekMaxHeapTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 9),
                Arguments.of(new int[]{1, 2, 3, 4, 10, 6, 7, 8, 9}, 10),
                Arguments.of(new int[]{15, 2, 3, 4, 10, 6, 7, 8, 9}, 15),
                Arguments.of(new int[]{15, 2, 3, 4, 10, 6, 7, 8, 9, 12, 13, 20, 23, 25, 99}, 99)
        );
    }

    public static Stream<Arguments> addAndPollMaxHeapTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                        new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}),
                Arguments.of(new int[]{15, 2, 3, 4, 10, 6, 7, 8, 9},
                        new int[]{15, 10, 9, 8, 7, 6, 4, 3, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("addAndPeekMaxHeapTestCases")
    void shouldAdd_whenMaxHeap_thenPeekMaxElement(int[] data, int expectedResult) {
        // given
        Heap heap = new Heap(Comparator.comparingInt(o -> o));

        // when
        heap.addAll(data);
        int actual = heap.peek();

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("addAndPollMaxHeapTestCases")
    void shouldAdd_whenMaxHeap_thenPollMaxElement(int[] data, int[] expectedResult) {
        // given
        Heap heap = new Heap(Comparator.comparingInt(o -> o));

        // when
        heap.addAll(data);
        int[] actual = new int[expectedResult.length];
        int index = 0;
        while(!heap.isEmpty()) {
            actual[index++] = heap.poll();
        }

        // then
        assertArrayEquals(expectedResult, actual);
    }


    public static Stream<Arguments> addAndPeekMinHeapTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 1),
                Arguments.of(new int[]{1, 2, 3, 4, 10, 6, 7, 8, 9}, 1),
                Arguments.of(new int[]{15, 2, 3, 4, 10, 6, 7, 8, 9}, 2)
        );
    }

    public static Stream<Arguments> addAndPollMinHeapTestCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arguments.of(new int[]{15, 2, 3, 4, 10, 6, 7, 8, 9},
                        new int[]{2, 3, 4, 6, 7, 8, 9, 10, 15})
        );
    }


    @ParameterizedTest
    @MethodSource("addAndPeekMinHeapTestCases")
    void shouldAdd_whenMinHeap_thenPeekMaxElement(int[] data, int expectedResult) {
        // given
        Heap heap = new Heap(Comparator.comparingInt(o -> -o));

        // when
        heap.addAll(data);
        int actual = heap.peek();

        // then
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @MethodSource("addAndPollMinHeapTestCases")
    void shouldAdd_whenMinHeap_thenPollMaxElement(int[] data, int[] expectedResult) {
        // given
        Heap heap = new Heap(Comparator.comparingInt(o -> -o));

        // when
        heap.addAll(data);
        int[] actual = new int[expectedResult.length];
        int index = 0;
        while(!heap.isEmpty()) {
            actual[index++] = heap.poll();
        }

        // then
        assertArrayEquals(expectedResult, actual);
    }
}
