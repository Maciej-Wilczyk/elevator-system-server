package com.server.spirngbootserver.services;

import com.server.spirngbootserver.Repository.ElevatorSystemConfigRepository;
import com.server.spirngbootserver.dto.ElevatorSystemConfigDto;
import com.server.spirngbootserver.model.ElevatorSystemConfig;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ElevatorSystemConfigService {

    final ElevatorSystemConfigRepository elevatorSystemConfigRepository;

    public void setNumbers(ElevatorSystemConfigDto elevatorSystemConfigDto){
        elevatorSystemConfigRepository.deleteAllQuery();
        ElevatorSystemConfig numberOfElevators = new ElevatorSystemConfig();
        System.out.println(elevatorSystemConfigDto.getNumberOfElevators());
        numberOfElevators.setNumberOfElevators(elevatorSystemConfigDto.getNumberOfElevators());
        numberOfElevators.setNumberOfFloors(elevatorSystemConfigDto.getNumberOfFloors());
        elevatorSystemConfigRepository.save(numberOfElevators);
    }

    public ElevatorSystemConfig getNumbers(){
        return elevatorSystemConfigRepository.findById(0).orElseThrow();
    }
}
