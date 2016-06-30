package com.vkg.pactice.hashing;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PairSumTest {
    @Test
    public void shouldName() throws Exception {
        PairSum ps = new PairSum();
        final ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(9, 5, 4, 9, 3, 6, 8, 7, 1, 2, 8, 7, 2, 9, 7, 1, 3, 9, 7, 8, 1, 0, 5, 5));
        final ArrayList<Integer> actual = ps.equal(list);
        final ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(0, 1, 3, 22));

        Assert.assertEquals(expected, actual);
    }
}