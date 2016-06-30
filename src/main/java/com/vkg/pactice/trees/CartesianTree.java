package com.vkg.pactice.trees;

import java.util.ArrayList;

public class CartesianTree {
    public TreeNode buildTree(ArrayList<Integer> a) {

        TreeNode root = null;
        for (int value : a) {
            root = addNode(new TreeNode(value), root);
        }

        return root;
    }

    private TreeNode addNode(final TreeNode newNode, TreeNode node) {
        if(node == null) {
            return newNode;
        } else if (node.val < newNode.val) {
            newNode.left = node;
            return newNode;
        } else {
            node.right = addNode(newNode, node.right);
            return node;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
