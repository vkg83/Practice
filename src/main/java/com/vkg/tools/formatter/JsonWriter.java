package com.vkg.tools.formatter;

import java.util.Stack;

public class JsonWriter implements ItemVisitor {
    private int level;
    private Stack<Integer> pLevel = new Stack<>();
    private Stack<Boolean> keyRequired = new Stack<>();

    private void printKey(String name) {
        if(!keyRequired.isEmpty() && keyRequired.peek()) {
            System.out.print("\"" + name + "\" : ");
        }
    }

    @Override
    public void visit(Field field) {
        indent(); printKey(field.getName());
        System.out.print("value");
    }

    @Override
    public void completeVisit(Field field) {
        if(!pLevel.isEmpty() && pLevel.peek()==level)pLevel.pop();
        pLevel.push(level);
    }

    @Override
    public void visit(CompositeObject obj) {
        indent(); printKey(obj.getName()); System.out.print("{");

        keyRequired.push(true);

        pLevel.push(level);
        level++;
    }

    @Override
    public void completeVisit(CompositeObject obj) {
        if(!keyRequired.isEmpty())keyRequired.pop();

        level--;
        indent(); System.out.print("}");
        if(!pLevel.isEmpty())pLevel.pop();

    }

    @Override
    public void visit(CompositeArray arr) {
        indent(); printKey(arr.getName()); System.out.print("[");

        keyRequired.push(false);

        pLevel.push(level);
        level++;
    }

    @Override
    public void completeVisit(CompositeArray arr) {
        if(!keyRequired.isEmpty())keyRequired.pop();

        level--;
        indent(); System.out.print("]");
        if(!pLevel.isEmpty())pLevel.pop();

    }

    private void indent() {
        indent(0);
    }
    private void indent(int x) {
        if(!pLevel.isEmpty() && pLevel.peek() == level+x) {
            System.out.print(",");
        }
        System.out.println();
        System.out.print("("+(pLevel.isEmpty()? "*" : pLevel.peek()) +": "+ level+")");
        for (int i = 0; i < level+x; i++) {
            System.out.print("    ");
        }
    }
}
