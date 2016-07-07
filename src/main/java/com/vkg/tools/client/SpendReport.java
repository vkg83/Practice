package com.vkg.tools.client;

import com.vkg.tools.excel.Key;
import com.vkg.tools.excel.Sheet;
import com.vkg.tools.excel.Value;

import java.util.Date;

@Sheet(oldName = "Spend Report Old", newName = "Spend Report New", allowDuplicate = true)
public class SpendReport {
    @Key private long customerNumber;
    @Key private int packageId;
    @Value private int packageTypeId;
    @Value private boolean isNetworkPackage;
    @Value private String billingProductCode;
    @Value private Double margin;
    @Value private boolean isBillable;
    @Value private Date fromDate;
    @Value private Date toDate;
    @Value private Double markup;
    @Value private boolean useMargin;
    @Value private Long marginScheduleId;
    @Value private Double customerMarginScheduleSpend;
    @Value private Double packageRetailMax;
    @Value private Double packageInvoiceApplied;
    @Value private Double packageSpend;
    @Value(delta=.01) private Double packageInvoice;
    private long id;
    private Date created;
    private Date lastModified;
    private long runId;
    private Long polkDealerInfoId;
    @Value private Long customerGroupId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpendReport{");
        sb.append("id=").append(id);
        sb.append(", customerNumber=").append(customerNumber);
        sb.append(", packageId=").append(packageId);
        sb.append(", packageTypeId=").append(packageTypeId);
        sb.append(", isNetworkPackage=").append(isNetworkPackage);
        sb.append(", billingProductCode='").append(billingProductCode).append('\'');
        sb.append(", margin=").append(margin);
        sb.append(", isBillable=").append(isBillable);
        sb.append(", created=").append(created);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", fromDate=").append(fromDate);
        sb.append(", toDate=").append(toDate);
        sb.append(", runId=").append(runId);
        sb.append(", markup=").append(markup);
        sb.append(", useMargin=").append(useMargin);
        sb.append(", marginScheduleId=").append(marginScheduleId);
        sb.append(", polkDealerInfoId=").append(polkDealerInfoId);
        sb.append(", customerMarginScheduleSpend=").append(customerMarginScheduleSpend);
        sb.append(", packageRetailMax=").append(packageRetailMax);
        sb.append(", packageInvoiceApplied=").append(packageInvoiceApplied);
        sb.append(", packageSpend=").append(packageSpend);
        sb.append(", packageInvoice=").append(packageInvoice);
        sb.append(", customerGroupId=").append(customerGroupId);
        sb.append('}');
        return sb.toString();
    }
}
