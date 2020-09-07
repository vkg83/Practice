package com.vkg.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;

/**
 * Created by Vishnu on 2/2/2018.
 */
public class Demo {
    public static void main(String[] args) throws ParseException, IOException {
        Arrays.stream(new int[]{1, 2, 3, 4}).map(n->3*n +1).filter(n->n>10).average().ifPresent(System.out::println);
        System.out.println(new Double("2.0").equals(new Double("2.00")) + "" +new BigDecimal("2.0").equals(new BigDecimal("2.00")));
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(2);
        System.out.println(f.parse("211.2332323asas"));
        short s = 9;
        add(s, 6);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        //os.writeObject(new Shape());
        os.close();
        System.out.println(out.toString());
        System.out.println("A");
        synchronized (args) {
            System.out.println("B");
            try {
                args.wait();
            } catch (InterruptedException ie) {

            }
        }
        System.out.println("C");
    }
    static void  add(int a, int b) {

    }
    int p = 0;
    void sum () {
        int a = 10;
        class Inner {
            void see() {
                System.out.println(a + p);
            }
        }
    }
}

class Fruit {

}
class Apple extends Fruit {

}

class Basket {
    public void add(Collection<Fruit> col) {
        Fruit f = new Apple();
        col.add(f);
    }
}

class Shape implements Serializable {
    private Square s = new Square();
}
class Square {

}