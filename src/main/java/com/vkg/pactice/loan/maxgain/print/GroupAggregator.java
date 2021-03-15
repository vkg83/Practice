package com.vkg.pactice.loan.maxgain.print;

import com.vkg.pactice.loan.maxgain.Entry;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class GroupAggregator<T> implements EntryAggregator<T> {
    private final List<? extends Entry> entries;
    private Function<Entry, T> groupValueExtractor;
    private Supplier<AggregatedEntry<T>> groupSupplier;
    private List<AggregatorRule<?, ?>> ruleList = new ArrayList<>();

    public GroupAggregator(List<? extends Entry> entries) {
        this.entries = entries;
    }

    public void groupBy(Function<Entry, T> groupValueExtractor, Supplier<AggregatedEntry<T>> groupSupplier) {
        this.groupValueExtractor = groupValueExtractor;
        this.groupSupplier = groupSupplier;
    }

    public <E, I> void addRule(AggregatorRule<E, I> rule) {
        ruleList.add(rule);
    }

    public <X extends Number> void sum(Function<Entry, X> extractor, BiConsumer<AggregatedEntry<?>, Double> consumer) {
        GroupAggregator.AggregatorRule<X, Double> rule = new GroupAggregator.AggregatorRule<>(0.0);
        rule.extractor(extractor);
        rule.operate((x, y) -> x + y.doubleValue());
        rule.applier(consumer);
        addRule(rule);
    }

    public <X extends Number> void last(Function<Entry, X> extractor, BiConsumer<AggregatedEntry<?>, Number> consumer) {
        GroupAggregator.AggregatorRule<X, Number> rule = new GroupAggregator.AggregatorRule<>(0.0);
        rule.extractor(extractor);
        rule.operate((x, y) -> y.doubleValue());
        rule.applier(consumer);
        addRule(rule);
    }

    /**
     *
     *      IN  BAL DAYS
     *      2   34  3
     *      1   54  5
     *      ---------
     *      3   34  5
     *      ---------
     *      2   34  5
     *      5   42  2
     *      8   27  4
     *      ---------
     *      5   48  7
     *      ---------
     *
     */
    @Override
    public Map<T, AggregatedEntry<T>> aggregateGroup() {
        Map<T, AggregatedEntry<T>> map = new LinkedHashMap<>();
        Map<T, List<Object>> tempMap = new LinkedHashMap<>();
        for (Entry e: entries) {
            T gv = groupValueExtractor.apply(e);
            //AggregatedEntry<T> aggEntry = map.get(gv);
            List<Object> aggRow = tempMap.get(gv);
            if(aggRow == null) {
//                aggEntry = groupSupplier.get();
//                map.put(gv, aggEntry);
//                aggEntry.setAggregatedOn(gv);
                aggRow = init();
                tempMap.put(gv, aggRow);
            }
            int idx = 0;
            for (AggregatorRule<?, ?> rule : ruleList) {
                rule.aggregate(aggRow, idx++, e);
                //rule.aggregate(aggEntry, e);
            }
        }

        for (T key : tempMap.keySet()) {
            List<Object> row = tempMap.get(key);
            AggregatedEntry<T> aggEntry = groupSupplier.get();
            map.put(key, aggEntry);
            aggEntry.setAggregatedOn(key);
            for (int i = 0; i < ruleList.size(); i++) {
                AggregatorRule<?, ?> rule = ruleList.get(i);
                rule.apply(aggEntry, row.get(i));
            }

        }
        return map;
    }

    private List<Object> init() {
        return ruleList.stream().map(AggregatorRule::initValue).collect(Collectors.toList());
    }

    @Override
    public AggregatedEntry aggregate() {
        AggregatedEntry aggEntry = new AggregatedMapEntry();
        for (Entry e: entries) {
            for (AggregatorRule<?, ?> colExtractor : ruleList) {
                //colExtractor.aggregate(aggEntry, e);
            }
        }

        return aggEntry;
    }

    public static class AggregatorRule<X, Y> {
        Supplier<Y> initSupplier;
        BiFunction<Y, X, Y> op;
        Function<Entry, ? extends X> valueExtractor;
        BiConsumer<AggregatedEntry<?>, ? super Y> consumer;

        public AggregatorRule(Y initValue) {
            this.initSupplier = () -> initValue;
        }

        public AggregatorRule(Supplier<Y> initSupplier) {
            this.initSupplier = initSupplier;
        }
        public void extractor(Function<Entry, ? extends X> extractor) {
            valueExtractor = extractor;
        }

        public void operate(BiFunction<Y, X, Y> op) {
            this.op = op;
        }

        public void applier(BiConsumer<AggregatedEntry<?>, ? super Y> consumer) {
            this.consumer = consumer;
        }

        private void aggregate(AggregatedEntry<?> result, Entry e) {
            X value = valueExtractor.apply(e);
            Y aggVal = (Y)valueExtractor.apply(result);
            Y val = op.apply(aggVal, value);
            consumer.accept(result, val);
        }
        private Y initValue() {
            return initSupplier.get();
        }
        private void aggregate(List<Object> result, int idx, Entry e) {
            X value = valueExtractor.apply(e);
            Y aggVal = (Y)result.get(idx);
            Y val = op.apply(aggVal, value);
            result.set(idx,val);
        }

        public void apply(AggregatedEntry<?> aggEntry, Object val) {
            consumer.accept(aggEntry, (Y)val);
        }
    }
}
