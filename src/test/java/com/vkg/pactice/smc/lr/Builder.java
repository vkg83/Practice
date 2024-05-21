package com.vkg.pactice.smc.lr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Builder {
    List<Object> params;
    private LRParser.NonTerminal output;

    public void setParams(List<Object> params) {
        this.params = params;
    }

//r1    BLOCK -> if ( Condition , Output , Output )
public Map<String, Object> r1() {
    Map<String, Object> map = new HashMap<>();
    map.put("condition", params.get(0));
    map.put("true", params.get(1));
    map.put("false", params.get(2));
    output= LRParser.NonTerminal.IF_BLOCK;
    return map;
}

//r2    BLOCK -> if ( Condition , BLOCK , Output )
public Map<String, Object> r2() {
    Map<String, Object> map = new HashMap<>();
    map.put("condition", params.get(0));
    map.put("true", params.get(1));
    map.put("false", params.get(2));
    output = LRParser.NonTerminal.IF_BLOCK;
    return map;
}
//r3    BLOCK -> if ( Condition , Output , BLOCK )
public Map<String, Object> r3() {
    Map<String, Object> map = new HashMap<>();
    map.put("condition", params.get(0));
    map.put("true", params.get(1));
    map.put("false", params.get(2));
    output = LRParser.NonTerminal.IF_BLOCK;
    return map;
}
//r4    Comp -> Output cOp Output
//r5    Comp -> cOp Output
public Map<String, Object> r4() {
    return r5();
}public Map<String, Object> r5() {
    Map<String, Object> map = new HashMap<>();
    output = LRParser.NonTerminal.COMPARISON;
    return map;
}
//r6    Condition -> Comp
//r7    Condition -> Comp Op Condition
public Map<String, Object> r6() {
    return r7();
}
public Map<String, Object> r7() {
    Map<String, Object> map = new HashMap<>();
    map.put("con", "");
    output = LRParser.NonTerminal.CONDITION;
    return map;
}

    //r8    Output -> o
    public String r8() {
        output = LRParser.NonTerminal.OUTPUT;
        return (String) params.get(0);
    }
    //r9    Op -> &&
//r10    Op -> ||
    public Map<String, Object> r9() {
        return r10();
    }
    public Map<String, Object> r10() {
        Map<String, Object> map = new HashMap<>();
        map.put("op", "logical");
        output= LRParser.NonTerminal.LOP;
        return map;
    }

//r11    cOp -> ==
//r12    cOp -> <
//r13    cOp -> <=
//r14    cOp -> >
//r15    cOp -> >=
    public Map<String, Object> r11() {
        return r15();
    }
    public Map<String, Object> r12() {
        return r15();
    }
    public Map<String, Object> r13() {
        return r15();
    }
    public Map<String, Object> r14() {
        return r15();
    }

    public Map<String, Object> r15() {
        Map<String, Object> map = new HashMap<>();
        map.put("comp", "comparator");
        output = LRParser.NonTerminal.CMP;
        return map;
    }

    public LRParser.NonTerminal getOutput() {
        return output;
    }
}
