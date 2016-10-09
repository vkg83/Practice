package com.vkg.pactice.math;


public class DivideByZero {
    public static void main(String[] args) {
        try {

            int i, sum;

            sum = 10;

            for (i = -1; i < 3 ;++i)

                sum = (sum / i);

        }

        catch(ArithmeticException e) {
            e.printStackTrace();
            System.out.print("0");

        }

        //System.out.print(sum);
        Constructor c = new Constructor(null);
    }
}

class Constructor {
    public Constructor(Object o) {
        System.out.println("O");
    }
    public Constructor(Number o) {
        System.out.println("N");
    }
    public Constructor(Integer o) {
        System.out.println("S");
    }
}
