package com.server.spirngbootserver.services;

import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.model.Status;

import java.util.List;

public interface ElevatorSystemService {
    List<Status> status();

    void  step();

    void update(int elevatorId);

    boolean pickup(int elevatorId, int requestedFloor, Direction direction);

    boolean select(int elevatorId, int requestedFloor);

    boolean addFloorToList(int elevatorId,int requestedOrSelectedFloor , Direction direction);

    boolean updateAllElevators();
}
