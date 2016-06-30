package com.vkg.pactice.stack;

import java.util.Scanner;
import java.util.Stack;

public class SimpleEditor {
    private StringBuilder text = new StringBuilder();
    private Stack<Operation> history = new Stack<Operation>();
    public static void main(String[] args) {
        SimpleEditor editor = new SimpleEditor();
        Scanner sc = new Scanner(System.in);
        int operationCount = sc.nextInt(); sc.nextLine();
        for(int i = 0; i < operationCount; i++) {
            String[] tokens = sc.nextLine().split(" ");
            switch(Integer.parseInt(tokens[0])) {
                case 1:
                    String str = tokens[1];
                    editor.history.push(new Operation(true, editor.append(str)));
                    break;
                case 2:
                    int count = Integer.parseInt(tokens[1]);
                    editor.history.push(new Operation(false, editor.delete(count)));
                    break;
                case 3:
                    int index = Integer.parseInt(tokens[1]);
                    editor.print(index);
                    break;
                case 4:
                    editor.undo();
                    break;
            }
        }
    }

    private void undo() {
        if(!history.isEmpty()) {
            final Operation operation = history.pop();
            if(operation.append) {
                delete(operation.str.length());
            } else {
                append(operation.str);
            }
        }
    }

    private void print(final int index) {
        if (index > 0 && index <= text.length()) {
            System.out.println(text.charAt(index - 1));
        }
    }

    private String delete(final int count) {
        final int length = text.length();
        final int start = length - count;
        String str = "";
        if (start >= 0) {
            str = text.substring(start);
            text.delete(start, length);

        }
        return str;
    }

    private String append(final String str) {
        text.append(str);
        return str;
    }

    private static class Operation {
        private boolean append;
        private String str;

        public Operation(final boolean append, final String str) {
            this.append = append;
            this.str = str;
        }
    }
}
