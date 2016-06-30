package com.vkg.pactice.other;

public class InheritanceDataAccess {
}


class Super {
    private int a;

    protected Super(int a) {
        this.a = a;
    }
}

class Sub extends Super {
    public Sub(int a) {
        super(a);
    }

    //public Sub() { this.a = 5; } no super constructor
    public Sub() {
        this(5);
    }
}

///////////////////////////////////////////////////////////

class Building {
}

class Barn extends Building {
    public static void main(String[] args) {
        Building build1 = new Building();
        Barn barn1 = new Barn();
        Barn barn2 = (Barn) build1;
        Object obj1 = (Object) build1;
        //String str1 = (String) build1; // this will not compile - not in hierarchy
        Building build2 = (Building) barn1;
    }
}