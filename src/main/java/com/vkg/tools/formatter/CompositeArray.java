package com.vkg.tools.formatter;

public class CompositeArray implements Item {
    private final Field field;
    private int repeat;
    private VisitorConfig config;

    public CompositeArray(Field field) {
        this(field, 2);
    }
    public CompositeArray(Field field, int repeat) {
        this.field = field;
        this.repeat = repeat;
    }

    public String getName() {
        return field.getName();
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
        for (int i = 0; i < repeat; i++) {
            field.accept(visitor);
        }
        visitor.completeVisit(this);
    }

    public VisitorConfig getConfig() {
        return config;
    }

    public void setConfig(ExcelVisitorConfig config) {
        this.config = config;
    }
}
