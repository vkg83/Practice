package com.vkg.pactice.other;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiPredicate;

public class DirCrawler {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\eBooks");
        new DirCrawler().crawl(path);
    }

    private void crawl(Path path) {
        try{
            final LabelGroovyVisitor visitor = new LabelGroovyVisitor();
            Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 50, visitor);
            final Set<String> uniques = visitor.getUniques();
            uniques.forEach(System.out::println);
            System.out.println("Total: " + uniques.size() + ", Duplicates: " + visitor.duplicates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
