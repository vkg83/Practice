package com.vkg.pactice.ee.cart;

import com.vkg.pactice.ee.Product;
import com.vkg.pactice.ee.offers.OfferOnQuantity;
import com.vkg.pactice.ee.offers.PercentageOffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {

    private ShoppingCart cart;
    private Product axe;
    private Product dove;

    @Before
    public void setUp() throws Exception {
        cart = getShoppingCart();
        axe = new Product("Axe Deos", 100);
        dove = new Product("Dove Soap", 30);
    }

    @Test
    public void shouldInstantiateShoppingCartWithZeroItems() {
        Assert.assertEquals(0, cart.getItemCount());
    }

    @Test
    public void emptyShoppingCartWithZeroItemsShouldHaveZeroPrice() {
        Assert.assertEquals(0, cart.getTotalPrice(), 0);
    }

    private ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }

    @Test
    public void shouldAddNewProductToShoppingCart() throws Exception {
        cart.add(dove);
        Assert.assertEquals(1, cart.getItemCount());
    }
    @Test
    public void shouldAddDovesToShoppingCartGivesCorrectTotalPrice() throws Exception {
        cart.add(dove, 5);
        Assert.assertEquals(5, cart.getItemCount());
        Assert.assertEquals(150, cart.getTotalPrice(), 0);
    }

    @Test
    public void shouldCalculateTotalPriceOfMultipleProductOfSameTypeWithOffer() throws Exception {
        dove.addOffer(new OfferOnQuantity(2, 1));
        cart.add(dove, 3);
        Assert.assertEquals(3, cart.getItemCount());
        Assert.assertEquals(60, cart.getTotalPrice(), 0);
    }

    @Test
    public void shouldCalculateTotalPriceOf5ProductOfSameTypeWithOffer() throws Exception {
        dove.addOffer(new OfferOnQuantity(2, 1));
        cart.add(dove, 5);
        Assert.assertEquals(5, cart.getItemCount());
        Assert.assertEquals(120, cart.getTotalPrice(), 0);
    }

    @Test
    public void shouldHaveTotalPrice260For3DoveSoapWithOfferAnd2AxeSoapWithoutOffer() throws Exception {
        dove.addOffer(new OfferOnQuantity(2, 1));
        cart.add(dove, 3);
        cart.add(axe, 2);
        Assert.assertEquals(5, cart.getItemCount());
        Assert.assertEquals(260, cart.getTotalPrice(), 0);
    }

    @Test
    public void shouldGiveDiscountOnShoppingCartHavingTotalPurchase500OrMore() throws Exception {
        cart.addOffer(new PercentageOffer(500, .2));
        cart.add(dove, 5);
        cart.add(axe, 4);
        Assert.assertEquals(9, cart.getItemCount());
        Assert.assertEquals(110, cart.getDiscount(), 0);
        Assert.assertEquals(440, cart.getTotalPrice(), 0);

    }

    @Test
    public void shouldBuyOneAndGet50Percent() throws Exception {


    }
}
