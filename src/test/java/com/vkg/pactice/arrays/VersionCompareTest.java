package com.vkg.pactice.arrays;

import org.junit.Assert;
import org.junit.Test;

public class VersionCompareTest {
    @Test
    public void shouldName() throws Exception {
        VersionCompare compare = new VersionCompare();
        final int i = compare.compareVersion("01", "1");
        Assert.assertEquals(0, i);

    }
}