package com.server.spirngbootserver.model;

import com.server.spirngbootserver.enums.Direction;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "saved_data")
public class SavedData {

    @Id
    @Column
    private int elevatorId;
    @Column
    private int currentFloor;
    @Column
    private int nearestTargetFloor;
    @Column
    private Direction direction;

    public SavedData() {
    }

    public SavedData(int elevatorId, int currentFloor, int nearestTargetFloor, Direction direction) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.nearestTargetFloor = nearestTargetFloor;
        this.direction = direction;
    }
}
