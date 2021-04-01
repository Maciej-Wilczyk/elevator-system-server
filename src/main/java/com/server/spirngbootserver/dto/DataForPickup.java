package com.server.spirngbootserver.dto;

import com.server.spirngbootserver.enums.Direction;
import lombok.Data;

@Data
public class DataForPickup {
    private int elevatorId;
    private int requestedFloor;
    private Direction direction;

}
