package com.server.spirngbootserver.services;

import com.server.spirngbootserver.command_.Command_;
import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.model.Status;

import java.util.List;

public interface ElevatorSystemService {
    List<Status> status();

    void step(List<Command_> commandsList);

    void  step();

    void step(Command_ command_);

    void update(int elevatorId/*, int requestedOrSelectedFloor, int direction*/);


    boolean pickup(int elevatorId, int requestedFloor, Direction direction);

    boolean selected(int elevatorId, int requestedFloor);

    boolean addFloorToList(int elevatorId,int requestedOrSelectedFloor , Direction direction);

    boolean updateAllElevators();
}
