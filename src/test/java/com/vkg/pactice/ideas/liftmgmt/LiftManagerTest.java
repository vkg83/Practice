package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.Direction;
import com.vkg.pactice.ideas.liftmgmt.buttons.FloorRequest;
import org.junit.Assert;
import org.junit.Test;

public class LiftManagerTest {

    @Test
    public void shouldGiveTopLiftWhenAllLiftsRunningUpwardsBelowRequestFloorForUp() throws Exception {
        LiftManager manager = new LiftManager();
        AbstractLift nearestLift = addLifts(2, manager, 5, 4, 2, 3);
        AbstractLift expLift = manager.findNearestLift(new FloorRequest(4, Direction.UP));
        Assert.assertEquals(expLift, nearestLift);
    }

    @Test
    public void shouldGiveTopLiftWhenAllLiftsRunningUpwardsBelowRequestFloorForUp1() throws Exception {
        LiftManager manager = new LiftManager();
        AbstractLift nearestLift = addLifts(2, manager, 5, 2, -2, 3);
        AbstractLift expLift = manager.findNearestLift(new FloorRequest(4, Direction.UP));
        Assert.assertEquals(expLift, nearestLift);
    }

    @Test
    public void shouldGiveTopLiftWhenAllLiftsRunningUpwardsBelowRequestFloorForUp2() throws Exception {
        LiftManager manager = new LiftManager();
        AbstractLift nearestLift = addLifts(1, manager, 5, 2, 2, 3);
        AbstractLift expLift = manager.findNearestLift(new FloorRequest(4, Direction.UP));
        Assert.assertEquals(expLift, nearestLift);
    }

    private AbstractLift addLifts(int index, LiftManager manager, int... distances) {
        AbstractLift nearest = null;
        for(int i=0; i< distances.length;i++) {
            int distance = distances[i];
            Direction dir = Direction.UP;
            if(distance < 0) {
                dir = Direction.HALTED;
                distance = -distance;
            }
            AbstractLift lift = new DummyLift(distance, dir);
            manager.addLift(lift);
            if(index == i) {
                nearest = lift;
            }
        }

        return nearest;
    }

    @Test
    public void shouldGiveCorrectLiftWhenAllLiftsRunningUpwardsBelowRequestFloorForDown() throws Exception {
        LiftManager manager = new LiftManager();
        AbstractLift lift = createLift(1, 3);
        manager.addLift(lift);
        manager.addLift(createLift(2, 5));
        AbstractLift nearestLift = manager.findNearestLift(new FloorRequest(4, Direction.DOWN));
        Assert.assertEquals(nearestLift, lift);
    }

    @Test
    public void shouldGiveBottomLiftWhenAllLiftsRunningDownwardsAboveRequestFloorForDown() throws Exception {
        LiftManager manager = new LiftManager();
        manager.addLift(createLift(5, 2));
        AbstractLift lift = createLift(4, 1);
        manager.addLift(lift);
        AbstractLift nearestLift = manager.findNearestLift(new FloorRequest(1, Direction.DOWN));
        Assert.assertEquals(nearestLift, lift);
    }

    @Test
    public void shouldGiveCorrectLiftWhenAllLiftsRunningDownwardsAboveRequestFloorForUp() throws Exception {
        LiftManager manager = new LiftManager();
        manager.addLift(createLift(5, 2));
        AbstractLift lift = createLift(4, 1);
        manager.addLift(lift);
        AbstractLift nearestLift = manager.findNearestLift(new FloorRequest(1, Direction.UP));
        Assert.assertEquals(nearestLift, lift);
    }

    private AbstractLift createLift(int from, int to) {
        AbstractLift lift = new TestLift("Test", from);
        lift.setTargetFloor(to);
        return lift;
    }

    private static class TestLift extends AbstractLift {

        public TestLift(String name, int initialFloor) {
            super(name, initialFloor);
        }
    }
}

class DummyLift extends AbstractLift {
    private int distance;
    private Direction direction;

    public DummyLift(int distance, Direction direction) {
        super("Dummy", 0);
        this.distance = distance;
        this.direction = direction;
    }

    @Override
    public int getDistance(int floor, Direction dir) {
        return distance;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Dummy " + distance + " " + direction;
    }
}