package com.vkg.pactice.smc.lr;

public class ExpLexer {
    String[] tokens;
    private int current = 0;
    public ExpLexer(String s) {
        tokens = s.split(" ");
    }

    public String pollToken() {
        return nextToken(true);
    }

    public String peekToken() {
        return nextToken(false);
    }

    private String nextToken(boolean forward) {
        String token = tokens[current];
        if(forward) {
            current++;
        }
        return token;
    }

    public boolean hasToken() {
        return current < tokens.length;
    }
}
