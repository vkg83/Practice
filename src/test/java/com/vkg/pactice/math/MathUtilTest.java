package com.vkg.pactice.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathUtilTest {
    private MathUtil math;

    @Before
    public void setUp() throws Exception {
        math = new MathUtil();
    }

    @Test
    public void shouldGetSqrt() throws Exception {
        Assert.assertEquals(5, math.sqrt(25));
        Assert.assertEquals(3, math.sqrt(11));
        Assert.assertEquals(7, math.sqrt(50));
        Assert.assertEquals(9, math.sqrt(99));

    }

    @Test
    public void shouldGetModuloPow() throws Exception {
        Assert.assertEquals(4, math.pow(2, 2, 7));
        Assert.assertEquals(20805472, math.pow(71045970, 41535484, 64735492));
    }

    @Test
    public void shouldName1() throws Exception {
        for (int i = 0; i < 1000; i++) {
            shouldName();
        }
    }

    @Test
    public void shouldName() throws Exception {
        int top = 20;
        int x = top/4 + (int)(Math.random() * top/4);
        int y = top/4 + (int)(Math.random() * top/4);
        int z = top/2 + (int)(Math.random() * top/2);
        System.out.print(x + " ^ " + y + " % " + z);
        final int pow = math.pow(x, y, z);
        System.out.println(" = " + pow);
        System.out.println((long)Math.pow(x, y));
        Assert.assertEquals((long)Math.pow(x, y) % z, pow);

    }

    @Test
    public void shouldCheckIfPower() throws Exception {
        Assert.assertTrue(math.isPower(4));
        Assert.assertFalse(math.isPower(28));

    }

    @Test
    public void shouldTestInfinity() {
        double s = 0;
        Double d = 1.0/0.0;
        System.out.println(d == Double.POSITIVE_INFINITY);
    }
}

/*
z = y
t = 0
while(t<x)
while (z>x) {
    z = z - x;
    t++;
}
z = x - z;
t++;
}
2x - y = x - (y - x) - (y - x)

(y - x) ^ 2 = x ^ 2
 */


