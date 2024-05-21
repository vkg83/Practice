package com.vkg.elevator;

public class Button {
    private final RequestHandler handler;
    private final int floor;

    public Button(RequestHandler handler, int floor) {
        this.handler = handler;
        this.floor = floor;
    }

    public void press() {
        FloorRequest request = new FloorRequest(floor);
        handler.accept(request);
    }
}
