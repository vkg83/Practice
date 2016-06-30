package com.vkg.pactice.other;

public class VarArg {
    public static void main(String[] args) {
//        new VarArg().go("hi", 1);
//        new VarArg().go("hi", "world", 2);
        new VarArg().go(1, "hi");
        new VarArg().go(2, "hi", "world");
    }

//    public void go(String... y, int x) { // VarArg should be the last
//        System.out.print(y[y.length - 1] + " ");
//    }

    public void go(int x, String... y) {
        System.out.print(y[y.length - 1] + " ");
    }
}