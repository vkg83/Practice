package com.vkg.pactice.ee.offers;

import com.vkg.pactice.ee.Pricable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PercentageOfferTest {

    private PercentageOffer percentageOffer;
    private PercentageOffer percentageOfferWithThreshold;

    @Before
    public void setUp() throws Exception {
        percentageOffer = new PercentageOffer(.2);
        percentageOfferWithThreshold = new PercentageOffer(500, .2);
    }

    @Test
    public void shouldGiveDiscountInPercentage() throws Exception {
        Pricable item = new MockPricable(200);
        percentageOffer.apply(item);
        Assert.assertEquals(40, item.getDiscount(), 0);
    }
    @Test
    public void shouldNotGiveDiscountForLessPrice() throws Exception {
        Pricable item = new MockPricable(499);
        percentageOfferWithThreshold.apply(item);
        Assert.assertEquals(0, item.getDiscount(), 0);
    }
    @Test
    public void shouldGiveDiscountForThresholdPrice() throws Exception {
        Pricable item = new MockPricable(500);
        percentageOfferWithThreshold.apply(item);
        Assert.assertEquals(100, item.getDiscount(), 0);
    }
}

class MockPricable implements Pricable {
    private double discount;
    private double price;

    public MockPricable(final double price) {
        this.price = price;
    }

    @Override
    public void setDiscount(final double discount) {
        this.discount = discount;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public double getPrice() {
        return price;
    }
}