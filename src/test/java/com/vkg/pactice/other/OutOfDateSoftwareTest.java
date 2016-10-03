package com.vkg.pactice.other;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class OutOfDateSoftwareTest {
    @Test
    public void sameVersionShouldCompareToZero() throws Exception {
        Assert.assertEquals(0, new Version("1.2.3").compareTo(new Version("1.2.3")));
        Assert.assertEquals(0, new Version("1.12.3").compareTo(new Version("1.12.3")));
        Assert.assertEquals(0, new Version("1.02.3").compareTo(new Version("01.2.3")));
    }

    @Test
    public void lowerVersionShouldCompareToLessThanZero() throws Exception {
        Assert.assertTrue(new Version("1.2.3").compareTo(new Version("2.2.3")) < 0);
        Assert.assertTrue(new Version("1.2.3").compareTo(new Version("1.3.1")) < 0);
        Assert.assertTrue(new Version("1.2.3").compareTo(new Version("1.2.5")) < 0);
        Assert.assertTrue(new Version("1.2").compareTo(new Version("1.2.5")) < 0);
        Assert.assertTrue(new Version("1.2.3").compareTo(new Version("1.3")) < 0);
    }

    @Test
    public void heigherVersionShouldCompareToGreaterThanZero() throws Exception {
        Assert.assertTrue(new Version("5.2.3").compareTo(new Version("2.2.3")) > 0);
        Assert.assertTrue(new Version("1.4.3").compareTo(new Version("1.3.1")) > 0);
        Assert.assertTrue(new Version("1.2.8").compareTo(new Version("1.2.5")) > 0);
        Assert.assertTrue(new Version("1.2.8").compareTo(new Version("1.2")) > 0);
        Assert.assertTrue(new Version("1.3").compareTo(new Version("1.2.8")) > 0);
    }

    @Test
    public void shouldReadDataCorrectly() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader("Location, Type, Name, 3.4"));
        OutOfDateSoftware oos = new OutOfDateSoftware();
        final Software software = oos.readSoftware(reader);
        Assert.assertEquals("Name", software.getName());
        Assert.assertTrue(new Version("3.4").compareTo(software.getVersion()) == 0);
    }

    @Test
    public void shouldFindCorrectly() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(
                "Server1, Database, MySQL, 5.5\n" +
                "Server2, Database, MySQL, 5.1\n" +
                "Server3, OS, Ubuntu, 10.04\n" +
                "Server1, OS, Ubuntu, 10.04\n" +
                "Server2, OS, Ubuntu, 12.04\n" +
                "Server3, Language, Python, 2.6.3"));
        StringWriter strWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(strWriter);
        OutOfDateSoftware oos = new OutOfDateSoftware();
        oos.find(reader, writer);
        writer.close();
        Assert.assertEquals("Ubuntu\n",strWriter.getBuffer().toString());
    }
}