package com.server.spirngbootserver.service;

import com.server.spirngbootserver.command_.Command_;

import java.util.List;

public interface ElevatorSystemService {
    List<int[]> status();

    void step(List<Command_> commandsList);

    void  step();

    void step(Command_ command_);

    boolean update(int elevatorId/*, int requestedOrSelectedFloor, int direction*/);

    boolean pickupOrSelect(int elevatorId, int requestedOrSelectedFloor, int direction);

    void updateAll();
}
