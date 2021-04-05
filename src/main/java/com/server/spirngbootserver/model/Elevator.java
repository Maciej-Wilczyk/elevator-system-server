package com.server.spirngbootserver.model;

import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.enums.NoTargetFloor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Elevator {

    private int elevatorId;
    private int currentFloor;
    private int nearestTargetFloor;
    private Direction direction;
    private List<Integer> requestedAndSelectedFloorList;
    private boolean ifReachedTargetFloor;

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.nearestTargetFloor = NoTargetFloor.NO_TARGET_FLOOR.noTargetFloorAsInt;
        this.direction = Direction.STANDING;
        this.ifReachedTargetFloor = false;
        requestedAndSelectedFloorList = new ArrayList<>();
    }



    public void removeNearest() {
        requestedAndSelectedFloorList.remove(0);
    }

    public int getNearestFromList() {
        if (requestedAndSelectedFloorList.isEmpty())
            return 0;
        else
            return requestedAndSelectedFloorList.get(0);
    }
}


