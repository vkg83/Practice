package com.vkg.tools.client;

import com.vkg.tools.excel.Key;
import com.vkg.tools.excel.Sheet;
import com.vkg.tools.excel.Value;

import java.util.Date;

@Sheet(oldName="Oracle Old", newName="Oracle New")
public class Oracle {
    @Key private String productCode;
    @Key private Long customerNumberNexus;
    @Value private String businessUnit;
    @Value private Date period;
    @Value private String thirdPartyVendor;
    @Value private Double vendorBillAmount;
    @Value private Double paymentPerKeyword;
    @Value private Long keywordCount;
    @Value private Double billAmount;
    @Value private Double budgetedAmount;
    @Value private Long billingGroupInvoiceId;
    @Value private Double billingGroupNumber;
    @Value private Double invoiceNumberNexus;
    @Value private String customerNumberOracle;
    @Value private String customerName;
    @Value private Double percentOfAdSpend;
    @Value private Long searchEngineCustomerId;
    @Value private String webId;
    @Value private Long orderNumberOracle;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Oracle{");
        sb.append("businessUnit='").append(businessUnit).append('\'');
        sb.append(", period=").append(period);
        sb.append(", thirdPartyVendor='").append(thirdPartyVendor).append('\'');
        sb.append(", productCode='").append(productCode).append('\'');
        sb.append(", vendorBillAmount=").append(vendorBillAmount);
        sb.append(", paymentPerKeyword=").append(paymentPerKeyword);
        sb.append(", keywordCount=").append(keywordCount);
        sb.append(", billAmount=").append(billAmount);
        sb.append(", budgetedAmount=").append(budgetedAmount);
        sb.append(", billingGroupInvoiceId=").append(billingGroupInvoiceId);
        sb.append(", billingGroupNumber=").append(billingGroupNumber);
        sb.append(", invoiceNumberNexus=").append(invoiceNumberNexus);
        sb.append(", customerNumberNexus=").append(customerNumberNexus);
        sb.append(", customerNumberOracle='").append(customerNumberOracle).append('\'');
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", orderNumberOracle=").append(orderNumberOracle);
        sb.append(", percentOfAdSpend=").append(percentOfAdSpend);
        sb.append(", searchEngineCustomerId=").append(searchEngineCustomerId);
        sb.append(", webId='").append(webId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
