package org.mikita.datastructure.tree.avl.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedNodeAvlTreeTest {

    public static Stream<Arguments> avlTreeRotationTestCases() {
        return Stream.of(
                Arguments.of(List.of(3, 2, 1), List.of(2, 1, 3)),
                Arguments.of(List.of(1, 2, 3), List.of(2, 1, 3)),
                Arguments.of(List.of(3, 1, 2), List.of(2, 1, 3)),
                Arguments.of(List.of(1, 3, 2), List.of(2, 1, 3)),
                Arguments.of(List.of(10, 5, 2, 1), List.of(5, 2, 1, 10)),
                Arguments.of(List.of(1, 5, 10, 15), List.of(5, 1, 10, 15)),
                Arguments.of(List.of(10, 20, 30, 25), List.of(20, 10, 30, 25)),
                Arguments.of(List.of(30, 20, 40, 10, 25, 22), List.of(25, 20, 10, 22, 30, 40)),
                Arguments.of(List.of(50, 20, 70, 10, 30, 60, 80), List.of(50, 20, 10, 30, 70, 60, 80)),
                Arguments.of(List.of(50, 20, 70, 10, 30, 25, 27), List.of(30, 20, 10, 25, 27, 50, 70))
        );
    }

    @ParameterizedTest
    @MethodSource("avlTreeRotationTestCases")
    void shouldInsert_andPerformRotation(List<Integer> insertions, List<Integer> expected) {

        // given
        LinkedNodeAvlTree<Integer> linkedNodeAvlTree = new LinkedNodeAvlTree<Integer>(Comparator.naturalOrder());

        // when
        insertions.forEach(linkedNodeAvlTree::insert);
        List<Integer> list = linkedNodeAvlTree.toList();

        // then
        assertEquals(expected, list);
    }
}