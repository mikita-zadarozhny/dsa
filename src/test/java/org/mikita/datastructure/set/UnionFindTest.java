package org.mikita.datastructure.set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnionFindTest {

    @Test
    void shouldFindSetRoots_whenSetsAreUnited() {
        // given
        UnionFind unionFind = new UnionFind(6);

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
    void shouldCompressPaths_whenSetsAreUnitedAndFound() {
        // given
        int unionSetSize = 10001;
        UnionFind unionFind = new UnionFind(unionSetSize);

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