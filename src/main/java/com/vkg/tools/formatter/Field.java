package com.vkg.tools.formatter;

public abstract class Field implements Item {
    private final String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CompositeArray multi() {
        return new CompositeArray(this);
    }

    public CompositeArray multi(int count) {
        return new CompositeArray(this, count);
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
        visitor.completeVisit(this);
    }

    public abstract Object getDefaultValue();
}
