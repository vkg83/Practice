package com.vkg.compress;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    public Item(String prefix, int index) {
        Map<String, Object> values = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            String key = RandomStringUtils.randomAlphanumeric(10);
            String value = RandomStringUtils.random(20);
            values.put(prefix+key, value);
        }
        for (int i = 0; i < 15; i++) {
            String key = RandomStringUtils.randomAlphanumeric(10);
            boolean value = RandomUtils.nextBoolean();
            values.put(prefix+key, value);
        }
        for (int i = 0; i < 20; i++) {
            String key = RandomStringUtils.randomAlphanumeric(10);
            int value = RandomUtils.nextInt();
            values.put(prefix+key, value);
        }
        for (int i = 0; i < 20; i++) {
            String key = RandomStringUtils.random(10);
            long value = RandomUtils.nextLong();
            values.put(prefix+key, value);
        }
        for (int i = 0; i < 15; i++) {
            String key = RandomStringUtils.random(10);
            BigInteger value = BigInteger.valueOf(RandomUtils.nextLong());
            values.put(prefix+key, value);
        }
    }
}
