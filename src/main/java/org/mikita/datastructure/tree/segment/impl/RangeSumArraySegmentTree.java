package org.mikita.datastructure.tree.segment.impl;

import org.mikita.datastructure.tree.segment.AbstractArrayBasedSegmentTree;

public class RangeSumArraySegmentTree extends AbstractArrayBasedSegmentTree {

    public RangeSumArraySegmentTree(int[] data) {
        super(data, 0);
    }

    @Override
    protected int merge(int value1, int value2) {
        return value1 + value2;
    }
}
