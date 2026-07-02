package org.mikita.datastructure.tree.segment.impl;

import org.mikita.datastructure.tree.segment.SegmentTree;

public class LinkedNodeSegmentTree implements SegmentTree {

    private static class Node {
        private int value;
        private final int leftRangeBoundary;
        private final int rightRangeBoundary;
        private Node leftNode;
        private Node rightNode;

        private Node(int left, int right) {
            leftRangeBoundary = left;
            rightRangeBoundary = right;
        }

        private Node(int value, int left, int right) {
            this.value = value;
            leftRangeBoundary = left;
            rightRangeBoundary = right;
        }

        private static Node buildNode(int left, int right, int[] data) {
            if(left == right) {
                return new Node(data[left], left, right);
            }

            int mid = left + (right - left) / 2;

            Node node = new Node(left, right);
            node.leftNode = buildNode(left, mid, data);
            node.rightNode = buildNode(mid + 1, right, data);

            node.value = node.leftNode.value + node.rightNode.value;
            return node;
        }

        private int query(int qLeft, int qRight) {
            if(leftRangeBoundary > qRight || qLeft > rightRangeBoundary) {
                return 0;
            }

            if(qLeft <= leftRangeBoundary && rightRangeBoundary <= qRight) {
                return value;
            }

            int result = 0;

            if(leftNode != null) {
                result += leftNode.query(qLeft, qRight);
            }
            if (rightNode != null) {
                result += rightNode.query(qLeft, qRight);
            }

            return result;
        }

        private void set(int targetIndex, int targetValue) {

            if(leftRangeBoundary == rightRangeBoundary) {
                value = targetValue;
                return;
            }

            int mid = leftRangeBoundary + (rightRangeBoundary - leftRangeBoundary) / 2;
            if(targetIndex <= mid) {
                leftNode.set(targetIndex, targetValue);
            } else {
                rightNode.set(targetIndex, targetValue);
            }

            value = leftNode.value + rightNode.value;
        }

        public void add(int targetIndex, int delta) {

            if(leftRangeBoundary == rightRangeBoundary) {
                value += delta;
                return;
            }

            int mid = leftRangeBoundary + (rightRangeBoundary - leftRangeBoundary) / 2;

            if(targetIndex <= mid) {
                leftNode.add(targetIndex, delta);
            } else {
                rightNode.add(targetIndex, delta);
            }

            value = leftNode.value + rightNode.value;
        }

        public void addOnRange(int targetLeft, int targetRight, int delta) {
            if(leftRangeBoundary > targetRight || targetLeft > rightRangeBoundary) {
                return;
            }

            if(leftRangeBoundary == rightRangeBoundary) {
                value += delta;
                return;
            }

            int mid = leftRangeBoundary + (rightRangeBoundary - leftRangeBoundary) / 2;

            leftNode.addOnRange(targetLeft, targetRight, delta);
            rightNode.addOnRange(targetLeft, targetRight, delta);

            value = leftNode.value + rightNode.value;
        }
    }

    private final Node root;

    public LinkedNodeSegmentTree(int[] data) {
        root = Node.buildNode(0, data.length - 1, data);
    }

    @Override
    public int query(int qLeft, int qRight) {
        return root.query(qLeft, qRight);
    }

    @Override
    public void set(int targetIndex, int targetValue) {
        root.set(targetIndex, targetValue);
    }

    @Override
    public void add(int targetIndex, int delta) {
        root.add(targetIndex, delta);
    }

    @Override
    public void addOnRange(int targetLeft, int targetRight, int delta) {
        root.addOnRange(targetLeft, targetRight, delta);
    }
}
