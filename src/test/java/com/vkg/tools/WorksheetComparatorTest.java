package com.vkg.tools;

import com.vkg.tools.client.FinanceInvoice;
import com.vkg.tools.client.Oracle;
import com.vkg.tools.client.SpendReport;
import com.vkg.tools.excel.WorksheetComparator;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

public class WorksheetComparatorTest {
    @Test
    public void shouldCompareFinanceInvoice() throws Exception {
        WorksheetComparator<FinanceInvoice> comparator = new WorksheetComparator<FinanceInvoice>("/Users/guptav/Documents/Billing Diff.xlsx", FinanceInvoice.class);
        final WorksheetComparator.CompareResult result = comparator.compare();
        printResult(result);
    }

    private void printResult(final WorksheetComparator.CompareResult result) {
        List dList = result.getDeletedRecords();
        printItems("Deleted records detail :", dList, System.out::println);
        List aList = result.getAddedRecords();
        printItems("Added records detail :", aList, System.out::println);
        List<WorksheetComparator.Pair> list = result.getUpdatedRecords();
        printItems("Changed records detail :", list, p -> {
            System.out.println("Old: " + p.getFirst());
            System.out.println("New: " + p.getSecond());
        });
    }

    private <T> void printItems(String msg, List<T> list, Consumer<T> consumer) {
        if (list.size() > 0) {
            System.out.println(msg);
        }

        list.stream().forEach(consumer);
    }

    @Test
    public void shouldCompareOracle() throws Exception {
        WorksheetComparator<Oracle> comparator = new WorksheetComparator<Oracle>("/Users/guptav/Documents/Billing Diff.xlsx", Oracle.class);
        final WorksheetComparator<Oracle>.CompareResult result = comparator.compare();
        printResult(result);
    }

    @Test
    public void shouldCompareSpendReport() throws Exception {
        WorksheetComparator<SpendReport> comparator = new WorksheetComparator<SpendReport>("/Users/guptav/Documents/Billing Diff.xlsx", SpendReport.class);
        final WorksheetComparator<SpendReport>.CompareResult result = comparator.compare();
        printResult(result);
    }
}