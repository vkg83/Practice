package com.vkg.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class VanController {
    private final ElevatorVan van;
    private final List<FloorRequest> pendingRequests;

    public VanController(ElevatorVan van) {
        this.van = van;
        this.pendingRequests = new ArrayList<>();
        CompletableFuture.runAsync(this::run);
    }

    private void run() {
        while(true) {
            FloorRequest r = pendingRequests.get(0);

            van.move();
        }
    }

    @Override
    public String toString() {
        return "{" + van + "->" + pendingRequests.stream().map(FloorRequest::getFloor).collect(Collectors.toList())+ '}';
    }

    public int calculateCost(FloorRequest request) {
        return (int)(Math.random() * 20);
    }

    public void accept(FloorRequest request) {
        pendingRequests.add(request);
    }
}
