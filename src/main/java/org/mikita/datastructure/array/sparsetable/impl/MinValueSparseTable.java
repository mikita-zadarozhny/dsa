package org.mikita.datastructure.array.sparsetable.impl;

import org.mikita.datastructure.array.sparsetable.AbstractSparseTable;

public class MinValueSparseTable extends AbstractSparseTable {

    public MinValueSparseTable(int[] data) {
        super(data, Integer.MAX_VALUE);
    }

    @Override
    protected int merge(int value1, int value2) {
        return Math.min(value1, value2);
    }

    //O(1) lookup
    @Override
    public int query(int left, int right) {
        int length = right - left + 1;
        int j = log2(length);

        return merge(lookupTable[left][j], lookupTable[right - (1 << j) + 1][j]);
    }
}
