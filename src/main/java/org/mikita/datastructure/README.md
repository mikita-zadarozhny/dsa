# Data Structures

## Array

### Sparse Table
- [RangeSumSparseTable](array/sparsetable/impl/RangeSumSparseTable.java) -
  Sparse table implementation for looking for a sum of elements of a sub-array.
- [MaxValueSparseTable](array/sparsetable/impl/MaxValueSparseTable.java) -
  Sparse table implementation for looking for the maximal element within a sub-array.
- [MinValueSparseTable](array/sparsetable/impl/MinValueSparseTable.java) -
  Sparse table implementation for looking for the minimal elements within a sub-array.

## Disjoint Set Union

### UnionFind
- [SimpleUnionFind](disjointset/unionfind/impl/SimpleUnionFind.java) -
  This implementation does not use path compression or union by rank.
  As a result, trees can become deep, and operations may degrade to linear time (O(n)) in the worst case.
- [PathCompressionUnionFind](disjointset/unionfind/impl/PathCompressionUnionFind.java) -
  This implementation uses path compression to flatten trees during find operations,
  improving performance in practice. However, it does not use union by rank,
  so worst-case guarantees are weaker.
- [RankedPathCompressionUnionFind](disjointset/unionfind/impl/RankedPathCompressionUnionFind.java) -
  This implementation combines path compression and union by rank to keep trees shallow.
  It provides near-constant amortized time complexity for all operations.

## Heap
- [Heap](heap/Heap.java) -
  Array-based implementation of a Heap data structure. Heap are used to find the element
  that posses the highest value out of all the values stored in this data structure.

## Tree

### Fenwick Tree
- [FenwickTree](tree/fenwick/FenwickTree.java) -
  Array-based fenwick tree implementation for updatable prefix sum data structure.
  Explanation is [here](/docs/fenwick-tree/fenwick-tree.md).

### Segment Tree
- [RangeSumArraySegmentTree](tree/segment/impl/RangeSumArraySegmentTree.java) -
  Array-based implementation for a segment tree data structure for looking for a sum of elements of a sub-array.
- [MaxValueArraySegmentTree](tree/segment/impl/MaxValueArraySegmentTree.java) -
  Array-based implementation for a segment tree data structure for looking for the maximal element within a sub-array.
- [MinValueArraySegmentTree.java](tree/segment/impl/MinValueArraySegmentTree.java) -
  Array-based implementation for a segment tree data structure for looking for the minimal element within a sub-array.
- [LazyArraySegmentTree](tree/segment/impl/LazyArraySegmentTree.java) -
  Array-based implementation for a segment tree data structure with lazy propagation for range update operation.
- [LinkedNodeSegmentTree](tree/segment/impl/LinkedNodeSegmentTree.java) -
  Linked Node-based implementation for a segment tree data structure.
