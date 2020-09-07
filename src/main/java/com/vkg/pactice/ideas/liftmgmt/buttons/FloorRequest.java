package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.Request;

/**
 * Created by Vishnu on 10/24/2017.
 */
public class FloorRequest implements Request {
    private int floor;
    private Direction direction;
    public FloorRequest(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}
