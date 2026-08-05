package org.mikita.datastructure.tree.avl.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedNodeAvlTreeTest {

    public static Stream<Arguments> insertAndRotateTestCases() {
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
    @MethodSource("insertAndRotateTestCases")
    void shouldInsert_andPerformRotation(List<Integer> insertions, List<Integer> expected) {

        // given
        LinkedNodeAvlTree<Integer> tree = new LinkedNodeAvlTree<Integer>(Comparator.naturalOrder());

        // when
        insertions.forEach(tree::insert);
        List<Integer> list = tree.toList();

        // then
        assertEquals(expected, list);
    }

    public static Stream<Arguments> deleteAndRotateTestCases() {
        return Stream.of(
                // 1. Delete a leaf; no rotation
                Arguments.of(
                        List.of(20, 10, 30),
                        List.of(10),
                        List.of(20, 30)
                ),

                // 2. Delete a node with one child; no rotation
                Arguments.of(
                        List.of(20, 10, 30, 25),
                        List.of(30),
                        List.of(20, 10, 25)
                ),

                // 3. Delete a node with two children
                // Assumes the in-order successor replaces the deleted node
                Arguments.of(
                        List.of(20, 10, 30, 25, 40),
                        List.of(20),
                        List.of(25, 10, 30, 40)
                ),

                // 4. Left-left imbalance after deletion:
                //
                //        30                         20
                //       /  \                       /  \
                //     20    40     delete 40      10   30
                //    /  \                             /
                //   10  25                           25
                //
                // Single right rotation
                Arguments.of(
                        List.of(30, 20, 40, 10, 25),
                        List.of(40),
                        List.of(20, 10, 30, 25)
                ),

                // 5. Right-right imbalance after deletion:
                //
                //       20                            30
                //      /  \                          /  \
                //    10    30       delete 10       20   40
                //         /  \                         \
                //        25  40                         25
                //
                // Single left rotation
                Arguments.of(
                        List.of(20, 10, 30, 25, 40),
                        List.of(10),
                        List.of(30, 20, 25, 40)
                ),

                // 6. Left-right imbalance after deletion:
                //
                //        30                           20
                //       /  \                         /  \
                //     10    40      delete 40       10   30
                //       \
                //        20
                //
                // Left rotation on 10, then right rotation on 30
                Arguments.of(
                        List.of(10, 30, 40, 20),
                        List.of(40),
                        List.of(20, 10, 30)
                ),

                // 7. Right-left imbalance after deletion:
                //
                //       20                            30
                //      /  \                          /  \
                //    10    40       delete 10       20   40
                //         /
                //        30
                //
                // Right rotation on 40, then left rotation on 20
                Arguments.of(
                        List.of(10, 20, 40, 30),
                        List.of(10),
                        List.of(30, 20, 40)
                ),

                // 8. Multiple deletions followed by a left-left rotation
                Arguments.of(
                        List.of(
                                50, 30, 70,
                                20, 40, 60, 80,
                                10, 25, 35, 45,
                                55, 65, 75, 85
                        ),
                        List.of(85, 80, 75),
                        List.of(
                                50,
                                30, 20, 10, 25, 40, 35, 45,
                                60, 55, 70, 65
                        )
                ),

                // 9. Multiple deletions followed by a left-right rotation
                Arguments.of(
                        List.of(40, 20, 60, 10, 30, 50, 70, 25, 35),
                        List.of(70, 60),
                        List.of(30, 20, 10, 25, 40, 35, 50)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("deleteAndRotateTestCases")
    void shouldDelete_andPerformRotation(List<Integer> insertions, List<Integer> deletions, List<Integer> expected) {

        // given
        LinkedNodeAvlTree<Integer> tree = new LinkedNodeAvlTree<Integer>(Comparator.naturalOrder());
        insertions.forEach(tree::insert);

        // when
        deletions.forEach(tree::delete);
        List<Integer> list = tree.toList();

        // then
        assertEquals(expected, list);
    }
}