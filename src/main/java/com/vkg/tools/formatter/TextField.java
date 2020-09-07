package com.vkg.tools.formatter;

/**
 * Created by Vishnu on 12/14/2018.
 */
public class TextField extends Field {
    public String value;
    public TextField(String name) {
        this(name, "String");
    }
    public TextField(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getDefaultValue() {
        return value;
    }
}
