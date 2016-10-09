package com.vkg.pactice.ee.cart;

import com.vkg.pactice.ee.Product;
import com.vkg.pactice.ee.offers.OfferOnQuantity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartItemTest {

    private Product dove;

    @Before
    public void setUp() throws Exception {
        dove = new Product("Dove", 30);
    }

    @Test
    public void shouldHaveZeroQuantityInitially() throws Exception {
        ShoppingCartItem item = new ShoppingCartItem(dove);
        Assert.assertEquals(0, item.getQuantity());

    }
    @Test
    public void shouldIncreaseQuantity() throws Exception {
        ShoppingCartItem item = new ShoppingCartItem(dove);
        item.increaseQuantity(5);
        Assert.assertEquals(5, item.getQuantity());
    }

    @Test
    public void shouldCalculateTotalPriceOf5ProductOfSameTypeWithOffer() throws Exception {
        dove.addOffer(new OfferOnQuantity(2, 1));
        ShoppingCartItem item = new ShoppingCartItem(dove);
        item.increaseQuantity(5);
        Assert.assertEquals(120, item.getTotalPrice(), 0);
    }

    @Test
    public void shouldCalculateTotalPriceOf3ProductOfSameTypeWithOffer() throws Exception {
        dove.addOffer(new OfferOnQuantity(2, 1));
        ShoppingCartItem item = new ShoppingCartItem(dove);
        item.increaseQuantity(3);
        Assert.assertEquals(60, item.getTotalPrice(), 0);
    }

    @Test
    public void shouldCalculateTotalPriceOf3AxeOfSameTypeWithoutOffer() throws Exception {
        final Product product = new Product("Axe Deos", 100);
        ShoppingCartItem item = new ShoppingCartItem(product);
        item.increaseQuantity(3);
        Assert.assertEquals(300, item.getTotalPrice(), 0);
    }

}