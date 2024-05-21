package com.vkg.pactice.smc;

import java.util.function.Consumer;

public class Parser implements TokenCollector {
    private ParserState state = ParserState.OPEN;
    private Builder builder;

    public Parser(Builder builder) {
        this.builder = builder;
    }

    @Override
    public void error(int position) {
        System.out.println("Syntax error");
    }

    @Override
    public void name(String name, int position) {
        builder.setName(name);
        handleEvent(ParserEvent.NAME, position);
    }

    @Override
    public void logicEnd(int position) {
        handleEvent(ParserEvent.LOGIC_END, position);
    }

    @Override
    public void keyword(String keyword, String name, int position) {
        if (keyword.startsWith("F")) {
            builder.setName(name);
            handleEvent(ParserEvent.FUNCTION, position);
        }
        if (keyword.startsWith("A")) {
            builder.setName(name);
            handleEvent(ParserEvent.ATTRIBUTE, position);
        }
        if (keyword.startsWith("V")) {
            builder.setName(name);
            handleEvent(ParserEvent.VALUE, position);
        }
    }

    static class Transition {
        public ParserState currentState;
        public ParserEvent event;
        public ParserState nextState;
        Consumer<Builder> action;

        public Transition(ParserState currentState, ParserEvent event, ParserState nextState, Consumer<Builder> action) {
            this.currentState = currentState;
            this.event = event;
            this.nextState = nextState;
            this.action = action;
        }
    }

    Transition[] transitions = {
            new Transition(ParserState.OPEN, ParserEvent.VALUE, ParserState.END, t -> t.value()),
            new Transition(ParserState.OPEN, ParserEvent.ATTRIBUTE, ParserState.END, t -> t.attribute()),
            new Transition(ParserState.OPEN, ParserEvent.FUNCTION, ParserState.FUNCTION_PARAM, t -> t.newFunction()),
            new Transition(ParserState.FUNCTION_PARAM, ParserEvent.VALUE, ParserState.FUNCTION_PARAM, t -> t.valueParam()),
            new Transition(ParserState.FUNCTION_PARAM, ParserEvent.ATTRIBUTE, ParserState.FUNCTION_PARAM, t -> t.attributeParam()),
            new Transition(ParserState.FUNCTION_PARAM, ParserEvent.FUNCTION, ParserState.NESTED_FUNCTION_PARAM, t -> t.functionParam()),
            new Transition(ParserState.FUNCTION_PARAM, ParserEvent.LOGIC_END, ParserState.END, t -> t.done()),
            new Transition(ParserState.NESTED_FUNCTION_PARAM, ParserEvent.VALUE, ParserState.NESTED_FUNCTION_PARAM, t -> t.valueParam()),
            new Transition(ParserState.NESTED_FUNCTION_PARAM, ParserEvent.ATTRIBUTE, ParserState.NESTED_FUNCTION_PARAM, t -> t.attributeParam()),
            new Transition(ParserState.NESTED_FUNCTION_PARAM, ParserEvent.FUNCTION, ParserState.NESTED_FUNCTION_PARAM, t -> t.functionParam()),
            new Transition(ParserState.NESTED_FUNCTION_PARAM, ParserEvent.LOGIC_END, ParserState.FUNCTION_PARAM, t -> t.completeNestedFunction()),
            new Transition(ParserState.FUNCTION_PARAM, ParserEvent.EOF, ParserState.END, t -> t.done()),
            new Transition(ParserState.END, ParserEvent.EOF, ParserState.END, null),
    };

    private void handleEvent(ParserEvent event, int position) {
        for (Transition t : transitions) {
            if (t.currentState == state && t.event == event) {
                state = t.nextState;
                if(t.action != null) {
                    t.action.accept(builder);
                }
                return;
            }
        }
    }
}
