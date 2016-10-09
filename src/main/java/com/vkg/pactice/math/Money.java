package com.vkg.pactice.math;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Money implements Serializable, Comparable<Money> {
    private final long value;
    private final double scale;
    private static final double DEFAULT_SCALE = 100;

    enum STRATEGY {
        FIRST, LAST, SMALLEST, BIGGEST, LARGEST_DEFICIT
    }

    public static final Money ZERO = new Money(0, DEFAULT_SCALE);
    public static final Money ONE = create(1);
    public static final Money ONE_CENT = create(0.01);

    private Money(final long value, final double scale) {
        this.value = value;
        this.scale =  scale;
    }

    public static Money create(final double value) {
        return new Money(Math.round(value * DEFAULT_SCALE), DEFAULT_SCALE);
    }

    public static Money createFloor(final double value) {
        return new Money((long)Math.floor(value * DEFAULT_SCALE), DEFAULT_SCALE);
    }

    public static Money createCeil(final double value) {
        return new Money((long)Math.ceil(value * DEFAULT_SCALE), DEFAULT_SCALE);
    }

    public static Money create(final double value, final double scale) {
        return new Money(Math.round(value * scale), scale);
    }

    public static Money createFloor(final double value, final double scale) {
        return new Money((long)Math.floor(value * scale), scale);
    }

    public static Money createCeil(final double value, final double scale) {
        return new Money((long)Math.ceil(value * scale), scale);
    }

    public double toDouble() {
        return value / scale;
    }

    public Money abs() {
        return new Money(Math.abs(value), scale);
    }

    public Money plus(final Money another) {
        return operate(another, Money::plus, (a, b) -> a + b);
    }

    public Money minus(final Money another) {
        return operate(another, Money::minus, (a, b) -> a - b);
    }

    public Money multiply(final Money another) {
        return operate(another, Money::multiply, (a, b) -> a * b / (long)scale);
    }

    public boolean isGreaterThanZero() {
        return value > 0;
    }

    public Iterable<Money> toIterableBySmallest() {
        return () -> new Iterator<Money>() {
            long start = 0;
            @Override
            public boolean hasNext() {
                return start < value;
            }

            @Override
            public Money next() {
                start++;
                return new Money(1, scale);
            }
        };
    }

    private Money operate(final Money another, final BiFunction<Money, Money, Money> op, final BiFunction<Long, Long, Long> actualOp) {
        if (scale == another.scale) {
            return new Money(actualOp.apply(value, another.value), scale);
        } else if (scale > another.scale) {
            return op.apply(this, another.rescale(scale));
        } else {
            return op.apply(rescale(another.scale), another);
        }
    }

    public Money rescale(final double newScale) {
        return create(toDouble(), newScale);
    }


    public List<Money> split(final Double ... proportions) {
        return split(Arrays.asList(proportions));
    }

    public List<Money> split(final List<Double> proportions) {
        //Biggest is default split strategy because of zero proportional case.
        //let split = [0, 10/3, 10/3, 10/3], the result should not allocate money to the first proportion which is zero.
        return split(proportions, STRATEGY.BIGGEST);
    }

    public List<Money> split(final List<Double> proportions, final STRATEGY strategy) {
        final double total = proportions.stream().mapToDouble(i -> i).sum();

        final List<Money> moneyFromProportion = proportions.stream().map(p -> Money.create(p, scale)).collect(Collectors.toList());
        if (hasEqualTotal(moneyFromProportion)) {
            return moneyFromProportion;
        }

        final List<Long> result = new ArrayList<>();
        for (final Double p : proportions) {
            result.add((long) Math.floor(value * p / total));
        }

        long remaining = value - result.stream().mapToLong(i -> i).sum();

        final List<Integer> orders = toOrdering(proportions, strategy);
        while (remaining > 0) {

            for (int i = 0; i < orders.size() && remaining > 0; i++) {
                final Integer index = orders.get(i);
                result.set(index, result.get(index) + 1);
                remaining--;
            }
        }

        return result.stream().map(v -> new Money(v, scale)).collect(Collectors.toList());
    }

    private boolean hasEqualTotal(final List<Money> moneyFromProportion) {
        return moneyFromProportion.stream().mapToDouble(Money::toDouble).sum() == toDouble();
    }

    private List<Integer> toOrdering(final List<Double> proportions, final STRATEGY strategy) {
        final List<Entry<Integer, Double>> indexToValues = new ArrayList<>(IntStream.range(0, proportions.size())
                .boxed()
                .collect(Collectors.toMap(i -> i, proportions::get)).entrySet());
        if (strategy == STRATEGY.BIGGEST) {
            Collections.sort(indexToValues, (final Entry<Integer, Double> e1, final Entry<Integer, Double> e2) ->  toCompareValue(e2.getValue() - e1.getValue()));
        } else if (strategy == STRATEGY.SMALLEST) {
            Collections.sort(indexToValues, (final Entry<Integer, Double> e1, final Entry<Integer, Double>  e2) ->  toCompareValue(e1.getValue() - e2.getValue()));
        } else if (strategy == STRATEGY.LAST) {
            Collections.sort(indexToValues, (final Entry<Integer, Double> e1, final Entry<Integer, Double> e2) ->  e2.getKey() - e1.getKey());
        } else if (strategy == STRATEGY.FIRST) {
            Collections.sort(indexToValues, (final Entry<Integer, Double> e1, final Entry<Integer, Double> e2) ->  e1.getKey() - e2.getKey());
        } else if (strategy == STRATEGY.LARGEST_DEFICIT) {
            Collections.sort(indexToValues, (final Entry<Integer, Double> e1, final Entry<Integer, Double> e2) ->  toCompareValue(getDeficit(e2.getValue()) - getDeficit(e1.getValue())));
        }

        return indexToValues.stream().map(e -> e.getKey()).collect(Collectors.toList());
    }

    private double getDeficit(final Double val) {
        return val - Math.floor(val);
    }

    private int toCompareValue(final double v) {
        if (v < 0) {
            return -1;
        } else if (v > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return toDouble() == money.toDouble();
    }

    @Override
    public int hashCode() {
        return Objects.hash(toDouble());
    }

    @Override
    public String toString() {
        return String.valueOf(toDouble());
    }

    @Override
    public int compareTo(final Money o) {
        return Double.valueOf(toDouble()).compareTo(Double.valueOf(o.toDouble()));
    }

}
