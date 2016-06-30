package com.vkg.pactice.hashing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FractionTest {

    private Fraction fraction;

    @Before
    public void setUp() throws Exception {
        fraction = new Fraction();
    }

    @Test
    public void shouldName() throws Exception {
        verify(127, 110, "1.1(54)");
    }

    @Test
    public void shouldName1() throws Exception {
        verify(-2147483648, -1, "2147483648");
    }
    @Test
    public void shouldName2() throws Exception {
        verify(-1, 2, "-0.5");
    }

    @Test
    public void shouldName3() throws Exception {
        verify(0, -1, "0");
    }
    private void verify(final int numerator, final int denominator, final String expected) {
        final String result = fraction.fractionToDecimal(numerator, denominator);
        Assert.assertEquals(expected, result);
    }
}