package com.server.spirngbootserver.dto;

import com.server.spirngbootserver.enums.Direction;
import lombok.Data;

@Data
public class StatusDto {

    private int elevatorId;
    private int currentFloor;
    private int nearestTargetFloor;
    private Direction direction;
    private boolean ifReachedTargetFloor;

    public StatusDto(int elevatorId, int currentFloor, int nearestTargetFloor, Direction direction, boolean ifReachedTargetFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.nearestTargetFloor = nearestTargetFloor;
        this.direction = direction;
        this.ifReachedTargetFloor = ifReachedTargetFloor;
    }
}
