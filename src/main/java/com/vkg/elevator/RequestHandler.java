package com.vkg.elevator;


public interface RequestHandler<T extends ElevatorRequest> {
    void accept(T request);
}
