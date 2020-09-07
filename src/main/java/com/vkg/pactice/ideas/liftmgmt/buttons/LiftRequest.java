package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.Request;

/**
 * Created by Vishnu on 10/24/2017.
 */
public class LiftRequest implements Request {
    private int floor;

    public LiftRequest(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
