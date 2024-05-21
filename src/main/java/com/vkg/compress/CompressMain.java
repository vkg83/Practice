package com.vkg.compress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final CompressMain main = new CompressMain();
        List<Mismatch> list = main.createList(200000);
        String path = "C:\\Users\\Vishnu Kant Gupta\\Downloads\\compress.obj";
        long begin = System.currentTimeMillis();
        main.writeToNormalFile(path+"ect", list);
        long start = System.currentTimeMillis();
        final long normalSize = new File(path + "ect").length();
        System.out.println("File size: " + normalSize);
        System.out.println("Normal save time: " + (start - begin));

        main.writeToFile(path, list);
        long mid = System.currentTimeMillis();
        final List<Mismatch> result = main.readFromFile(path);
        long end = System.currentTimeMillis();
        System.out.println("Total mismatches: " + result.size());
        final long compressedSize = new File(path).length();
        System.out.println("File size: " + compressedSize);
        System.out.println("Save time: " + (mid - start));
        System.out.println("Load time: " + (end - mid));
        System.out.println(String.format("Compression: %.2f%%", compressedSize * 100.0 / normalSize));

    }

    private List<Mismatch> createList(int count) {
        List<Mismatch> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Item leftItem = new Item("expected",i);
            Item rightItem = new Item("actual", i);
            Mismatch mis = new Mismatch(leftItem, rightItem);
            list.add(mis);
        }
        return list;
    }

    private void writeToNormalFile(String path, List<Mismatch> mismatchList) throws IOException {
        try(FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(mismatchList);
        }
    }

    private void writeToFile(String path, List<Mismatch> mismatchList) throws IOException {
        try(FileOutputStream f = new FileOutputStream(path);
            GZIPOutputStream z = new GZIPOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(z)) {
            o.writeObject(mismatchList);
        }
    }
    private List<Mismatch> readFromFile(String path) throws IOException, ClassNotFoundException {
        try(FileInputStream f = new FileInputStream(path);
            GZIPInputStream z = new GZIPInputStream(f);
            ObjectInputStream o = new ObjectInputStream(z)) {
            return (List<Mismatch>) o.readObject();
        }
    }
}
