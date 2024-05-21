package com.vkg.pactice.smc.ml;

import com.sun.xml.internal.ws.api.ha.HaInfo;

import java.util.*;

public class LogicBuilder implements MappingLogic {

    List<MappingLogic> components;
    @Override
    public void validate(DataType expectedType) {
        Deque<MappingLogic> stack = new ArrayDeque<>();

    }

    private <T, U> boolean validateConnector(Class<T> cls, Class<U> chCls, int start, int end) {
        Map<Integer, T> map = findComponents(cls, 0, components.size());
        if(map.isEmpty()) {
            return false;
        }

        for(Map.Entry<Integer, T> e : map.entrySet()) {
            int end1 = e.getKey();
            T fn = e.getValue();
            //DataType t = fn.paramType(1);
            Map<Integer, U> fnMap = findComponents(chCls, start, end1);
        }


        return true;
    }

    private <T> Map<Integer,T> findComponents(Class<T> clz, int start, int end) {
        Map<Integer, T> map = new LinkedHashMap<>();
        for (int i = start; i < end; i++) {
            MappingLogic ml = components.get(i);
            if(clz.isInstance(ml)) {
                map.put(i, clz.cast(ml));
            }
        }
        return map;
    }

    private SupportedFunction findFunction(int i) {
        final Optional<MappingLogic> mappingLogic = components.subList(i, components.size()).stream().filter(x -> x instanceof SupportedFunction).findFirst();
        return (SupportedFunction) mappingLogic.orElse(null);
    }
}
