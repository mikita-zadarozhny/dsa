package org.mikita.datastructure.tree.avl.impl;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedNodeAvlTreeTest {

    @Test
    void shouldInsert_andPerformLeftLeftRotation() {
        LinkedNodeAvlTree<Integer> linkedNodeAvlTree = new LinkedNodeAvlTree<Integer>(Comparator.naturalOrder());

        linkedNodeAvlTree.insert(5);
        linkedNodeAvlTree.insert(4);
        linkedNodeAvlTree.insert(3);
        linkedNodeAvlTree.insert(2);
        linkedNodeAvlTree.insert(1);
        linkedNodeAvlTree.insert(0);

        List<Integer> list = linkedNodeAvlTree.toList();
        assertEquals(List.of(2, 1, 0, 4, 3, 5), list);
    }

    @Test
    void shouldInsert_andPerformRightRightRotation() {
        LinkedNodeAvlTree<Integer> linkedNodeAvlTree = new LinkedNodeAvlTree<Integer>(Comparator.naturalOrder());

        linkedNodeAvlTree.insert(0);
        linkedNodeAvlTree.insert(1);
        linkedNodeAvlTree.insert(2);
        linkedNodeAvlTree.insert(3);
        linkedNodeAvlTree.insert(4);
        linkedNodeAvlTree.insert(5);

        List<Integer> list = linkedNodeAvlTree.toList();
        assertEquals(List.of(3, 1, 0, 2, 4, 5), list);
    }
}