package com.vkg.pactice.trees;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KthSmallestTest {

    private KthSmallest kthSmallest;

    @Before
    public void setUp() throws Exception {
        kthSmallest = new KthSmallest();
    }

    @Test
    public void shouldNotWorkForEmptyTree() throws Exception {
        KthSmallest kthSmallest = new KthSmallest();
        int result = kthSmallest.kthSmallest(null, 2);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldNotWorkForKMoreThanTreeSize() throws Exception {
        KthSmallest kthSmallest = new KthSmallest();
        KthSmallest.TreeNode node = new KthSmallest.TreeNode(5);
        node.right = new KthSmallest.TreeNode(8);
        int result = kthSmallest.kthSmallest(node, 3);
        Assert.assertEquals(-1, result);
    }
    @Test
    public void should1() throws Exception {
        KthSmallest kthSmallest = new KthSmallest();
        KthSmallest.TreeNode node = new KthSmallest.TreeNode(5);
        int result = kthSmallest.kthSmallest(node, 1);
        Assert.assertEquals(5, result);
    }
    @Test
    public void should2() throws Exception {
        KthSmallest.TreeNode node = new KthSmallest.TreeNode(5);
        node.left = new KthSmallest.TreeNode(3);
        node.right = new KthSmallest.TreeNode(8);
        node.left.right = new KthSmallest.TreeNode(4);
        node.right.right = new KthSmallest.TreeNode(11);
        int result = kthSmallest.kthSmallest(node, 1);
        Assert.assertEquals(5, result);
    }
}