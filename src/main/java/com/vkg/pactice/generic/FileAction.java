package com.vkg.pactice.generic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FileAction implements Action {
    UPLOAD, DOWNLAOD, CONVERT;

    @Override
    public List<FileAction> other() {
        return Stream.of(values()).filter(a -> a != this).collect(Collectors.toList());
    }

    @Override
    public String getStatus() {
        return name().toUpperCase();
    }
}
