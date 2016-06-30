package com.vkg.pactice.stack;

import java.util.Scanner;
import java.util.Stack;

public class PoisonousPlant {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named BrickMaker. */
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] plants = new int[size];
        for(int i = 0; i < size; i++) {
            plants[i] = sc.nextInt();
        }

        PoisonousPlant poisonousPlant = new PoisonousPlant();
        int days = poisonousPlant.removeDead(plants);

        System.out.println(days);
    }

    private int removeDead(int[] pests) {
        if(pests.length < 2) {
            return 0;
        }
        Stack<Plant> stack = new Stack<Plant>();
        stack.push(new Plant(pests[0], 0));
        for (int i = 1; i < pests.length; i++) {
            int pest = pests[i];
            Plant p = stack.peek();
            int life = p.life + 1;
            if(p.pest < pest) {
                if(p.life > 0) {
                    life--;
                }
            } else {
                while(p.life > 0) {
                    stack.pop();
                    p = stack.peek();
                }

                if(p.pest >= pest) {
                    p.life = life - 1;
                    life = 0;
                }


            }
            stack.push(new Plant(pest, life));

            System.out.println(stack);
        }

        int maxLife = 0;
        while(!stack.isEmpty()) {
            Plant p = stack.pop();
            if(p.life > maxLife) {
                maxLife = p.life;
            }
        }
        return maxLife;

    }

    private class Plant {
        int pest;
        int life;

        public Plant(final int pest, final int life) {
            this.pest = pest;
            this.life = life;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Plant{");
            sb.append("pest=").append(pest);
            sb.append(", life=").append(life);
            sb.append('}');
            return sb.toString();
        }
    }
}