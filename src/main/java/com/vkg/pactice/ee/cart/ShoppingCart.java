package com.vkg.pactice.ee.cart;

import com.vkg.pactice.ee.Pricable;
import com.vkg.pactice.ee.Product;
import com.vkg.pactice.ee.offers.Offer;
import com.vkg.pactice.ee.offers.OfferSupport;
import com.vkg.pactice.ee.offers.PercentageOffer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;

public class ShoppingCart implements Pricable, OfferSupport<PercentageOffer> {
    private Map<Product, ShoppingCartItem> productMap = new HashMap<>();
    private Offer offer = Offer.NO_OFFER;
    private double discount;

    public int getItemCount() {
        int itemCount = 0;
        for (ShoppingCartItem item : productMap.values()) {
            itemCount += item.getQuantity();
        }
        return itemCount;
    }

    public double getTotalPrice() {
        return calculatePrice(ShoppingCartItem::getTotalPrice) - discount;
    }

    public void add(final Product product) {
        add(product, 1);
    }

    public void add(final Product product, final int count) {
        ShoppingCartItem item = productMap.get(product);
        if(item == null) {
            item = new ShoppingCartItem(product);
            productMap.put(product, item);
        }

        item.increaseQuantity(count);
        offer.apply(this);
    }

    public double getDiscount() {
            return discount;
    }

    @Override
    public double getPrice() {
        return calculatePrice(ShoppingCartItem::getPrice);
    }

    private double calculatePrice(final ToDoubleFunction<ShoppingCartItem> priceFn) {
        return productMap.values().stream().mapToDouble(priceFn).sum();
    }

    @Override
    public void setDiscount(final double value) {
        this.discount = value;
    }

    @Override
    public void addOffer(final PercentageOffer offer) {
        this.offer = offer;
    }
}
