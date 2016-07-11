package com.vkg.pactice.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSumNoAdjecentTest {
    @Test
    public void shouldGetMaxSum() throws Exception {
        MaxSumNoAdjecent maxSum = new MaxSumNoAdjecent();
        final ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)));
        list.add(new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5)));
        int max = maxSum.adjacentBetter(list);
        Assert.assertEquals(8, max);
    }
    @Test
    public void shouldGetMaxSum2() throws Exception {
        MaxSumNoAdjecent maxSum = new MaxSumNoAdjecent();
        final ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<Integer>(Arrays.asList(74, 37, 82, 1)));
        list.add(new ArrayList<Integer>(Arrays.asList(66, 38, 16, 1)));
        int max = maxSum.adjacentBetter(list);
        Assert.assertEquals(156, max);
    }
}

//74, 38, 82, 1
//74, 38, 156,