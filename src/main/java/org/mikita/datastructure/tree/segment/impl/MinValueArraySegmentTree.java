package org.mikita.datastructure.tree.segment.impl;

import org.mikita.datastructure.tree.segment.AbstractArrayBasedSegmentTree;

public class MinValueArraySegmentTree extends AbstractArrayBasedSegmentTree {

    public MinValueArraySegmentTree(int[] data) {
        super(data, Integer.MAX_VALUE);
    }

    @Override
    protected int merge(int value1, int value2) {
        return Math.min(value1, value2);
    }
}
