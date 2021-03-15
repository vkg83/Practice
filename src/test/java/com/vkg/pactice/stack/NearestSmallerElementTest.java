package com.vkg.pactice.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NearestSmallerElementTest {

    private NearestSmallerElement nearestSmallerElement;

    @Before
    public void setUp() throws Exception {
        nearestSmallerElement = new NearestSmallerElement();
    }

    @Test
    public void shouldFindNSE() throws Exception {
        verify(Arrays.asList(4, 5, 2, 10), Arrays.asList(-1, 4, -1, 2));
    }

    @Test
    public void shouldFindNSE2() throws Exception {
        verify(Arrays.asList(3, 2, 1), Arrays.asList(-1, -1, -1));
    }

    private void verify(final List<Integer> input, final List<Integer> output) {
        final ArrayList<Integer> result = nearestSmallerElement.prevSmaller(input);
        Assert.assertEquals(output, result);
    }

    @Test
    public void shouldName() throws Exception {
        int k = 2500;
        int nf = 1;
        BigInteger nf2 = BigInteger.ONE;
        System.out.println(Integer.MAX_VALUE + ", " + (2L * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12));
        for (int i = 0; i < 25; i++) {
            nf *= (i + 1);
            nf2 = nf2.multiply(BigInteger.valueOf(i + 1));
            System.out.println(i + " : " + nf + " : " + nf2);
        }


    }
}