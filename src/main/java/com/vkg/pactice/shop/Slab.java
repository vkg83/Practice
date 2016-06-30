package com.vkg.pactice.shop;

import java.math.BigDecimal;

public class Slab {
    private BigDecimal minRetail;
    private BigDecimal percentage;
    public Slab(final double minRetail, final double percentage) {
        this(BigDecimal.valueOf(minRetail), BigDecimal.valueOf(percentage));
    }
    public Slab(final BigDecimal minRetail, final BigDecimal percentage) {
        this.minRetail = minRetail;
        this.percentage = percentage;
    }

    public BigDecimal getMinRetail() {
        return minRetail;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    BigDecimal getDiscount(final BigDecimal amount) {
        return amount.subtract(getMinRetail()).multiply(getPercentage());
    }
}
