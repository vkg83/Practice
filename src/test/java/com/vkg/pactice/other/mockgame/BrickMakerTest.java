package com.vkg.pactice.other.mockgame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BrickMakerTest {
    private BrickMaker brickMaker;

    @Before
    public void setUp() throws Exception {
        brickMaker = new BrickMaker(0, 0, 0);

    }

    @Test
    public void canNotMake() throws Exception {
        Assert.assertEquals(false, brickMaker.canMakeWallWithLargeBricks());
    }
    @Test
    public void canMake() throws Exception {
        Assert.assertEquals(true, new BrickMaker(2, 5, 2).canMakeWallWithLargeBricks());
    }
}