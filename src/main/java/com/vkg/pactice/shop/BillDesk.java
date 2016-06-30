package com.vkg.pactice.shop;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

public class BillDesk {
    private Map<CustomerType, DiscountCalculator> calculatorMap;
    private final MathContext CONTEXT = new MathContext(0, RoundingMode.HALF_EVEN);

    public BillDesk() {
    }

    public void setCalculator(CustomerType type, DiscountCalculator calculator) {
        calculatorMap.put(type, calculator);
    }

    public BigDecimal getBillAmount(final CustomerType type, final BigDecimal purchaseAmount) {
        BigDecimal discount = calculatorMap.get(type).getDiscount(purchaseAmount);
        return purchaseAmount.subtract(discount, CONTEXT);
    }
}
