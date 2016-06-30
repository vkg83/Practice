package com.vkg.pactice.arrays;

import java.util.ArrayList;
import java.util.Stack;

public class RPNEvaluator {
    public static void main(String[] args) {
        RPNEvaluator evaluator = new RPNEvaluator();
        final String[] arr = {"2", "1", "+", "3", "*"};
        double result = evaluator.evaluate(arr);
        System.out.println(result);
        String[] arr2 = {"4", "13", "5", "/", "+"};
        double result2 = evaluator.evaluate(arr2);
        System.out.println(result2);
    }

    private double evaluate(final String[] arr) {
        String operators = "+-*/";
        Stack<Double> stack = new Stack<Double>();
        for (int i = 0; i < arr.length; i++) {
            String token = arr[i];
            if(!operators.contains(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                int operator = operators.indexOf(token);
                final Double b = stack.pop();
                final Double a = stack.pop();
                switch(operator) {
                    case 0:
                        stack.push((a + b));
                        break;
                    case 1:
                        stack.push(a - b);
                        break;
                    case 2:
                        stack.push(a * b);
                        break;
                    case 3:
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }

    private double evalRPN(final ArrayList<String> arr) {
        String operators = "+-*/";
        Stack<Double> stack = new Stack<Double>();
        for (int i = 0; i < arr.size(); i++) {
            String token = arr.get(i);
            if(!operators.contains(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                int operator = operators.indexOf(token);
                final Double b = stack.pop();
                final Double a = stack.pop();
                switch(operator) {
                    case 0:
                        stack.push((a + b));
                        break;
                    case 1:
                        stack.push(a - b);
                        break;
                    case 2:
                        stack.push(a * b);
                        break;
                    case 3:
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }

}
