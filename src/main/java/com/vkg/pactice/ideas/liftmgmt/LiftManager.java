package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.LiftRequest;
import com.vkg.pactice.ideas.liftmgmt.buttons.FloorRequest;
import com.vkg.pactice.ideas.liftmgmt.buttons.RequestHandler;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LiftManager implements Runnable, RequestHandler<FloorRequest> {
    private Set<AbstractLift> lifts = new HashSet<>();
    private BlockingQueue<FloorRequest> requestQueue = new LinkedBlockingQueue<>();
    private volatile boolean running;

    public void start() {
        new Thread(this).start();
    }

    public void addLift(AbstractLift lift) {
        lifts.add(lift);
    }

    public void handle(FloorRequest request) {
        requestQueue.add(request);
    }

    AbstractLift findNearestLift(FloorRequest request) {
        AbstractLift nearestLift = null;
        int minDistance = Integer.MAX_VALUE;

        for (AbstractLift lift : lifts) {
            int dist = lift.getDistance(request.getFloor(), request.getDirection());
            if(dist < minDistance || lift.isHalted() && dist == minDistance) {
                nearestLift = lift;
                minDistance = dist;
            }
        }
        return nearestLift;
    }

    AbstractLift findNearestLift8(FloorRequest request) {
        return lifts.stream().filter(lift -> lift.canHandle(request)).min(getLiftComp(request)).get();
    }

    private Comparator<AbstractLift> getLiftComp(FloorRequest request) {
        return (l1, l2) -> {
            int dist1 = l1.getDistance(request.getFloor(), request.getDirection());
            int dist2 = l2.getDistance(request.getFloor(), request.getDirection());
            return dist1 != dist2 ? dist1 - dist2 : (!l1.isHalted() && l2.isHalted() ? 1 : 0);
        };
    }

    public void shutdown() {
        System.out.println("Stop called for AbstractLift SYSTEM");
        running = false;
    }
    @Override
    public void run() {
        running = true;
        lifts.forEach(AbstractLift::start);

        while(running) {
            FloorRequest request;
            try {
                request = requestQueue.poll(1, TimeUnit.SECONDS);
                if (request == null) continue;
            } catch (InterruptedException e) {
                throw new RuntimeException("AbstractLift system interrupted");
            }
            AbstractLift lift = findNearestLift(request);
            if (lift == null) {
                System.out.println("No lift found");
                continue;
            }
            lift.handle(new LiftRequest(request.getFloor()));
        }

        lifts.forEach(AbstractLift::shutdown);

        System.out.println("***AbstractLift System is stopped");
    }
}
