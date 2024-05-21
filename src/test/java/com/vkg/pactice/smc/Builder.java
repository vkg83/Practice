package com.vkg.pactice.smc;

import java.util.Stack;

public interface Builder {
    void print();

    void done();

    void value();

    void setName(String name);

    void attribute();

    void newFunction();

    void functionParam();

    void valueParam();

    void attributeParam();

    void completeNestedFunction();
}

class Printer implements Builder {
    String data;
    private String name;
    Stack<String> stack = new Stack<>();

    @Override
    public void print() {
        data += name;
    }

    @Override
    public void done() {
        if(data.endsWith(",")) {
            data = data.substring(0, data.length()-1)+")";
        }
        System.out.println("Completed: " + data);
    }

    @Override
    public void value() {
        data = ("Value: " + name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void attribute() {
        data = ("Attribute: " + name);
    }

    @Override
    public void newFunction() {
        data = "Function "+name+"(";
    }

    @Override
    public void functionParam() {
        stack.push(data);
        newFunction();
    }

    @Override
    public void valueParam() {
        data += "Value:"+name + ",";
    }

    @Override
    public void attributeParam() {
        data += "Attribute: " + name + ",";
    }

    @Override
    public void completeNestedFunction() {
        String func = data.substring(0, data.length()-1) + ")";
        data = stack.pop();
        data += func + ",";
    }
}