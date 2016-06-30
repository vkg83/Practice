package com.vkg.pactice.trees;

public class BST<I extends Comparable<I>> implements Tree<I> {
    private Node head;
    public void insert(final I item) {
        final Pair pair = findNode(item, head, null);

        if (pair.node != null) {
            throw new IllegalArgumentException("Item already present");
        }

        if (pair.parent != null) {
            new Node(item, pair.parent);
        } else {
            head = new Node(item);
        }
    }

    public void delete(final I item) {
        final Pair pair = findNode(item, head, null);
        if (pair.node != null) {
            Node tmp;
            if(pair.node.getLeft() == null) {
                tmp = pair.node.getRight();
            } else if (pair.node.getRight() == null) {
                tmp = pair.node.getLeft();
            } else {
                tmp = getSuccessor(pair.node);
                delete(tmp.getData());
                tmp.setLeft(pair.node.getLeft());
                tmp.setRight(pair.node.getRight());
            }

            if(pair.parent != null) {
                if(pair.parent.getData().compareTo(item) > 0) {
                    pair.parent.setLeft(tmp);
                } else {
                    pair.parent.setRight(tmp);
                }
            } else {
                head = tmp;
            }
        }
    }

    private Node getSuccessor(final Node node) {
        Node current = node.getRight();

        while(current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public boolean find(final I item) {
        final Pair pair = findNode(item, head, null);
        return pair.node != null;
    }

    private Pair findNode(final I item, final Node node, Node parent) {
        if(node == null || node.getData().compareTo(item) == 0) {
            return new Pair(node, parent);
        } else if (node.getData().compareTo(item) > 0) {
            return findNode(item, node.getLeft(), node);
        } else {
            return findNode(item, node.getRight(), node);
        }
    }

    private class Pair {
        private Node node;
        private Node parent;

        private Pair(final Node node, final Node parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private class Node {
        private I data;
        private Node left;
        private Node right;

        public Node(final I data) {
            this.data = data;
        }

        public Node(final I data, Node parent) {
            if (parent.data.compareTo(data) > 0) {
                parent.left = this;
            } else {
                parent.right = this;
            }
            this.data = data;
        }

        public I getData() {
            return data;
        }

        public void setData(I data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

