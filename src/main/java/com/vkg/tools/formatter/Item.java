package com.vkg.tools.formatter;

public interface Item {
    void accept(ItemVisitor visitor);
}
