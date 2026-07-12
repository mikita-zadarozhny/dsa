package org.mikita.algorithm.greedy.pathfinding.dijkstra;

import java.util.*;

public class Dijkstra {

    private static class Node {
        private final Map<Integer, Integer> neighbors;

        private Node() {
            neighbors = new HashMap<>();
        }
    }

    private final Map<Integer, Node> graph;

    public Dijkstra() {
        graph = new HashMap<>();
    }

    public void addAll(int[][] nodes) {
        if(nodes.length == 0 || nodes[0].length != 3) {
            return;
        }

        for (int[] node : nodes) {
            add(node[0], node[1], node[2]);
        }
    }

    public void add(int from, int to, int weight) {
        graph.putIfAbsent(from, new Node());
        graph.putIfAbsent(to, new Node());

        graph.get(from).neighbors.put(to, weight);
    }

    public int findDistance(int start, int finish) {
        if(!graph.containsKey(start) || !graph.containsKey(finish)) {
            return Integer.MAX_VALUE;
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        priorityQueue.add(new int[] {start, 0});

        while(!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            visited.add(current[0]);

            if (finish == current[0]) {
                return current[1];
            }

            Node currentNode = graph.get(current[0]);
            currentNode.neighbors.forEach((neighbor, distance) -> {
                if(!visited.contains(neighbor)) {
                    priorityQueue.add(new int[]{neighbor, current[1] + distance});
                }
            });
        }

        return Integer.MAX_VALUE;
    }
}
