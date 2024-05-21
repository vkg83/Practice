package com.vkg.elevator;

import com.vkg.pactice.ideas.liftmgmt.buttons.Direction;

public class ElevatorVan {
    int currentFloor;
    Direction direction;

    @Override
    public String toString() {
        return "" + currentFloor;
    }

    public void move() {
        if(direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }
    }

    public void startUp() {
        this.direction = Direction.UP;
    }
    public void startDown() {
        this.direction = Direction.DOWN;
    }
    public void stop() {
        this.direction = Direction.HALTED;
    }
}
