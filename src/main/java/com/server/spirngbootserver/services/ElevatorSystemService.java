package com.server.spirngbootserver.services;

import com.server.spirngbootserver.dto.DataForPickupDto;
import com.server.spirngbootserver.dto.DataForSelectDto;
import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.dto.StatusDto;

import java.util.List;

public interface ElevatorSystemService {
    List<StatusDto> status();

    void  step();

    void update(int elevatorId);

    boolean pickup(List<DataForPickupDto> list);

    boolean select(List<DataForSelectDto> list);

    boolean addFloorToList(int elevatorId,int requestedOrSelectedFloor , Direction direction);

    boolean updateAllElevators();

    void updateElevatorList();

}
