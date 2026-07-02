package org.mikita.datastructure.tree.segment.impl;

import org.mikita.datastructure.tree.segment.AbstractArrayBasedSegmentTree;

public class MaxValueArraySegmentTree extends AbstractArrayBasedSegmentTree {

    public MaxValueArraySegmentTree(int[] data) {
        super(data, Integer.MIN_VALUE);
    }

    @Override
    protected int merge(int value1, int value2) {
        return Math.max(value1, value2);
    }
}
