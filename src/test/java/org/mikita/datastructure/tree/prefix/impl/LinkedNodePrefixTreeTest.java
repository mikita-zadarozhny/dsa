package org.mikita.datastructure.tree.prefix.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedNodePrefixTreeTest {

    public static final List<String> VOCABULARY = List.of("Hello", "World", "hello", "world", "Helicopter");

    public static Stream<Arguments> prefixTreeWordSearchTestCases() {
        return Stream.of(
                Arguments.of(VOCABULARY, "Helicopter", true),
                Arguments.of(VOCABULARY, "Helicopterr", false),
                Arguments.of(VOCABULARY, "helicopter", false),
                Arguments.of(VOCABULARY, "Helicopte", false),
                Arguments.of(VOCABULARY, "Hel", false),
                Arguments.of(VOCABULARY, "Hello", true),
                Arguments.of(VOCABULARY, "hello", true)
        );
    }

    @ParameterizedTest
    @MethodSource("prefixTreeWordSearchTestCases")
    void exists(List<String> vocabulary, String query, boolean expected) {

        // given
        LinkedNodePrefixTree prefixTree = new LinkedNodePrefixTree();

        for (String word : vocabulary) {
            prefixTree.add(word);
        }

        // when
        boolean actual = prefixTree.exists(query);

        // then
        assertEquals(expected, actual);
    }
}