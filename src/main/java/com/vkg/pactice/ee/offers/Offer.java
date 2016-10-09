package com.vkg.pactice.ee.offers;


import com.vkg.pactice.ee.Offerable;

public interface Offer<T extends Offerable> {
    Offer NO_OFFER = offerable -> {};
    void apply(T offerable);
}
