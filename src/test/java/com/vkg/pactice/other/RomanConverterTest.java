package com.vkg.pactice.other;

import com.vkg.pactice.string.RomanConverter;
import org.junit.Assert;
import org.junit.Test;

public class RomanConverterTest {
    @Test
    public void shouldConvertToRoman() throws Exception {
        RomanConverter i2r = new RomanConverter();
        Assert.assertEquals("V", i2r.intToRoman(5));
        Assert.assertEquals("XIV", i2r.intToRoman(14));
        Assert.assertEquals("CCXLIV", i2r.intToRoman(244));
    }
    @Test
    public void shouldConvertToDecimal() throws Exception {
        RomanConverter i2r = new RomanConverter();
        Assert.assertEquals(5, i2r.romanToInt("V"));
        Assert.assertEquals(14, i2r.romanToInt("XIV"));
        Assert.assertEquals(244, i2r.romanToInt("CCXLIV"));
        Assert.assertEquals(4, i2r.romanToInt("IV"));
    }
}