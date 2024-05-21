package com.vkg.elevator;

public class FloorRequest implements ElevatorRequest {
    private int floor;

    public FloorRequest(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
