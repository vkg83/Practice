package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.Direction;
import org.junit.Assert;
import org.junit.Test;

public class AbstractLiftTest {
    @Test
    public void shouldCalculateDirectDistanceInSameDirection() throws Exception {
        assertDistance(2, 3, 4, Direction.UP, 2);
        assertDistance(2, 4, 3, Direction.UP, 1);
        assertDistance(4, 2, 1, Direction.DOWN, 3);
        assertDistance(4, 1, 2, Direction.DOWN, 2);
    }

    @Test
    public void shouldCalculateDirectDistanceInOppositeDirection() throws Exception {
        assertDistance(2, 3, 4, Direction.DOWN, 2);
        assertDistance(2, 4, 3, Direction.DOWN, 3);
        assertDistance(4, 2, 1, Direction.UP, 3);
        assertDistance(4, 1, 2, Direction.UP, 4);
    }
    @Test
    public void shouldCalculateIndirectDistance() throws Exception {
        assertDistance(3, 2, 4, Direction.UP, 3);
        assertDistance(3, 4, 2, Direction.UP, 3);
        assertDistance(3, 2, 4, Direction.DOWN, 3);
        assertDistance(3, 4, 2, Direction.DOWN, 3);
    }

    private void assertDistance(int from, int to, int floor, Direction dir, int expDistance) {
        AbstractLift lift = new TestLift("Test", from);
        lift.setTargetFloor(to);
        Assert.assertEquals(expDistance, lift.getDistance(floor, dir));
    }

    private static class TestLift extends AbstractLift {

        public TestLift(String name, int initialFloor) {
            super(name, initialFloor);
        }
    }
}

