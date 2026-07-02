package org.mikita.datastructure.sparsetable.impl;

import org.mikita.datastructure.sparsetable.AbstractSparseTable;

public class RangeSumSparseTable extends AbstractSparseTable {

    public RangeSumSparseTable(int[] data) {
        super(data, 0);
    }

    @Override
    protected int merge(int value1, int value2) {
        return value1 + value2;
    }
}
