package com.vkg.pactice.stack;

import org.junit.Assert;
import org.junit.Test;

public class MyStackTest {
    @Test
    public void shouldPush() throws Exception {
        MyStack<String> stk = new MyStack<>();
        stk.push("XYZ1");
        stk.push("XYZ2");
        stk.push("XYZ3");
        stk.push("XYZ4");
        stk.push("XYZ5");
        stk.push("XYZ6");
        stk.push("XYZ7");
        stk.push("XYZ8");
        stk.push("XYZ9");
        stk.push("XYZ10");
        Assert.assertEquals("XYZ10", stk.pop());
        Assert.assertEquals("XYZ9", stk.pop());
        Assert.assertEquals("XYZ8", stk.pop());
        Assert.assertEquals("XYZ7", stk.pop());
        Assert.assertEquals("XYZ6", stk.pop());
        Assert.assertEquals("XYZ5", stk.pop());
        Assert.assertEquals("XYZ4", stk.pop());
        Assert.assertEquals("XYZ3", stk.pop());
        Assert.assertEquals("XYZ2", stk.pop());
        Assert.assertEquals("XYZ1", stk.pop());
        Assert.assertEquals("XYZ1", stk.pop());
    }
}