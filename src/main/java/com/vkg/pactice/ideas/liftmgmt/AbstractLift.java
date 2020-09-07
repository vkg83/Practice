package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractLift implements Runnable, RequestHandler<LiftRequest> {
    private String name;
    private int currentFloor;
    private int targetFloor;
    private BlockingQueue<LiftRequest> requestQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;

    public AbstractLift(String name, int initialFloor) {
        this.name = name;
        currentFloor = initialFloor;
        targetFloor = currentFloor;
    }

    public synchronized void move() {
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(this + " Interrupted");
        }
        Direction dir = getDirection();
        if (dir == Direction.UP) {
           currentFloor++;
        } else if(dir == Direction.DOWN) {
            currentFloor--;
        }
        System.out.println(this + " Moving " + dir + " (Floor " + currentFloor+")");
    }

    public int getDistance(int floor, Direction dir) {
        Direction liftDir = getDirection();
        if(liftDir == Direction.HALTED || isComingTo(floor) && getDirection() == dir) {
            return getDistance(currentFloor, floor);
        } else {
            return getDistance(currentFloor, targetFloor) + getDistance(targetFloor, floor);
        }
    }

    public Direction getDirection() {
        Direction dir = Direction.HALTED;
        if(targetFloor > currentFloor) {
            dir = Direction.UP;
        }else if(targetFloor < currentFloor) {
            dir = Direction.DOWN;
        }

        return dir;
    }

    private int getDistance(int from, int to) {
        return Math.abs(to - from);
    }

    private boolean isComingTo(int floor) {
        return (currentFloor < targetFloor && currentFloor < floor) || (currentFloor > targetFloor && currentFloor > floor);
    }

    @Override
    public String toString() {
        return "AbstractLift " + name;
    }

    public void shutdown() {
        System.out.println("Stop called for " + this);
        running = false;
    }

    @Override
    public void run() {
        running = true;
        waitForPeople();
        while(running) {
            LiftRequest request;
            try {
                request = requestQueue.poll(1, TimeUnit.SECONDS);
                if (request == null) continue;
            } catch (InterruptedException e) {
                throw new RuntimeException(this + " Interrupted");
            }

            targetFloor = request.getFloor();
            while (!isHalted()) {
                move();
            }

            openDoor();
            waitForPeople();
            closeDoor();
        }
        System.out.println(this + " is Stopped ***");
    }

    protected void waitForPeople() {
        System.out.println(this + " @ Floor " + currentFloor);
    }

    private void closeDoor() {
        System.out.println(this + " Doors Closing");
    }

    private void openDoor() {
        System.out.println(this + " Doors Opening");
    }

    @Override
    public void handle(LiftRequest req) {
        requestQueue.add(req);
    }

    boolean isHalted() {
        return currentFloor == targetFloor;
    }

    public boolean canHandle(FloorRequest request) {
        return true;
    }

    public void start() {
        new Thread(this).start();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }
}
