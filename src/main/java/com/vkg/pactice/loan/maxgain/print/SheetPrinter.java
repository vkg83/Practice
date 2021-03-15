package com.vkg.pactice.loan.maxgain.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class SheetPrinter<T> implements AccountPrinter<T> {
    List<String> cols = new ArrayList<>();
    @Override
    public final void print() {
        //String[] cols = {"Month", "Iterest", "Limit", "Available", "Book Balance"};
        int size = printHeader1();
        printContent();
        printFooter(size);
    }

    protected abstract void printContent();

    public int printHeader1() {
        String str = String.join(" | ", cols);
        String s = fill(str.length() + 2, '-');
        print("+"+s+"+");
        print("| "+str+" |");
        print("|"+s+"|");
        return s.length();
    }

    private String fill(int length, char s) {
        char[] arr = new char[length];
        Arrays.fill(arr, s);
        return new String(arr);
    }


    public void printHeader() {
        print("+--------------------------------------------------------------------+");
        print("|  #| Month    | Interest  | Limit       | Available   | Book Balance|");
        print("|--------------+-----------+-------------+-------------+-------------|");
    }

    public void printFooter(int size) {
        String s = fill(size, '-');
        System.out.println("+"+s+"+");
//        print("Balance:        %12.2f", state.getBalance());
//        print("Interest Saved: %12.2f", state.getEmiBalance());
//        print("Interest Payed: %12.2f", totalInterest);
    }

    protected void print(String format, Object... values) {
        System.out.println(String.format(format, values));
    }

    public void add(String s) {
        cols.add(s);
    }
}
