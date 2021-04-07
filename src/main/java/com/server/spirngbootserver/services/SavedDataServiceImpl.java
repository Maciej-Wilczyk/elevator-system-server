package com.server.spirngbootserver.services;

import com.server.spirngbootserver.Repository.SavedDataRepository;
import com.server.spirngbootserver.dataManager.DataManager;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.model.SavedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class SavedDataServiceImpl implements SavedDataService {

    final DataManager dataManager;

    final SavedDataRepository savedDataRepository;

    final ElevatorSystemService elevatorSystemService;

    public void save(boolean save) {
        if(save){
            savedDataRepository.deleteAllQuery();
            for(Elevator i: dataManager.getElevatorList()){
                SavedData savedData = new SavedData(i.getElevatorId(),i.getCurrentFloor(),i.getNearestTargetFloor(),i.getDirection());
                savedDataRepository.save(savedData);
            }
        }else {
            dataManager.setElevatorList(new ArrayList<>());
            for(SavedData i: savedDataRepository.findAll()){
                dataManager.getElevatorList().add(new Elevator(i.getElevatorId(), i.getCurrentFloor(), i.getNearestTargetFloor(), i.getDirection()));
            }
            elevatorSystemService.updateElevatorList();
        }
    }
}
