package com.vkg.pactice.smc.lr;

enum PEvent {
    IF("if"), COMMA(","), OP("("), CP(")"), O("o"), AND("&&"), OR("||"), LT("<"), GT(">"), EQ("=="), LE("<="), GE(">="), END("");
    String token;

    PEvent(String token) {
        this.token = token;
    }

    static PEvent parse(String token) {
        for (PEvent e : PEvent.values()) {
            if (e.token.equals(token)) {
                return e;
            }
        }
        return null;
    }
}
