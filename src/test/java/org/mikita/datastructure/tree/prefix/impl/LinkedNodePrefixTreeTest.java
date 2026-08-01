package org.mikita.datastructure.tree.prefix.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedNodePrefixTreeTest {

    public static final List<String> VOCABULARY;

    static {
        VOCABULARY = new ArrayList<>();
        VOCABULARY.add("Hello");
        VOCABULARY.add("World");
        VOCABULARY.add("hello");
        VOCABULARY.add("world");
        VOCABULARY.add("Helicopter");
        VOCABULARY.add(null); // should be ignored
    }

    public static Stream<Arguments> prefixTreeWordSearch_HappyTestCases() {
        return Stream.of(
                Arguments.of(VOCABULARY, "Helicopter", true),
                Arguments.of(VOCABULARY, "Helicopterr", false),
                Arguments.of(VOCABULARY, "helicopter", false),
                Arguments.of(VOCABULARY, "Helicopte", false),
                Arguments.of(VOCABULARY, "Hel", false),
                Arguments.of(VOCABULARY, "Hello", true),
                Arguments.of(VOCABULARY, "hello", true),
                // even though 'null' is present in the vocabulary,
                // it will be ignored by the prefix tree.
                Arguments.of(VOCABULARY, null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("prefixTreeWordSearch_HappyTestCases")
    void shouldCheckWhetherExists_whenQueryIsValid(List<String> vocabulary, String query, boolean expected) {

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