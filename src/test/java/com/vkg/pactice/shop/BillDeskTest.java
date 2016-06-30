package com.vkg.pactice.shop;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

public class BillDeskTest {
    @Test
    public void shouldBillCustomerSameAsPurchase() {
        BillDesk billDesk = new BillDesk();
        billDesk.setCalculator(CustomerType.REGULAR, new DiscountCalculator());
        double purchaseAmount = new Random().nextDouble() * 5000;
        double billAmount = billDesk.getBillAmount(CustomerType.REGULAR, BigDecimal.valueOf(purchaseAmount)).doubleValue();
        Assert.assertEquals(purchaseAmount, billAmount, 0.00001);
    }

    @Test
    public void shouldBillCustomer10PercentageLessFor5000AndMore() {
        BillDesk billDesk = new BillDesk();
        billDesk.setCalculator(CustomerType.REGULAR, new DiscountCalculator());
        double accessAmountOver5000 = new Random().nextDouble() * 5000;
        double discount = accessAmountOver5000 * .1;
        double purchaseAmount = 5000 + accessAmountOver5000;
        double billAmount = billDesk.getBillAmount(CustomerType.REGULAR, BigDecimal.valueOf(purchaseAmount)).doubleValue();
        Assert.assertEquals(purchaseAmount - discount, billAmount, 0.00001);
    }
    @Test
    public void shouldBillCustomer20PercentageLessFor10000AndMore() {
        BillDesk billDesk = new BillDesk();
        billDesk.setCalculator(CustomerType.REGULAR, new DiscountCalculator());
        double accessAmountOver10000 = new Random().nextDouble() * 5000;
        double discount = 500 + accessAmountOver10000 * .2;
        double purchaseAmount = 10000 + accessAmountOver10000;
        double billAmount = billDesk.getBillAmount(CustomerType.REGULAR, BigDecimal.valueOf(purchaseAmount)).doubleValue();
        Assert.assertEquals(purchaseAmount - discount, billAmount, 0.00001);
    }
    @Test
    public void shouldBillCustomer30PercentageLessFor20000AndMore() {
        BillDesk billDesk = new BillDesk();
        billDesk.setCalculator(CustomerType.REGULAR, new DiscountCalculator());
        double accessAmountOver20000 = new Random().nextDouble() * 5000;
        double discount = 500 + 2000 + accessAmountOver20000 * .3;
        double purchaseAmount = 20000 + accessAmountOver20000;
        double billAmount = billDesk.getBillAmount(CustomerType.REGULAR, BigDecimal.valueOf(purchaseAmount)).doubleValue();
        Assert.assertEquals(purchaseAmount - discount, billAmount, 0.00001);
    }
}
