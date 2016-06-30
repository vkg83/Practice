package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    public String largestNumber(final List<Integer> a) {
        List<Integer> strList = new ArrayList<Integer>(a);


        Collections.sort(strList, new StrComparator());
        StringBuilder builder = new StringBuilder();
        for (Integer str : strList) {
            builder.append(str);
        }

        return builder.toString();
    }

    class StrComparator implements Comparator<Integer> {

        public int compare(final Integer o1, final Integer o2) {
            String a = o1 +""+ o2;
            String b = o2 +""+ o1;


            return b.compareTo(a);
        }
    }

    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        final List<Integer> list = Arrays.asList(0, 0, 0 ,0);//3, 30, 34, 5, 9);
        System.out.println(ln.largestNumber(list));
    }
}
