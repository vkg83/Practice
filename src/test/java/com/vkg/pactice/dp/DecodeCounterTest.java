package com.vkg.pactice.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecodeCounterTest {

    private DecodeCounter decodeCounter;

    @Before
    public void setUp() throws Exception {
        decodeCounter = new DecodeCounter();
    }

    @Test
    public void shouldDecodeEmptyString() throws Exception {
        verify("Blank", "", 0);
    }

    @Test
    public void shouldDecodeSingleDigitString() throws Exception {
        for (int i = 1; i < 10; i++) {
            String input = Integer.toString(i);
            verify(input, input, 1);
        }
    }

    @Test
    public void shouldDecodeDoubleDigitGreaterThan26String() throws Exception {
        for (int i = 27; i < 100; i++) {
            if(i % 10 == 0) continue;
            String input = Integer.toString(i);
            verify(input, input, 1);
        }
    }

    @Test
    public void shouldDecodeDoubleDigitLessThanOrEqualTo26String() throws Exception {
        for (int i = 11; i <= 26; i++) {
            if(i % 10 == 0) continue;
            String input = Integer.toString(i);
            verify(input, input, 2);
        }
    }

    @Test
    public void shouldDecode() throws Exception {
            verify("124", "124", 3); //1 24, 1 2 4, 12 4 :    24, 2 4

    }

    private void verify(final String msg, final String input, final int expected) {
        int count = decodeCounter.numDecodings(input);
        Assert.assertEquals(msg, expected, count);
    }
}