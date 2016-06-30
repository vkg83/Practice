package com.vkg.pactice.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalindromicSubstringTest {

    private PalindromicSubstring ps;

    @Before
    public void setUp() throws Exception {
        ps = new PalindromicSubstring();
    }

    @Test
    public void shouldName() throws Exception {
        //verify("caccbcbaabacabaccacaaccaccaaccbbcbcbbaacabccbcccbbacbbacbccaccaacaccbbcc", "caaccaccaac");
        //verify("bb", "bb");
        verify("bcbbbcbbbc", "cbbbcbbbc");
        //verify("bbacaccaabcbaccbcacacabcbcbbbcbbbccabcbccacbacbbaacbbbcbbaabbbcabcabccaacbcccaabaccacabaccabbcacbc", "cbbbcbbbc");
    }

    private void verify(final String str, final String subStr) {
        String length = ps.longestPalSubstr(str);
        Assert.assertEquals(subStr, length);
    }
}