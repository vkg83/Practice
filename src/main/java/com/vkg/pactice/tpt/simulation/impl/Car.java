package com.vkg.pactice.tpt.simulation.impl;

import com.vkg.pactice.tpt.simulation.Direction;
import com.vkg.pactice.tpt.simulation.Movable;
import com.vkg.pactice.tpt.simulation.Point;

public class Car implements Movable {
    private Point current;
    @Override
    public boolean canMove(final int distance, final Direction direction) {
        return false;
    }

    @Override
    public void move(final int distance, final Direction direction) {
        Point next = current.next(distance, direction);
        driveTo(next);
        current = next;
    }

    private void driveTo(final Point next) {
        System.out.println("From " + current + " To " + next);
    }

    @Override
    public void moveUp(final int distance) {
        move(distance, Direction.UP);
    }

    @Override
    public void moveDown(final int distance) {
        move(distance, Direction.DOWN);
    }

    @Override
    public void moveLeft(final int distance) {
        move(distance, Direction.LEFT);
    }

    @Override
    public void moveRight(final int distance) {
        move(distance, Direction.RIGHT);
    }
}
