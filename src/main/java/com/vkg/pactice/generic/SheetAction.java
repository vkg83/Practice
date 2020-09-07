package com.vkg.pactice.generic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SheetAction implements Action {
    READ, WRITE, EXECUTE;

    @Override
    public List<SheetAction> other() {
        return Stream.of(values()).filter(a -> a != this).collect(Collectors.toList());
    }
    public String getTask() {
        return name().toLowerCase();
    }

    @Override
    public String getStatus() {
        return name().toLowerCase();
    }
}
