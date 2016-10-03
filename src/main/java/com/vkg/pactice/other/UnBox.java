package com.vkg.pactice.other;

class Shape {
    public Shape makeCopy() {
        // code to make a copy of this Shape
        return null;
    }
}

class Circle extends Shape {
    @Override
    public Circle makeCopy() {
        // code to make a copy of a Circle
        return null;
    }
}

public class UnBox {
    public static void main(String[] args) {
        Shape s = new Circle();
        //Circle c = s.makeCopy();
    }
}
