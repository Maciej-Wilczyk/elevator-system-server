package com.server.spirngbootserver.model;

import lombok.Data;

@Data
public class Status {

    private int elevatorId;
    private int currentFloor;
    private int nearestTargetFloor;

    public Status(int elevatorId, int currentFloor, int nearestTargetFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.nearestTargetFloor = nearestTargetFloor;
    }
}
