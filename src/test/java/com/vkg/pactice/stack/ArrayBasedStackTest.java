package com.vkg.pactice.stack;

import org.junit.Assert;
import org.junit.Test;


public class ArrayBasedStackTest {
    @Test
    public void shouldInsert() {
        ArrayBasedStack<Integer> stk = new ArrayBasedStack<>(10);
        for (int i = 1; i <= 10; i++) {
            stk.push(i);
        }
        for (int i = 10; i > 0; i--) {
            Assert.assertEquals(Integer.valueOf(i), stk.pop());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowPopFromEmptyStack() throws Exception {
        ArrayBasedStack<Integer> stk = new ArrayBasedStack<>(1);
        stk.pop();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowPushInAlreadyFullStack() {
        ArrayBasedStack<Integer> stk = new ArrayBasedStack<>(1);
        stk.push(1);
        stk.push(2);
    }
}