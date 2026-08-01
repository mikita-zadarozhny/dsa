# Algorithms

## Dynamic Programming
- [Binary Lifting](dp/binarylifting/BinaryLifting.java) -
  2D DP algorithm used for finding Kth ancestor of a node in a tree.  
- [Kadane](dp/kadane/Kadane.java) - 
  Array-based algorithm used for finding max sub-array sum.

## Graph

### Minimum Spanning Tree
Minimum Spanning Tree (MST) is a tree derived from a connected, weighted, and undirected graph 
that connects all the vertices with minimal possible total edge weight without cycles.
- [Kruskal](graph/mst/kruskal/Kruskal.java) -
  Greedy algorithm used for constructing a minimum spanning tree in a weighted graph by iteratively 
  selecting the smallest edge that preserves a valid (acyclic) structure. Kruskal's algorithm supports
  search for minimum spanning tree within a disjoint graph - the result is a minimum spanning forest.
- [Prim](graph/mst/prim/Prim.java) -
  Greedy algorithm used for constructing a minimum spanning tree in a weighted graph by randomly choosing
  start vertex and expanding the tree iteratively by connecting new vertices using edges with the smallest
  weight that preserves a valid (acyclic) structure. Prim's algorithm does not support search for minimum
  spanning tree within a disjoint graph.

### Shortest Path
- [Dijkstra](graph/shortestpath/dijkstra/Dijkstra.java) -
  Search algorithm used for finding the shortest path in a weighted graph.
- [EuclideanAStarSearch](graph/shortestpath/astarsearch/impl/EuclideanAStarSearch.java) -
  Informed search algorithm used for finding the shortest path in a weighted graph.
  This algorithm is an extended version of Dijkstra's Algorithm with a heuristic (based on Euclidean distance)
  for improved efficiency.
- [ManhattanAStarSearch](graph/shortestpath/astarsearch/impl/ManhattanAStarSearch.java) -
  Informed search algorithm used for finding the shortest path in a weighted graph.
  This algorithm is an extended version of Dijkstra's Algorithm with a heuristic (based on Manhattan distance)
  for improved efficiency.

## Two Pointer
- [DutchNationalFlag](twopointer/dutchnationalflag/DutchNationalFlag.java) - 
  Array-based algorithm used for efficient sorting for arrays that consists only of 0s, 1s, and 2s.
