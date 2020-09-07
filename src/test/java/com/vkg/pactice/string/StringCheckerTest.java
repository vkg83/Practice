package com.vkg.pactice.string;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vishnu on 5/14/2018.
 */
public class StringCheckerTest {
    @Test
    public void shouldCheckForAnagrams() throws Exception {
        Assert.assertTrue(StringChecker.areAnagrams("LISTEN","SILENT"));
        Assert.assertFalse(StringChecker.areAnagrams("LISTEN","SILENCE"));
        Assert.assertTrue(StringChecker.areAnagrams("ANAGRAM","MANAGRA"));

    }
}