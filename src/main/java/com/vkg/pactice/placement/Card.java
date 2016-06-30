package com.vkg.pactice.placement;

public class Card implements Comparable<Card> {
    private int rowSize;
    private int columnSize;
    private int priority;
    private int x, y;

    public Card(final int rowSize, final int columnSize, final int priority) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.priority = priority;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public int getArea() {
        return rowSize * columnSize;
    }

    public int getPriority() {
        return priority;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int compareTo(final Card o) {
        return this.priority - o.priority;
    }
}
