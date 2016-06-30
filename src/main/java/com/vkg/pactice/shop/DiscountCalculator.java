package com.vkg.pactice.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {
    private List<Slab> slabs = new ArrayList<Slab>();

    public DiscountCalculator() {
        addSlab(20000, .3);
        addSlab(10000, .2);
        addSlab(5000, .1);
    }

    private void addSlab(final Slab slab) {
        this.slabs.add(slab);
    }


    private void addSlab(final double minRetail, final double percentage) {
        addSlab(new Slab(minRetail, percentage));
    }

    BigDecimal getDiscount(final BigDecimal purchaseAmount) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal amount = purchaseAmount;
        for (Slab slab : slabs) {
            if (amount.compareTo(slab.getMinRetail()) > 0) {
                discount = discount.add(slab.getDiscount(amount));
                amount = slab.getMinRetail();
            }
        }
        return discount;
    }

}