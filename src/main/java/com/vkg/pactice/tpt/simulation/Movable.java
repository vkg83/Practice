package com.vkg.pactice.tpt.simulation;

public interface Movable {
    boolean canMove(int distance, Direction direction);
    void move(int distance, Direction direction);
    void moveUp(int distance);
    void moveDown(int distance);
    void moveLeft(int distance);
    void moveRight(int distance);
}
