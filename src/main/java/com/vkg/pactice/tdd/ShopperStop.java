package com.vkg.pactice.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopperStop {
    Map<CustomerType, List<Pair>> discountMap = new HashMap<>();
    public double calculateBillAmount(double purchaseAmount, final CustomerType type) {
        double billAmount = 0;
        final List<Pair> discountList = discountMap.get(type);
        if(discountList.size() > 2) {
            if(purchaseAmount > discountList.get(2).max) {
                billAmount += discountList.get(2).max * getMultiplier(discountList.get(1).percent);
                purchaseAmount -= discountList.get(2).max;
                billAmount += purchaseAmount * getMultiplier(discountList.get(2).percent);
            }
        }
        if(discountList.size() <= 1) {
            if(purchaseAmount > discountList.get(0).max) {
                billAmount += discountList.get(0).max * getMultiplier(discountList.get(0).percent);
                purchaseAmount -= discountList.get(0).max;
                billAmount += purchaseAmount * getMultiplier(discountList.get(0).percent);
            }
        }

        if(purchaseAmount > discountList.get(0).max){
            billAmount += purchaseAmount * getMultiplier(discountList.get(0).percent);
        }


        return billAmount;
    }

    private double getMultiplier(final double percent) {
        return 1 - percent;
    }

    public void addSlab(final CustomerType type, final double max, double percentage) {
        List<Pair> list = discountMap.get(type);
        if(list == null) {
            list = new ArrayList<>();
            discountMap.put(type, list);
        }
        list.add(new Pair(max, percentage));
    }

    public enum CustomerType { PREMIUM, REGULAR}

    private class Pair {
        private double max;
        private double percent;

        public Pair(final double max, final double percentage) {
            this.max = max;
            this.percent = percentage;
        }
    }
}
