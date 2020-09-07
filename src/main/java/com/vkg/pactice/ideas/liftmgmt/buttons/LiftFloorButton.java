package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.AbstractLift;
import com.vkg.pactice.ideas.liftmgmt.Request;

public class LiftFloorButton extends AbstractButton {
    private final LiftRequest request;

    public LiftFloorButton(AbstractLift lift, int floor) {
        super(lift);
        this.request = new LiftRequest(floor);
    }

    @Override
    protected Request createRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "Floor " + request.getFloor() + " Button in " + handler;
    }
}
