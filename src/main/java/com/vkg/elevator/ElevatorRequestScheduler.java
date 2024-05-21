package com.vkg.elevator;

import java.util.List;
import java.util.stream.Collectors;

public class ElevatorRequestScheduler implements RequestHandler<FloorRequest> {
    private List<VanController> controllers;

    public ElevatorRequestScheduler(List<VanController> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void accept(FloorRequest request) {
        //System.out.println("Request received for floor: " + request.getFloor()+"");
        int minCost = Integer.MAX_VALUE;
        VanController finalController = null;
        for (VanController c : controllers) {
            int cost = c.calculateCost(request);
            if(cost < minCost) {
                minCost = cost;
                finalController = c;
            }
        }
        finalController.accept(request);
        printStatus();
    }

    private void printStatus() {
        String line = controllers.stream().map(VanController::toString).collect(Collectors.joining(" # "));
        System.out.print(line + "\r");
    }
}
