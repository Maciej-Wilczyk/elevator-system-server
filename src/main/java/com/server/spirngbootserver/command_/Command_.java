package com.server.spirngbootserver.command_;

import lombok.Data;

@Data
public class Command_ {
    private int elevatorId;
    private int requestedOrSelectedFloor;
    private int direction;

    public Command_(int elevatorId, int requestedOrSelectedFloor, int direction) {
        this.elevatorId = elevatorId;
        this.requestedOrSelectedFloor = requestedOrSelectedFloor;
        this.direction = direction;
    }


}
