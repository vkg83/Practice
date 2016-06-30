package com.vkg.pactice.trees;

public class KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        Result r = new Result(k);
        return kthNode(root, r);
    }

    private int kthNode(TreeNode node, Result r) {
        if(node != null) {
            int val = kthNode(node.left, r);
            if(r.k == 0) {
                return val;
            }
            r.k--;
            if(r.k == 0) {
                return node.val;
            }
            return kthNode(node.right, r);
        }

        return -1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}

class Result {
    int k;

    Result(int k) {
        this.k = k;
    }
}
