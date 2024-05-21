package com.vkg.pactice.smc.lr;

public class Main {
    public static void main(String[] args) {
        final ExpLexer expLexer = new ExpLexer("if ( o < o , o , o )");
        final Builder builder = new Builder();
        final LRParser parser = new LRParser(expLexer, builder);
        parser.parse();
    }
}
