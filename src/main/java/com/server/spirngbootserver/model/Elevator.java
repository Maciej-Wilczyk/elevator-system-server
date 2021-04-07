package com.server.spirngbootserver.model;

import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.enums.NoTargetFloor;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
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

    public Elevator() {
    }

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.nearestTargetFloor = NoTargetFloor.NO_TARGET_FLOOR.noTargetFloorAsInt;
        this.direction = Direction.STANDING;
        this.ifReachedTargetFloor = false;
        this.requestedAndSelectedFloorList = new ArrayList<>();
    }

    public Elevator(int elevatorId, int currentFloor, int nearestTargetFloor, Direction direction) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.nearestTargetFloor = nearestTargetFloor;
        this.direction = direction;
        this.ifReachedTargetFloor = false;
        this.requestedAndSelectedFloorList = new ArrayList<>();
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

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getNearestTargetFloor() {
        return nearestTargetFloor;
    }

    public void setNearestTargetFloor(int nearestTargetFloor) {
        this.nearestTargetFloor = nearestTargetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Integer> getRequestedAndSelectedFloorList() {
        return requestedAndSelectedFloorList;
    }

    public void setRequestedAndSelectedFloorList(List<Integer> requestedAndSelectedFloorList) {
        this.requestedAndSelectedFloorList = requestedAndSelectedFloorList;
    }

    public boolean isIfReachedTargetFloor() {
        return ifReachedTargetFloor;
    }

    public void setIfReachedTargetFloor(boolean ifReachedTargetFloor) {
        this.ifReachedTargetFloor = ifReachedTargetFloor;
    }
}


