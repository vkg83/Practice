package com.vkg.tools.formatter;

public class NumberField extends Field {
    private int value;
    public NumberField(String name) {
        this(name, 1);
    }

    public NumberField(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public Integer getDefaultValue() {
        return value;
    }
}
