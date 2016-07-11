package com.vkg.pactice.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PartyGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int personCount = sc.nextInt();
        int relationCount = sc.nextInt();

        Person[] persons = new Person[personCount];

        for (int i = 0; i < personCount; i++) {
            int iq = sc.nextInt();
            persons[i] = new Person(iq, i);
        }

        for (int i = 0; i < relationCount; i++) {
            int p1Index = sc.nextInt() - 1;
            int p2Index = sc.nextInt() - 1;
            persons[p2Index].setGroup(persons[p1Index].getGroup());
        }

        Map<Integer, Leader> map = new HashMap<>();

        for (Person person : persons) {
            Leader leader = map.get(person.getGroup());
            if(leader == null || leader.iq < person.iq) {
                leader = new Leader(person.iq);
                map.put(person.getGroup(), leader);
            }

            if(leader.iq == person.iq) {
                leader.increaseCount();
            }
        }

        long ways = 1;

        int MOD=1000000007;
        for (Leader l : map.values()) {
            ways = (ways * l.count) % MOD;
        }

        System.out.println(ways);
    }
}

class Leader {
    final int iq;
    int count;

    public Leader(final int iq) {
        this.iq = iq;
        this.count = 0;
    }

    public void increaseCount() {
        this.count++;
    }
}

class Person {
    final int iq;
    int group;

    public Person(final int iq, final int group) {
        this.iq = iq;
        this.group = group;
    }

    public void setGroup(final int group) {
        this.group = group;
    }

    public int getGroup() {
        return group;
    }
}
