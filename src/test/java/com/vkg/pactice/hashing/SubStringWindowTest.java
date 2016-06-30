package com.vkg.pactice.hashing;

import org.junit.Assert;
import org.junit.Test;

public class SubStringWindowTest {
    @Test
    public void shouldName() throws Exception {
        SubStringWindow ssw = new SubStringWindow();
        final String result = ssw.minWindow("ADOBECODEBANC", "ABC");
        Assert.assertEquals("BANC", result);

    }
}