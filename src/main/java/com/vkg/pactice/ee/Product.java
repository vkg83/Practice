package com.vkg.pactice.ee;


import com.vkg.pactice.ee.offers.Offer;
import com.vkg.pactice.ee.offers.OfferSupport;

public class Product implements OfferSupport<Offer> {
    private String name;
    private double unitPrice;
    private Offer offer;

    public Product(final String name, final double price) {
        this.name = name;
        this.unitPrice = price;
        this.offer = Offer.NO_OFFER;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public void addOffer(final Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }
}
