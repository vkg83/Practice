package com.vkg.pactice.t9;

import org.junit.Assert;
import org.junit.Test;

public class KeypadTest {

    @Test
    public void shouldGiveAssignedCharacter() throws Exception {
        Keypad keypad = new Keypad();
        keypad.assign(1, "ABC");
        keypad.assign(2, "DEF");
        keypad.assign(4, "GHI");
        keypad.assign(5, "JKL");
        keypad.assign(6, "MNO");
        keypad.assign(7, "PQRS");
        keypad.assign(8, "TUVW");
        keypad.assign(9, "XYZ");
        keypad.assign(-1, "");
        keypad.assign(0, " ");
        keypad.assignToggle(-2);
        PadUser.withKeypad(keypad).typesIn(1).shouldGiveTextAs("A");
        PadUser.withKeypad(keypad).typesIn(1, 1).shouldGiveTextAs("B");
        PadUser.withKeypad(keypad).typesIn(1, 1, 1).shouldGiveTextAs("C");
        PadUser.withKeypad(keypad).typesIn(1, 1, 1, 2, 5, 5).shouldGiveTextAs("CDK");
        PadUser.withKeypad(keypad).typesIn(1, 1, 1, 1, 1, -1, 1, 1, 1).shouldGiveTextAs("CBC");
        PadUser.withKeypad(keypad).typesIn(4, 4, 4, -2, 0, 1, 6, 0, -2, 8,8,8,-2,4,4,4,7,7,7,7,4,4, 6,6,8,8).shouldGiveTextAs("I AM VISHNU");
    }
    private static class PadUser {
        private final Keypad pad;
        private int[] keys;

        public PadUser(final Keypad pad) {
            this.pad = pad;
        }

        public static PadUser withKeypad(Keypad pad) {
            return new PadUser(pad);
        }

        public PadUser typesIn(final int ... keys) {
            this.keys = keys;
            return this;
        }

        public void shouldGiveTextAs(final String text) {
            Assert.assertEquals(text, pad.convert2(keys));
        }
    }
}