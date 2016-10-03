package com.vkg.pactice.arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayAdapter {
    public Object convert(Object o, String outType) {
        Collection inlist;
        Collection outlist;
        if(o.getClass().isArray()) {
            final int length = Array.getLength(o);
            inlist = new ArrayList();
            for (int i = 0; i < length; i++) {
                inlist.add(Array.get(o, i));
            }
        } else {
            inlist = (Collection) o;
        }

        if(outType.equals("[]")) {
            outlist = new ArrayList();
        } else {
            outlist = createNewCollection(outType);
        }

        for (Object x : inlist) {
            outlist.add(adapt(x));
        }

        if(outType.equals("[]")) {
            return outlist.toArray();
        }

        return outlist;

    }

    private Object adapt(final Object x) {
        return x;
    }

    private Collection createNewCollection(final String outType) {
        try {
            return Collection.class.cast(Class.forName(outType).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4};
        ArrayAdapter arrayAdapter = new ArrayAdapter();
        Collection col = (Collection) arrayAdapter.convert(arr, "java.util.ArrayList");
        System.out.println(col);
        Object[] arr2 = (Object[]) arrayAdapter.convert(col, "[]");
        System.out.println(Arrays.toString(arr2));
    }

}
