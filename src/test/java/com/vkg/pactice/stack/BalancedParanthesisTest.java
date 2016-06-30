package com.vkg.pactice.stack;

import org.junit.Assert;
import org.junit.Test;

public class BalancedParanthesisTest {
    @Test
    public void shouldHaveBalancedParanthesis() throws Exception {
        //"()" and "()[]{}"
        BalancedParanthesis validator = new BalancedParanthesis();
        Assert.assertEquals(1, validator.isValid("()"));
        Assert.assertEquals(1, validator.isValid("()[]{}"));
    }

    @Test
    public void shouldNotHaveBalancedParanthesis() throws Exception {
        //"(]" and "([)]
        BalancedParanthesis validator = new BalancedParanthesis();
        Assert.assertEquals(0, validator.isValid("(]"));
        Assert.assertEquals(0, validator.isValid("([)]"));
    }
}