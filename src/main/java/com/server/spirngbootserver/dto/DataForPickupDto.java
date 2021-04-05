package com.server.spirngbootserver.dto;

import com.server.spirngbootserver.enums.Direction;
import lombok.Data;

@Data
public class DataForPickupDto {
    private int elevatorId;
    private int requestedFloor;
    private Direction direction;

}
