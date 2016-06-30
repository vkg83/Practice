package com.vkg.pactice.trees;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BSTTest {
    private Tree<Integer> unit;
    @Before
    public void setUp() throws Exception {
        unit = new BST<Integer>();
    }

    @Test
    public void shouldInsertData() throws Exception {
        unit.insert(62);
        unit.insert(75);
        unit.insert(60);
        Assert.assertTrue(unit.find(62));
        Assert.assertTrue(unit.find(75));
        Assert.assertTrue(unit.find(60));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertDuplicateData() throws Exception {
        unit.insert(60);
        unit.insert(60);
    }

    @Test
    public void shouldAbleToDeleteData() throws Exception {
        unit.insert(62);
        unit.insert(75);
        unit.insert(60);
        unit.insert(80);
        unit.insert(90);
        unit.insert(45);
        unit.delete(80);
        Assert.assertFalse(unit.find(80));
    }
}