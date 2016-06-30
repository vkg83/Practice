package com.vkg.pactice.other;

public class UniquePaths {
    public int uniquePaths(int a, int b) {
        if (a == 1 || b == 1) return 1;
        return uniquePaths(a-1, b)+uniquePaths(a, b-1);
    }

    public static void main(String[] args) {
        UniquePaths paths = new UniquePaths();
        System.out.println(paths.uniquePaths(2, 2));
    }
}
