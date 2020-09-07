package com.vkg.pactice.hugexls;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vishnu on 7/27/2019.
 */
public class ExcelReaderTest {
    @Test
    public void test() throws Exception {
        String path = ExcelReaderTest.class.getResource("/Home.xlsx").toURI().getPath();
        System.out.println(path);
        ExcelReader example = new ExcelReader();
        example.processOneSheet(path);
    }

}