package com.vkg.pactice.trees;

public class RPNConvertor {
    public static void main(String[] args) {
        RPNConvertor convertor = new RPNConvertor();
        final String[] tokens = "( 1 + 2 ) * 3".split(" ");
        String[] rpn = convertor.convert(tokens);
    }

    private String[] convert(final String[] tokens) {
        return new String[0];
    }
}
