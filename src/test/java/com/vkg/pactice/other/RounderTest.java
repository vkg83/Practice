package com.vkg.pactice.other;

import org.junit.Test;

public class RounderTest {

    @Test
    public void shouldName() throws Exception {
        final DummyRounder rounder = new DummyRounder(3.23656);
        System.out.println(rounder.toMoney());
    }
}

class DummyRounder extends Rounder {

    public DummyRounder(final double number) {
        super(number);
    }

    @Override
    long round(final double d) {
        return (long) d;
    }
}