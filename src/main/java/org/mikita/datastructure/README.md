# Data Structures

## Array

### Sparse Table
- [RangeSumSparseTable](array/sparsetable/impl/RangeSumSparseTable.java) -
  Sparse table implementation of looking for a sum of elements of a sub-array.
- [MaxValueSparseTable](array/sparsetable/impl/MaxValueSparseTable.java) -
  Sparse table implementation of looking for the maximal element within a sub-array.
- [MinValueSparseTable](array/sparsetable/impl/MinValueSparseTable.java) -
  Sparse table implementation of looking for the minimal elements within a sub-array.

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
- [ArrayHeap](heap/impl/ArrayHeap.java) -
  Array-based implementation of a Heap data structure. Heap is used to find the element
  that posses either the highest or the lowest value out of all the values stored in this 
  data structure. This implementation supports both max heap and min heap modes.

## Tree

### AVL Tree
- [LinkedNodeAvlTree.java](tree/avl/impl/LinkedNodeAvlTree.java) -
  Linked-node based implementation of the AVL tree data structure. It is a self-balancing
  binary search tree in which the height difference between the left and right subtrees
  of every node is at most one. When this condition is violated, rotations are performed
  to rebalance the tree.

### Fenwick Tree
- [FenwickTree](tree/fenwick/FenwickTree.java) -
  Array-based fenwick tree implementation of updatable prefix sum data structure.
  Explanation is [here](/docs/fenwick-tree/fenwick-tree.md).

### Prefix Tree
- [LinkedNodePrefixTree](tree/prefix/impl/LinkedNodePrefixTree.java)
  Linked-node based implementation of a prefix tree data structure (a.k.a. Trie, which is derived 
  from a word `reTRIEval`). This data structure serves to store and retrieve strings by breaking
  them down into one-character tokens and storing them in a tree data structure. It is used
  for an efficient word lookup.

### Segment Tree
- [RangeSumArraySegmentTree](tree/segment/impl/RangeSumArraySegmentTree.java) -
  Array-based implementation of a segment tree data structure for looking for a sum of elements of a sub-array.
- [MaxValueArraySegmentTree](tree/segment/impl/MaxValueArraySegmentTree.java) -
  Array-based implementation of a segment tree data structure for looking for the maximal element within a sub-array.
- [MinValueArraySegmentTree.java](tree/segment/impl/MinValueArraySegmentTree.java) -
  Array-based implementation of a segment tree data structure for looking for the minimal element within a sub-array.
- [LazyArraySegmentTree](tree/segment/impl/LazyArraySegmentTree.java) -
  Array-based implementation of a segment tree data structure with lazy propagation for range update operation.
- [LinkedNodeSegmentTree](tree/segment/impl/LinkedNodeSegmentTree.java) -
  Linked-node implementation of a segment tree data structure.
