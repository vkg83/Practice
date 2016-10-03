package com.vkg.pactice.other;

public class Rounder {
    double number;

    public Rounder(final double number) {
        this.number = number;
    }

    public String toMoney() {
        long a = (long) number;
        long b = round((number - a) * 100);
        return "$" + a + "." + b;
    }

    long round(double d) {
        return Math.round(d);
    }

    public static void main(String[] args) {
        Rounder r = new Rounder(3.657234);

        System.out.println(r.toMoney());
    }
}

// $3.33
