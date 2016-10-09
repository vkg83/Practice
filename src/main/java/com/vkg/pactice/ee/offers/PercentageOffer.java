package com.vkg.pactice.ee.offers;


import com.vkg.pactice.ee.Pricable;

public class PercentageOffer implements Offer<Pricable> {
    private double threshold;
    private double percentage;

    public PercentageOffer(final double threshold, final double percentage) {
        this.threshold = threshold;
        this.percentage = percentage;
    }

    public PercentageOffer(final double percentage) {
        this(0, percentage);
    }

    @Override
    public void apply(final Pricable product) {
        double value = threshold <= product.getPrice() ? percentage * product.getPrice() : 0;
        product.setDiscount(value);
    }
}
