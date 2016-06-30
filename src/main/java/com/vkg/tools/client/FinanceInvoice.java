package com.vkg.tools.client;

import com.vkg.tools.excel.Key;
import com.vkg.tools.excel.Sheet;
import com.vkg.tools.excel.Value;

import java.util.Date;

@Sheet(oldName = "Finance Invoice Old", newName = "Finance Invoice New")
public class FinanceInvoice {
    @Key private int customerNumber;
    @Key private String productCode;
    @Value private String customerNumberOracle;
    @Value private String webId;
    @Value private Date period;
    @Value private double billAmount;
    @Value private String customerName;
    @Value private double packageRetailMax;
    private long id;
    @Value private long orderNumberOracle;
    private long runId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FinanceInvoice{");
        sb.append("id=").append(id);
        sb.append(", customerNumberOracle=").append(customerNumberOracle);
        sb.append(", orderNumberOracle=").append(orderNumberOracle);
        sb.append(", webId='").append(webId).append('\'');
        sb.append(", productCode='").append(productCode).append('\'');
        sb.append(", period=").append(period);
        sb.append(", billAmount=").append(billAmount);
        sb.append(", runId=").append(runId);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", packageRetailMax=").append(packageRetailMax);
        sb.append('}');
        return sb.toString();
    }
}
