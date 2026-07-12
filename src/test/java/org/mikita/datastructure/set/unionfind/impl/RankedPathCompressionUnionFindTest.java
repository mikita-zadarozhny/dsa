package org.mikita.datastructure.set.unionfind.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RankedPathCompressionUnionFindTest {

    @Test
    void shouldFindSetRoots_whenSetsAreUnited() {
        // given
        RankedPathCompressionUnionFind unionFind = new RankedPathCompressionUnionFind(6);

        // when
        unionFind.union(0, 1);
        unionFind.union(1, 2);
        unionFind.union(3, 4);

        // then
        assertEquals(unionFind.find(0), unionFind.find(1));
        assertEquals(unionFind.find(0), unionFind.find(2));
        assertEquals(unionFind.find(1), unionFind.find(2));
        assertEquals(unionFind.find(3), unionFind.find(4));
    }

    @Test
    void shouldBeConnected_whenSetsAreUnited() {
        // given
        SimpleUnionFind unionFind = new SimpleUnionFind(6);

        // when
        unionFind.union(0, 1);
        unionFind.union(1, 2);
        unionFind.union(3, 4);

        // then
        assertTrue(unionFind.connected(0, 1));
        assertTrue(unionFind.connected(0, 2));
        assertTrue(unionFind.connected(1, 2));
        assertTrue(unionFind.connected(3, 4));
    }

    @Test
    void shouldNotBeConnected_whenSetsAreNotUnited() {
        // given
        SimpleUnionFind unionFind = new SimpleUnionFind(6);

        // when
        unionFind.union(0, 1);
        unionFind.union(1, 2);
        unionFind.union(3, 4);

        // then
        assertFalse(unionFind.connected(0, 3));
        assertFalse(unionFind.connected(0, 4));
        assertFalse(unionFind.connected(1, 3));
        assertFalse(unionFind.connected(1, 4));
        assertFalse(unionFind.connected(1, 5));
    }

    @Test
    void shouldCompressPaths_whenSetsAreUnitedAndFound() {
        // given
        int unionSetSize = 10001;
        RankedPathCompressionUnionFind unionFind = new RankedPathCompressionUnionFind(unionSetSize);

        // when
        for (int i = 0; i < unionSetSize - 1; i++) {
            unionFind.union(i, i + 1);
        }

        // then
        int root = unionFind.find(0);
        for (int i = 1; i < unionSetSize; i++) {
            assertEquals(root, unionFind.find(i));
            assertEquals(root, unionFind.parents[i]);
        }
    }
}
