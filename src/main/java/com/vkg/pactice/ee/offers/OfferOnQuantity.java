package com.vkg.pactice.ee.offers;


import com.vkg.pactice.ee.cart.ShoppingCartItem;

public class OfferOnQuantity implements Offer<ShoppingCartItem> {
    private int baseCount;
    private int freeCount;

    public OfferOnQuantity(final int baseCount, final int freeCount) {
        this.baseCount = baseCount;
        this.freeCount = freeCount;
    }

    @Override
    public void apply(ShoppingCartItem item) {
        int qty = item.getQuantity();
        double unitPrice = item.getUnitPrice();
        double value = (qty / (baseCount + freeCount)) * unitPrice;
        item.setDiscount(value);
    }
}
