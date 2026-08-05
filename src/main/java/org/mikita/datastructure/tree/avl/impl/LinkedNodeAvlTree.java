package org.mikita.datastructure.tree.avl.impl;

import org.mikita.datastructure.tree.avl.AvlTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LinkedNodeAvlTree<T> implements AvlTree<T> {

    private class Node {
        private T value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private int height;

        private Node(T value) {
            this.value = value;
            this.height = 1;
        }

        private Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.height = 1;
        }

        private void insert(T value) {
            int comparison = comparator.compare(this.value, value);

            if(comparison < 0) {
                if(rightChild == null) {
                    rightChild = new Node(value, this);
                } else {
                    rightChild.insert(value);
                }
                height = 1 + Math.max(getLeftHeight(), getRightHeight());
            } else if(comparison > 0) {
                if(leftChild == null) {
                    leftChild = new Node(value, this);
                } else {
                    leftChild.insert(value);
                }
                height = 1 + Math.max(getLeftHeight(), getRightHeight());
            } else {
                throw new RuntimeException("Value %s is already present.".formatted(value));
            }

            if (calculateBalanceFactor() >= 2) {
                if (leftChild.calculateBalanceFactor() >= 0) {
                    leftChild.ll();
                } else {
                    leftChild.lr();
                }
            } else if (calculateBalanceFactor() <= -2) {
                if(rightChild.calculateBalanceFactor() <= 0) {
                    rightChild.rr();
                } else {
                    rightChild.rl();
                }
            }
        }

        private int calculateBalanceFactor() {
            return getLeftHeight() - getRightHeight();
        }

        private int getLeftHeight() {
            if(leftChild == null) {
                return 0;
            }
            return leftChild.height;
        }

        private int getRightHeight() {
            if(rightChild == null) {
                return 0;
            }
            return rightChild.height;
        }

        /**
         * left-left rotation

         * ## 1st case (added node with value 1), the following rotation is expected
         *       3(3)                   2(2)
         *      /                      /   \
         *    2(2)        ->       1(1)   3(1)
         *   /
         * 1(1)

         * ## 2nd case (added node with value 1), the following rotation is expected (unreal case)
         *        4(3)                    2(3)
         *       /    \                  /   \
         *     2(2)   5(1)    ->       0(1)   4(2)
         *    /    \                         /   \
         *  0(1)  3(1)                     3(1)  5(1)

         */
        private Node ll() {
            Node prevParent = this.parent;
            Node prevRightChild = this.rightChild;

            this.parent = prevParent.parent;
            this.rightChild = prevParent;
            prevParent.leftChild = prevRightChild;
            prevParent.parent = this;

            if(prevRightChild != null) {
                prevRightChild.parent = prevParent;
            }

            if(this.parent != null) {
                if(this.parent.leftChild == prevParent) {
                    this.parent.leftChild = this;
                } else {
                    this.parent.rightChild = this;
                }
            } else {
                LinkedNodeAvlTree.this.root = this;
            }

            prevParent.height = 1 + Math.max(prevParent.getLeftHeight(), prevParent.getRightHeight());
            this.height = 1 + Math.max(this.getLeftHeight(), this.getRightHeight());

            return this;
        }

        /**
         * left-right rotation

         * ## 1st case (added node with value 2), the following rotation is expected
         *       3(3)                 3(3)                2(2)
         *      /                    /                   /   \
         *    1(2)        ->       2(2)       ->      1(1)   3(1)
         *       \                /
         *        2(1)          1(1)

         */
        private Node lr() {
            Node prevParent = this.parent;
            Node prevRightChild = this.rightChild;
            Node prevRightLeftChild = prevRightChild.leftChild;

            this.parent = prevRightChild;
            prevParent.leftChild = prevRightChild;
            prevRightChild.parent = prevParent;
            prevRightChild.leftChild = this;
            this.rightChild = prevRightLeftChild;

            if(prevRightLeftChild != null) {
                prevRightLeftChild.parent = this;
            }

            this.height = 1 + Math.max(this.getLeftHeight(), this.getRightHeight());
            prevRightChild.height = 1 + Math.max(prevRightChild.getLeftHeight(), prevRightChild.getRightHeight());
            prevParent.height = 1 + Math.max(prevParent.getLeftHeight(), prevParent.getRightHeight());

            return parent.ll();
        }


        /**
         * right-right rotation

         * ## 1st case (added node with value 1), the following rotation is expected
         *  1(3)                        2(2)
         *     \                       /   \
         *     2(2)        ->       1(1)   3(1)
         *        \
         *        3(1)

         * ## 2nd case (added node with value 7), the following rotation is expected (unreal case)
         *        2(3)                    5(3)
         *       /    \                  /   \
         *     1(1)   5(2)    ->       2(2)  7(1)
         *           /   \            /   \
         *        3(1)   7(1)       1(1)   3(1)

         */
        private Node rr() {
            Node prevParent = this.parent;
            Node prevLeftChild = this.leftChild;

            this.parent = prevParent.parent;
            this.leftChild = prevParent;

            prevParent.rightChild = prevLeftChild;
            prevParent.parent = this;

            if(prevLeftChild != null) {
                prevLeftChild.parent = prevParent;
            }

            if(this.parent != null) {
                if(this.parent.leftChild == prevParent) {
                    this.parent.leftChild = this;
                } else {
                    this.parent.rightChild = this;
                }
            } else {
                LinkedNodeAvlTree.this.root = this;
            }

            prevParent.height = 1 + Math.max(prevParent.getLeftHeight(), prevParent.getRightHeight());
            this.height = 1 + Math.max(this.getLeftHeight(), this.getRightHeight());

            return this;
        }

        /**
         * right-left rotation

         * ## 1st case (added node with value 1), the following rotation is expected
         *    1(3)                1(3)                        2(2)
         *       \                   \                       /   \
         *       3(2)     ->         2(2)        ->       1(1)   3(1)
         *      /                       \
         *    2(1)                      3(1)

         */
        private Node rl() {
            Node prevParent = this.parent;
            Node prevLeftChild = this.leftChild;
            Node prevLeftRightChild = prevLeftChild.rightChild;

            this.parent = prevLeftChild;
            prevParent.rightChild = prevLeftChild;

            prevLeftChild.parent = prevParent;
            prevLeftChild.rightChild = this;
            this.leftChild = prevLeftRightChild;

            if(prevLeftRightChild != null) {
                prevLeftRightChild.parent = this;
            }

            this.height = 1 + Math.max(this.getLeftHeight(), this.getRightHeight());
            prevLeftChild.height = 1 + Math.max(prevLeftChild.getLeftHeight(), prevLeftChild.getRightHeight());
            prevParent.height = 1 + Math.max(prevParent.getLeftHeight(), prevParent.getRightHeight());

            return parent.rr();
        }

        private Node delete(T value) {
            int comparison = comparator.compare(this.value, value);

            if(comparison < 0) {
                if(rightChild != null) {
                    rightChild = rightChild.delete(value);
                }
            } else if(comparison > 0) {
                if(leftChild != null) {
                    leftChild = leftChild.delete(value);
                }
            } else {
                if(leftChild == null || rightChild == null) {
                    Node replacement = leftChild != null ? leftChild : rightChild;

                    if (replacement != null) {
                        replacement.parent = parent;
                    }

                    return replacement;
                } else {
                    Node tmp = minValueNode(rightChild);

                    this.value = tmp.value;

                    rightChild = rightChild.delete(tmp.value);
                }
            }

            height = 1 + Math.max(getLeftHeight(), getRightHeight());

            if (calculateBalanceFactor() >= 2) {
                if (leftChild.calculateBalanceFactor() >= 0) {
                    return leftChild.ll();
                } else {
                    return leftChild.lr();
                }
            } else if (calculateBalanceFactor() <= -2) {
                if(rightChild.calculateBalanceFactor() <= 0) {
                    return rightChild.rr();
                } else {
                    return rightChild.rl();
                }
            }

            return this;
        }

        private Node minValueNode(Node node) {
            Node current = node;

            while (current.leftChild!= null)
                current = current.leftChild;

            return current;
        }
    }

    private Comparator<T> comparator;
    private Node root;

    public LinkedNodeAvlTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void insert(T value) {
        if(Objects.isNull(value)) {
            throw new RuntimeException("Value cannot be null.");
        }

        if(root == null) {
            root = new Node(value);
            return;
        }

        root.insert(value);
    }

    @Override
    public void delete(T value) {
        if(Objects.isNull(value)) {
            throw new RuntimeException("Value cannot be null.");
        }

        if(root == null) {
            return;
        }

        root = root.delete(value);
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        add(result, root);
        return result;
    }

    private void add(List<T> list, Node current) {
        if(current == null) {
            return;
        }
        list.add(current.value);
        add(list, current.leftChild);
        add(list, current.rightChild);
    }
}
