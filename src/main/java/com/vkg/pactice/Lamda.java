package com.vkg.pactice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Lamda {

    public static void main(String[] args) {
        IntStream.range(1, 100)
                .forEach( i ->
                {
                    int t = i / 0;
                }
                );
        final Runnable runnable = () -> System.out.println("Hello World"); // -/-
        final Consumer<Object> consumer = (t) -> System.out.println("Hello World"); // I/-
        final Function<Object, String> func = (t) -> t.toString(); // I/O
        final Supplier<String> supplier = () -> "Vishnu"; // -/O
        IntStream.range(1, 5);
    }

}
