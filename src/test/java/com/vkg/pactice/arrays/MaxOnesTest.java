package com.vkg.pactice.arrays;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxOnesTest {
    @Test
    public void shouldGiveMaxOnes() throws Exception {
        MaxOnes maxOnes = new MaxOnes();
        final ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1));
        Assert.assertEquals(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), maxOnes.maxone(arr, 0));

        final ArrayList<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(1, 1, 0));
        Assert.assertEquals(new ArrayList<Integer>(Arrays.asList(0, 1, 2)), maxOnes.maxone(arr2, 2));
    }
}