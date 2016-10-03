package com.vkg.pactice.other;

public class StaticClass {
    static void m(Object o) {
        System.out.println("m(o)");
    }
    static void m(Integer i) {
        System.out.println("m(i)");
    }
    static void m(String s) {
        System.out.println("m(s)");
    }
    static <T> void f(T t) {
        m(t);
    }

    public static void main(String[] args) {
        m("asa");
    }
}
