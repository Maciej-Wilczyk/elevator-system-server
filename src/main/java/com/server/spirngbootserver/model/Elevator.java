package com.server.spirngbootserver.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Elevator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int elevatorId;

    @Column
    private int currentFloor;
    @Column
    private int nearestTargetFloor;
    @Column
    private int direction; // 1 = up, -1 = down, 0 = standing
    private List<Integer> requestedAndSelectedFloorList;

    public Elevator(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.nearestTargetFloor = 0; // no target floor
        this.direction = 0; // elevator is standing
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


