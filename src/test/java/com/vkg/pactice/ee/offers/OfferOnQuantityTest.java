package com.vkg.pactice.ee.offers;

import com.vkg.pactice.ee.Product;
import com.vkg.pactice.ee.cart.ShoppingCartItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OfferOnQuantityTest {

    private Product dove;
    private OfferOnQuantity buy2Get1FreeOffer;

    @Before
    public void setUp() throws Exception {
        dove = new Product("Dove", 30);
        buy2Get1FreeOffer = new OfferOnQuantity(2, 1);
    }

    @Test
    public void shouldBeNoDiscountForLessProduct() throws Exception {
        double discount = getDiscount(buy2Get1FreeOffer, dove, 2);
        Assert.assertEquals(0, discount, 0);
    }

    private double getDiscount(final OfferOnQuantity offer, final Product product, final int count) {
        final ShoppingCartItem item = new ShoppingCartItem(product);
        item.increaseQuantity(count);
        offer.apply(item);
        return item.getDiscount();
    }

    @Test
    public void shouldBeDiscountForMoreProductThanThresold() throws Exception {
        double discount = getDiscount(buy2Get1FreeOffer, dove, 3);
        Assert.assertEquals(30, discount, 0);
    }
    @Test
    public void shouldBeDiscountForEvenMoreProductThanThresold() throws Exception {
        double discount = getDiscount(buy2Get1FreeOffer, dove, 6);
        Assert.assertEquals(60, discount, 0);
    }
}