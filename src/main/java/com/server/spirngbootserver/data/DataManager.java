package com.server.spirngbootserver.data;

import com.server.spirngbootserver.model.Elevator;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Data
public class DataManager {
    private List<Elevator> elevatorList;

    public DataManager() {
        elevatorList = new ArrayList<>();
        for(int i = 0; i < 16; i++){
            elevatorList.add(new Elevator(i));
        }
    }
}
