package com.vkg.tools.formatter;

import java.util.ArrayList;
import java.util.List;

public class CompositeObject extends Field {
    private List<Item> items = new ArrayList<>();
    private VisitorConfig config;

    public CompositeObject(String name) {
        super(name);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void accept(ItemVisitor visitor) {

        visitor.visit(this);
        for (int i = 0; i < items.size(); i++) {
            items.get(i).accept(visitor);
        }
        visitor.completeVisit(this);
    }

    @Override
    public Object getDefaultValue() {
        return null;
    }

    public void setConfig(VisitorConfig config) {
        this.config = config;
    }
    public VisitorConfig getConfig() {
        return config;
    }
}
