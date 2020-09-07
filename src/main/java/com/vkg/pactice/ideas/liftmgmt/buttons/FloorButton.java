package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.LiftManager;
import com.vkg.pactice.ideas.liftmgmt.Request;

public class FloorButton extends AbstractButton {
    private final FloorRequest request;

    public FloorButton(LiftManager manager, int floor, Direction dir) {
        super(manager);
        this.request = new FloorRequest(floor, dir);
    }

    @Override
    protected Request createRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "Floor " + request.getFloor() + " " + request.getDirection() + " Button";
    }
}
