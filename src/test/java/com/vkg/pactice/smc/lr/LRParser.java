package com.vkg.pactice.smc.lr;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.vkg.pactice.smc.lr.PEvent.*;
import static com.vkg.pactice.smc.lr.LRParser.NonTerminal.*;

public class LRParser {

    public LRParser(ExpLexer lexer, Builder builder) {
        this.builder = builder;
        this.lexer = lexer;
    }

    int currentState = 0;

    public void parse() {
        while(currentState != 1 && lexer.hasToken()) {
            String token = lexer.peekToken();
            PEvent e = PEvent.parse(token);
            Transition transition = transitions[currentState];
            ParserAction parserAction = transition.parserActionMap.get(e);
            if(parserAction == null) {
                throw new RuntimeException("null action");
            }
            System.out.println("e: " + e);
            parserAction.action.accept(this);
        }

    }

    public enum NonTerminal {
        IF_BLOCK,COMPARISON, CONDITION, OUTPUT, LOP, CMP
    }

    static class ParserAction {
        public Consumer<LRParser> action;
        int nextState;

        public ParserAction(int nextState, Consumer<LRParser> action) {
            this.nextState = nextState;
            this.action = action;
        }
    }
    static ParserAction reduce(Function<Builder, Object> builderFn, int pCount) {
        return new ParserAction(0,p -> p.parserReduce(builderFn, pCount));
    }
    static ParserAction shift(int nextState) {
        return new ParserAction(nextState, p -> p.parserShift());
    }
    static class Transition {
        public int currentState;
        public Map<PEvent, ParserAction> parserActionMap;
        public Map<NonTerminal, Integer> gotoMap;

        private Transition(int currentState) {
            this.currentState = currentState;
            parserActionMap = new HashMap<>();
            gotoMap = new HashMap<>();
        }

        Transition e(PEvent event, ParserAction a) {
            parserActionMap.put(event, a);
            return this;
        }

        Transition g(NonTerminal event, int nextState) {
            gotoMap.put(event, nextState);
            return this;
        }
    }
    static Transition t(int currentState) {
        return new Transition(currentState);
    }

    public Transition[] transitions = {
            t(0).e(IF, shift(2)).g(IF_BLOCK, 1),
            t(1),
            t(2).e(OP, shift(3)),
            t(3).e(O, shift(8)).e(EQ, shift(9)).e(LT, shift(10)).e(LE, shift(11)).e(GT, shift(12)).e(GE, shift(13)).g(COMPARISON, 5).g(CONDITION, 4).g(OUTPUT, 6),
            t(4).e(COMMA, shift(14)),
            t(5).e(COMMA, reduce(Builder::r6, 1)).e(AND, shift(16)).e(OR, shift(17)).g(LOP, 15),
            t(6).e(EQ, shift(9)).e(LT, shift(10)).e(LE, shift(11)).e(GT, shift(12)).e(GE, shift(13)),
            t(7).e(O, shift(20)).g(OUTPUT, 19),
            t(8).e(EQ, reduce(Builder::r8, 1)).e(LT, reduce(Builder::r8, 1)).e(LE, reduce(Builder::r8, 1)).e(GT, reduce(Builder::r8, 1)).e(GE, reduce(Builder::r8, 1)),
            t(9).e(O, reduce(Builder::r11, 1)),
            t(10).e(O, reduce(Builder::r12, 1)),
            t(11).e(O, reduce(Builder::r13, 1)),
            t(12).e(O, reduce(Builder::r14, 1)),
            t(13).e(O, reduce(Builder::r15, 1)),
            t(14).e(IF, shift(24)).e(O, shift(23)).g(IF_BLOCK, 22).g(OUTPUT, 21),
            t(15).e(O, shift(8)).e(EQ, shift(9)).e(LT, shift(10)).e(LE, shift(11)).e(GT, shift(12)).e(GE, shift(13)).g(COMPARISON, 5).g(CONDITION, 25).g(OUTPUT, 6),
            t(16).e(O, reduce(Builder::r9, 1)).e(EQ, reduce(Builder::r9, 1)).e(LT, reduce(Builder::r9, 1)).e(LE, reduce(Builder::r9, 1)).e(GT, reduce(Builder::r9, 1)).e(GE, reduce(Builder::r9, 1)),
            t(17).e(O, reduce(Builder::r10, 1)).e(EQ, reduce(Builder::r10, 1)).e(LT, reduce(Builder::r10, 1)).e(LE, reduce(Builder::r10, 1)).e(GT, reduce(Builder::r10, 1)).e(GE, reduce(Builder::r10, 1)),
            t(18).e(O, shift(20)).g(OUTPUT, 26),
            t(19).e(COMMA, reduce(Builder::r5, 2)).e(AND, reduce(Builder::r5, 2)).e(OR, reduce(Builder::r5, 2)),
            t(20).e(COMMA, reduce(Builder::r8, 1)).e(AND, reduce(Builder::r8, 1)).e(OR, reduce(Builder::r8, 1)),
            t(21).e(COMMA, shift(27)),
            t(22).e(COMMA, shift(28)),
            t(23).e(COMMA, reduce(Builder::r8, 1)),
            t(24).e(OP, shift(29)),
            t(25).e(COMMA, reduce(Builder::r7, 3)),
            t(26).e(COMMA, reduce(Builder::r4, 3)).e(AND, reduce(Builder::r4, 3)).e(OR, reduce(Builder::r4, 3)),
            t(27).e(IF, shift(33)).e(O, shift(32)).g(IF_BLOCK, 31).g(OUTPUT, 30),
            t(28).e(O, shift(32)).g(OUTPUT, 34),
            t(29).e(O, shift(8)).e(EQ, shift(9)).e(LT, shift(10)).e(LE, shift(11)).e(GT, shift(12)).e(GE, shift(13)).g(COMPARISON, 5).g(CONDITION, 35).g(OUTPUT, 6),
            t(30).e(CP, shift(36)),
            t(31).e(CP, shift(37)),
            t(32).e(CP, reduce(Builder::r8, 1)),
            t(33).e(OP, shift(38)),
            t(34).e(CP, shift(39)),
            t(35).e(COMMA, shift(40)),
            t(36),
            t(37),
            t(38).e(O, shift(8)).e(EQ, shift(9)).e(LT, shift(10)).e(LE, shift(11)).e(GT, shift(12)).e(GE, shift(13)).g(COMPARISON, 5).g(CONDITION, 41).g(OUTPUT, 6),
            t(39),
            t(40).e(IF, shift(24)).e(O, shift(23)).g(IF_BLOCK, 43).g(OUTPUT, 42),
            t(41).e(COMMA, shift(44)),
            t(42).e(COMMA, shift(45)),
            t(43).e(COMMA, shift(46)),
            t(44).e(IF, shift(24)).e(O, shift(23)).g(IF_BLOCK, 48).g(OUTPUT, 47),
            t(45).e(IF, shift(33)).e(O, shift(32)).g(IF_BLOCK, 50).g(OUTPUT, 49),
            t(46).e(O, shift(32)).g(OUTPUT, 51),
            t(47).e(COMMA, shift(52)),
            t(48).e(COMMA, shift(53)),
            t(49).e(CP, shift(54)),
            t(50).e(CP, shift(55)),
            t(51).e(CP, shift(56)),
            t(52).e(IF, shift(33)).e(O, shift(32)).g(IF_BLOCK, 58).g(OUTPUT, 57),
            t(53).e(O, shift(32)).g(OUTPUT, 59),
            t(54).e(COMMA, reduce(Builder::r1, 3)),
            t(55).e(COMMA, reduce(Builder::r3, 3)),
            t(56).e(COMMA, reduce(Builder::r2, 3)),
            t(57).e(CP, shift(60)),
            t(58).e(CP, shift(61)),
            t(59).e(CP, shift(62)),
            t(60).e(CP, reduce(Builder::r1, 3)),
            t(61).e(CP, reduce(Builder::r3, 3)),
            t(62).e(CP, reduce(Builder::r2, 3)),
    };

    private final Deque<Object> deque = new ArrayDeque<>();
    Builder builder;
    ExpLexer lexer;
    boolean parserShift() {
        String token = lexer.pollToken();
        PEvent e = PEvent.parse(token);
        final ParserAction parserAction = transitions[currentState].parserActionMap.get(e);
        if(parserAction != null) {
            currentState = parserAction.nextState;
            deque.push(token);
        }
        return parserAction != null;
    }

    boolean parserReduce(Function<Builder, Object> builderFn, int count) {
        builder.setParams(getParams(count));
        final Object result = builderFn.apply(builder);
        deque.push(result);
        NonTerminal output = builder.getOutput();

        final Integer next = transitions[currentState].gotoMap.get(output);
        if(next != null) {
            currentState = next;
        }
        return next != null;
    }

    List<Object> getParams(int count) {
        List<Object> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            items.add(0, deque.pop());
        }
        return items;
    }
}
