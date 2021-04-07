package com.server.spirngbootserver.dataManager;

import com.server.spirngbootserver.Repository.ElevatorSystemConfigRepository;
import com.server.spirngbootserver.Repository.SavedDataRepository;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.model.ElevatorSystemConfig;
import com.server.spirngbootserver.model.SavedData;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DataManager {

    private List<Elevator> elevatorList;

    final SavedDataRepository savedDataRepository;

    final ElevatorSystemConfigRepository elevatorSystemConfigRepository;



    public DataManager(SavedDataRepository savedDataRepository, ElevatorSystemConfigRepository elevatorSystemConfigRepository) {

        this.savedDataRepository = savedDataRepository;
        this.elevatorSystemConfigRepository = elevatorSystemConfigRepository;
//        if(savedDataRepository.count() == 0){
//            elevatorList = new ArrayList<>();
//            for (int i = 0; i < elevatorSystemConfigRepository.findById(0).orElseThrow().getNumberOfElevators(); i++) {
//                elevatorList.add(new Elevator(i));
//            }
//        }
        if(elevatorSystemConfigRepository.count() == 0){
            ElevatorSystemConfig elevatorSystemConfig = new ElevatorSystemConfig();
            elevatorSystemConfig.setId(0);
            elevatorSystemConfig.setNumberOfElevators(-1);
            elevatorSystemConfig.setNumberOfFloors(-1);
            elevatorSystemConfigRepository.save(elevatorSystemConfig);
        }
        else {
            elevatorList = new ArrayList<>();
            for(SavedData i: savedDataRepository.findAll()){
                elevatorList.add(new Elevator(i.getElevatorId(), i.getCurrentFloor(), i.getNearestTargetFloor(), i.getDirection()));
            }
        }


    }
    public void setElevatorSystemConfig(){
        elevatorList = new ArrayList<>();
        for(int i = 0; i < elevatorSystemConfigRepository.findById(0).orElseThrow().getNumberOfElevators(); i++){
            elevatorList.add(new Elevator(i));
        }
    }

}
