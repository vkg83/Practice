package com.vkg.pactice.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SampleTree<V extends Comparable> {
    private class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private V data;
    }

    private class Pair {
        private TreeNode node;
        private TreeNode parent;

        public Pair(final TreeNode node, final TreeNode parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private TreeNode root;

    public void insert(V data) {
        if(root == null) {
            root = createNode(data);
            return;
        }
        Pair p = find(root, null, data);

        if(p.node != null) {
            throw new IllegalArgumentException("Value already present");
        }

        TreeNode newNode = createNode(data);

        if(p.parent.data.compareTo(data) > 0) {
            p.parent.left = newNode;
        } else {
            p.parent.right = newNode;
        }
    }

    public void delete(V data) {
        Pair p = find(root, null, data);

        if(p.node != null) {
            TreeNode temp;
            if(p.node.left == null) {
                temp = p.node.right;
            } else if(p.node.right == null) {
                temp = p.node.left;
            } else {
                temp = getSuccessor(p.node);
                delete(temp.data);
                temp.left = p.node.left;
                temp.right = p.node.right;
            }
            if(p.parent.data.compareTo(data) > 0) {
                p.parent.left = temp;
            } else {
                p.parent.right = temp;
            }
        }
    }

    private TreeNode createNode(final V data) {
        TreeNode node = new TreeNode();
        node.data = data;
        return node;
    }

    private Pair find(final TreeNode node, TreeNode parent, final V data) {
        if (node == null) {
            return new Pair(node, parent);
        }
        int diff = node.data.compareTo(data);
        if (diff < 0) {
            return find(node.right, node, data);
        } else if (diff > 0) {
            return find(node.left, node, data);
        } else {
            return new Pair(node, parent);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(final TreeNode node) {
        if(node == null) {
            return;
        }

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(final TreeNode node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public void inOrder() {
        inOrder(root);
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode n = node.right;
        while(n.left != null) {
            n = n.left;
        }

        return n;
    }

    private void inOrder(final TreeNode node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    private void levelOrder() {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode node = q.remove();
            System.out.println(node.data);
            if(node.left != null) q.add(node.left);
            if(node.right != null) q.add(node.right);
        }
    }

    public void neighboursOf(V data, int distance) {
        hasData(data, root, distance);
    }

    public Integer hasData(V data, TreeNode node, int distance) {
        if (node == null) {
            return null;
        } else if( node.data.compareTo(data) == 0) {
            printLevel(node, distance);
            return 0;
        } else {
            final Integer left = hasData(data, node.left, distance);
            final Integer right = hasData(data, node.right, distance);

            if (left != null) {
                printLevel(node.right, distance - left - 2);
                return left + 1;
            }
            if (right != null) {
                printLevel(node.left, distance - right - 2);
                return right + 1;
            }

            return null;
        }
    }

    public void printLevel(TreeNode node, int distance) {
        if(node == null) return;
        if (distance == 0) {
            System.out.println(node.data);
            return;
        }

        printLevel(node.left, distance - 1);
        printLevel(node.right, distance - 1);
    }

    public void printVertical() {
        printVertical(root.data);
    }

    public void printVertical(V data) {
        Integer index = getVerticalIndex(root, data, 0);
        if(index != null) {
            printVertical(root, -index);
        }
    }

    private Integer getVerticalIndex(TreeNode node, final V data, int index) {
        if(node == null) {
            return null;
        }
        if (node.data.compareTo(data) == 0) {
            return index;
        }

        Integer lIdx = getVerticalIndex(node.left, data, index + 1);
        return lIdx == null ? getVerticalIndex(node.right, data, index - 1) : lIdx;
    }

    private void printVertical(final TreeNode node, final int verticalIndex) {
        if(node == null) {
            return;
        }
        if(verticalIndex == 0) {
            System.out.println(node.data);
        }

        printVertical(node.left, verticalIndex + 1);
        printVertical(node.right, verticalIndex - 1);
    }

    public void print() {
        print(root, 0, false, "");
    }
    public void print(TreeNode node, int level, boolean right, String preFix) {
        if(node == null) {
            return;
        }

        if(right) {
            System.out.print("--");
        } else {
            System.out.println();
            System.out.print(preFix);
//            for (int i = 0; i < level; i++) {
//                System.out.print('\t');
//            }
        }

        System.out.print(node.data);
        if(level > 0 && node.left != null) {
            preFix += "|   ";
        }

        print(node.right, level+1, true, preFix);
        //System.out.println();
        print(node.left, level, false, preFix);
    }
    public static void main(String[] args) {
        final SampleTree<Integer> integerTree = new SampleTree<Integer>();

        integerTree.insert(62);
        integerTree.insert(50);
        integerTree.insert(75);
        integerTree.insert(60);
        integerTree.insert(65);
        integerTree.insert(90);
        integerTree.insert(80);
        integerTree.insert(85);
        integerTree.insert(78);
        integerTree.insert(76);
        integerTree.insert(77);
        //integerTree.delete(75);

        integerTree.print();

        //integerTree.neighboursOf(90, 2);
    }
}


/*
               ---------62---------
             /                      \
            50--------       -------75-------
                       \   /                  \
                       60 65            ------90
                                      /
                             --------80------
                           /                   \
                  --------78                    85
                /
               76-------
                         \
                        77
*/

