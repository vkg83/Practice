package com.vkg.compress;

import java.io.Serializable;

public class Mismatch implements Serializable {
    private static final long serialVersionUID = 1L;
    private Item expected;
    private Item actual;

    public Mismatch(Item expected, Item actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public void setExpected(Item expected) {
        this.expected = expected;
    }

    public Item getExpected() {
        return expected;
    }

    public void setActual(Item actual) {
        this.actual = actual;
    }

    public Item getActual() {
        return actual;
    }
}
