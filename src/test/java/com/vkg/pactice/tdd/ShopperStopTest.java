package com.vkg.pactice.tdd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShopperStopTest {
    private static final double DELTA = 0;
    private ShopperStop shopperStop;

    @Before
    public void setUp() throws Exception {
        shopperStop = new ShopperStop();
        shopperStop.addSlab(ShopperStop.CustomerType.REGULAR, 5000, 0);
        //shopperStop.addSlab(ShopperStop.CustomerType.REGULAR, 10000, 10);
        //shopperStop.addSlab(ShopperStop.CustomerType.REGULAR, Double.MAX_VALUE, 20);

        shopperStop.addSlab(ShopperStop.CustomerType.PREMIUM, 4000, .10);
        shopperStop.addSlab(ShopperStop.CustomerType.PREMIUM, 8000, .15);
        shopperStop.addSlab(ShopperStop.CustomerType.PREMIUM, 12000, 20);
        //shopperStop.addSlab(ShopperStop.CustomerType.PREMIUM, Double.MAX_VALUE, 30);
    }

    @Test
    public void shouldGetNoBillForNoPurchase() {
        double billAmount = shopperStop.calculateBillAmount(0, ShopperStop.CustomerType.PREMIUM);
        Assert.assertEquals(0, billAmount, DELTA);
        billAmount = shopperStop.calculateBillAmount(0, ShopperStop.CustomerType.REGULAR);
        Assert.assertEquals(0, billAmount, DELTA);
    }

    @Test
    public void shouldGetNoDiscountForRegularFor4000() throws Exception {
        double billAmount = shopperStop.calculateBillAmount(4000, ShopperStop.CustomerType.REGULAR);
        Assert.assertEquals(4000, billAmount, DELTA);
    }

    @Test
    public void shouldGetDiscountForPremiumFor4000() throws Exception {
        double billAmount = shopperStop.calculateBillAmount(4000, ShopperStop.CustomerType.PREMIUM);
        Assert.assertEquals(3600, billAmount, DELTA);
    }

    @Test
    public void shouldGetDiscountForPremiumFor5000() throws Exception {
        double billAmount = shopperStop.calculateBillAmount(5000, ShopperStop.CustomerType.PREMIUM);
        Assert.assertEquals(4450, billAmount, DELTA);
    }

    @Test
    public void shouldGetDiscountForPremiumFor7000() throws Exception {
        double billAmount = shopperStop.calculateBillAmount(9000, ShopperStop.CustomerType.PREMIUM);
        Assert.assertEquals(7800, billAmount, DELTA);
    }
}