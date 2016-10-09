package com.vkg.pactice.ee.offers;

import com.vkg.pactice.ee.Product;
import com.vkg.pactice.ee.cart.ShoppingCartItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OfferOnQuantityTest {

    private static final int THRESHOLD = 2;
    private static final int FREE_COUNT = 1;
    private static final double DELTA = 0;
    private Product dove;
    private OfferOnQuantity buy2Get1FreeOffer;

    @Before
    public void setUp() throws Exception {
        dove = new Product("Dove", 30);
        buy2Get1FreeOffer = new OfferOnQuantity(THRESHOLD, FREE_COUNT);
        dove.addOffer(buy2Get1FreeOffer);
    }

    @Test
    public void shouldBeNoDiscountForLessProduct() throws Exception {
        final int totalItems = getItemsLessThanPair();
        Assert.assertEquals(getMessage(totalItems), 0, getDiscount(totalItems), DELTA);
    }

    private int getItemsLessThanPair() {
        return 1 + (int)(Math.random() * (getItemsInPair() - 1));
    }

    @Test
    public void shouldNotBeDiscountForLessThanOfferPairProduct() throws Exception {
        int totalPairs = getRandom();
        final int totalItems = totalPairs * getItemsInPair() - getItemsLessThanPair();
        Assert.assertEquals(getMessage(totalItems), getExpectedDiscount(totalPairs - 1), getDiscount(totalItems), DELTA);
    }

    @Test
    public void shouldBeDiscountOnEveryPairOfProductsForOffer() throws Exception {
        int totalPairs = getRandom();
        final int totalCount = totalPairs * (getItemsInPair());
        Assert.assertEquals(getMessage(totalCount), getExpectedDiscount(totalPairs), getDiscount(totalCount), DELTA);
    }

    private int getRandom() {
        return 1 + (int)(Math.random() * 10);
    }

    private int getItemsInPair() {
        return THRESHOLD + FREE_COUNT;
    }

    private String getMessage(final int totalCount) {
        return "Discount on " + totalCount + " Items with " + buy2Get1FreeOffer;
    }

    private double getExpectedDiscount(final int totalPairs) {
        return dove.getUnitPrice() * totalPairs * FREE_COUNT;
    }

    private double getDiscount(final int count) {
        final ShoppingCartItem item = new ShoppingCartItem(dove);
        item.increaseQuantity(count);
        return item.getDiscount();
    }
}