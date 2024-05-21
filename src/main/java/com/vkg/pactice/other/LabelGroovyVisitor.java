package com.vkg.pactice.other;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
abstract class GroovyVisitor implements FileVisitor<Path> {

    @Override
    public final FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        preVisitDirectory(dir);
        return FileVisitResult.CONTINUE;
    }

    public abstract void preVisitDirectory(Path dir) throws IOException;


    @Override
    public final FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        visitFile(file);
        return FileVisitResult.CONTINUE;
    }

    public abstract void visitFile(Path file) throws IOException;

    @Override
    public final FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public final FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}
class LabelGroovyVisitor extends GroovyVisitor {
    Set<String> uniques = new HashSet<>();
    int duplicates = 0;
    @Override
    public void preVisitDirectory(Path dir) {
        System.out.println("Directory: " + dir);
    }

    @Override
    public void visitFile(Path file) {
        final String fileName = file.getFileName().toString();

        System.out.println("  File: " + fileName);
        int dot = fileName.indexOf(".");
        final String clazz = fileName.substring(0, dot >= 0 ? dot : fileName.length());
        duplicates+= uniques.add(clazz)? 0 : 1;
    }

    public Set<String> getUniques() {
        return uniques;
    }
}
