package com.vkg.pactice.ee.cart;

import com.vkg.pactice.ee.Offerable;
import com.vkg.pactice.ee.Product;

public class ShoppingCartItem implements Offerable {
    private Product product;
    private int quantity;
    private double discount;

    public ShoppingCartItem(final Product product) {
        this.product = product;
        this.quantity = 0;
    }

    public double getPrice() {
        return product.getUnitPrice() * quantity;
    }

    public double getTotalPrice() {
        return getPrice() - discount;
    }

    @Override
    public void setDiscount(final double discount) {
        this.discount = discount;
    }

    @Override
    public double getDiscount() {
        return discount;
    }


    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(final int count) {
        quantity += count;
        product.getOffer().apply(this);
    }

    public double getUnitPrice() {
        return product.getUnitPrice();
    }
}
