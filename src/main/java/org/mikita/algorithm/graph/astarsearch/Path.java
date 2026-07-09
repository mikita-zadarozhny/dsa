package org.mikita.algorithm.graph.astarsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path {
    private final boolean isFound;
    private final double distance;
    private final List<int[]> pathSegments;

    private Path(boolean isFound, double distance) {
        this.isFound = isFound;
        this.distance = distance;
        this.pathSegments = new LinkedList<>();
    }

    protected void addPathSegment(int[] segment) {
        pathSegments.addFirst(segment.clone());
    }

    protected static Path notFound() {
        return new Path(false, -1);
    }

    protected static Path found(double distance, List<int[]> pathSegments) {
        Path path = new Path(true, distance);
        pathSegments.forEach(path::addPathSegment);
        return path;
    }

    public boolean isFound() {
        return isFound;
    }

    public double getDistance() {
        return distance;
    }

    public List<int[]> getPathSegments() {
        List<int[]> result = new ArrayList<>();
        for (int[] pathSegment : pathSegments) {
            result.add(pathSegment.clone());
        }
        return Collections.unmodifiableList(result);
    }

    public String toString() {
        if(pathSegments.isEmpty()) {
            return "[]";
        }
        if(pathSegments.size() == 1) {
            return "[%s, %s]".formatted(pathSegments.getFirst()[0], pathSegments.getFirst()[1]);
        }
        StringBuilder stringRepresentation = new StringBuilder();
        for (int i = 0; i < pathSegments.size(); i++) {
            int[] pathSegment = pathSegments.get(i);
            stringRepresentation.append("[")
                    .append(pathSegment[0]).append(", ")
                    .append(pathSegment[1])
                    .append("]");

            if(i < pathSegments.size() - 1) {
                stringRepresentation.append(" -> ");
            }
        }

        return stringRepresentation.toString();
    }
}