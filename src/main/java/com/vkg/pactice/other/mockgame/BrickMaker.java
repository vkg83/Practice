package com.vkg.pactice.other.mockgame;

public class BrickMaker {
    private int smallBrickCount;
    private int largeBrickCount;
    private int wallLength;
    private int SMALL_BRICK_SIZE = 1;
    private int LARGE_BRICK_SIZE = 5;

    public BrickMaker(final int smallBrickCount, final int largeBrickCount, final int wallLength) {
        this.smallBrickCount = smallBrickCount;
        this.largeBrickCount = largeBrickCount;
        this.wallLength = wallLength;
    }

    public boolean canMakeWallWithLargeBricks() {
        return wallLength / LARGE_BRICK_SIZE < largeBrickCount;
    }

    public int useLargeBricks() {
        return useBricks(LARGE_BRICK_SIZE);
    }

    public int useSmallBricks() {
        return useBricks(SMALL_BRICK_SIZE);
    }
    private int useBricks(final int brickSize) {
        int brickCount = brickSize == LARGE_BRICK_SIZE ? largeBrickCount : smallBrickCount;
        final int constructedWall = brickCount * brickSize;
        if(constructedWall > wallLength) {
            return wallLength % brickSize;
        }else {
            return wallLength - constructedWall;
        }
    }

    public boolean canMakeWall() {
        for(int i  = 1; i <= largeBrickCount; i++) {
            if((wallLength - LARGE_BRICK_SIZE * i) / SMALL_BRICK_SIZE < smallBrickCount) {
                return true;
            }
        }
        return false;
    }
}