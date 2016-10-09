package com.vkg.pactice.ee;

import com.vkg.pactice.ee.offers.OfferOnQuantity;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void shouldCreateProductWithCorrectNameAndUnitPrice() throws Exception {
        final Product product = new Product("Dove Soap", 30);
        Assert.assertEquals("Dove Soap", product.getName());
        Assert.assertEquals(30, product.getUnitPrice(), 0);
    }

    @Test
    public void shouldCreateProductWithOfferBuyXGetY() throws Exception {
        final Product product = new Product("Dove Soap", 30);
        final OfferOnQuantity offer = new OfferOnQuantity(2, 1);
        product.addOffer(offer);
        Assert.assertEquals(offer, product.getOffer());
    }
}