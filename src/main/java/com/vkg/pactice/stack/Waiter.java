package com.vkg.pactice.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Waiter {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int steps = sc.nextInt();
        Stack<Integer> startStack = new Stack<Integer>();
        for (int i = 0; i < count; i++) {
            startStack.push(sc.nextInt());
        }

        Stack<Integer> endStack = new Stack<Integer>();
        List<Integer> primes = waiter.getPrimes(steps);
        System.out.println(primes);
        for (int prime : primes) {
            waiter.move(startStack, endStack, prime);
            Stack<Integer> tmp = startStack;
            startStack = endStack;
            endStack = tmp;
        }

        while (!startStack.isEmpty()) {
            System.out.println(startStack.pop());
        }
    }

    private List<Integer> getPrimes(int count) {
        List<Integer> primes = new ArrayList<Integer>();
        if(count == 0) return primes;
        primes.add(2);
        if(count == 1) return primes;
        primes.add(3);


        while(count>2) {
            int last = primes.get(primes.size() - 1);
            do {
                last = last + 2;
            } while(isPrime(last, primes)==false);
            primes.add(last);
            count--;
        }

        return primes;
    }

    private boolean isPrime(final int last, final List<Integer> primes) {
        int limit = (int)Math.sqrt(last);
        for (int prime: primes) {
            if(prime > limit) {
                break;
            } else if (last % prime == 0) {
                return false;
            }
        }
        return true;
    }


    private void move(final Stack<Integer> startStack, final Stack<Integer> endStack, int prime) {
        Stack<Integer> tmp = new Stack<Integer>();
        while(!startStack.isEmpty()) {
            int plate = startStack.pop();
            if(plate % prime == 0) {
                tmp.push(plate);
            } else {
                endStack.push(plate);
            }
        }

        while (!tmp.isEmpty()) {
            System.out.println(tmp.pop());
        }
    }
}
