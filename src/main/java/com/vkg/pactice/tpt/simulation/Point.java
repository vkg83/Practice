package com.vkg.pactice.tpt.simulation;

public class Point {
    private final int x, y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Point next(final int distance, final Direction direction) {
        int x =  this.x + getNext(distance, direction, Direction.RIGHT, Direction.LEFT);
        int y =  this.y + getNext(distance, direction, Direction.UP, Direction.DOWN);
        return new Point(x, y);
    }

    private int getNext(final int value, Direction direction, final Direction forward, final Direction back) {
        return direction == forward ? value : (direction == back ? -value : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
