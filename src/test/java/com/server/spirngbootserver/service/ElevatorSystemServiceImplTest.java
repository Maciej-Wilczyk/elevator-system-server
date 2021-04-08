package com.server.spirngbootserver.service;


import com.server.spirngbootserver.Repository.ElevatorSystemConfigRepository;
import com.server.spirngbootserver.Repository.SavedDataRepository;
import com.server.spirngbootserver.dataManager.DataManager;
import com.server.spirngbootserver.dto.StatusDto;
import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.model.ElevatorSystemConfig;
import com.server.spirngbootserver.model.SavedData;
import com.server.spirngbootserver.services.ElevatorSystemService;
import com.server.spirngbootserver.services.ElevatorSystemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ElevatorSystemServiceImplTest {

    @Mock
    private DataManager dataManager;

    @InjectMocks
    private ElevatorSystemServiceImpl elevatorSystemService;


    @Test
    public void updateTest() {
        //given
        Elevator elevator1 = new Elevator(0, 1, 5, Direction.UP);
        elevator1.getRequestedAndSelectedFloorList().add(5);
        Elevator expectedElevator = new Elevator(0, 2, 5, Direction.UP);
        expectedElevator.getRequestedAndSelectedFloorList().add(5);
        elevatorSystemService.getElevatorList().add(elevator1);
        //when
        elevatorSystemService.update(0);
        //then
        assertEquals(elevatorSystemService.getElevatorList().get(0), expectedElevator);
    }

    @Test
    public void addToFloorListTestShouldReturnTrue(){
        //given
        Elevator elevator1 = new Elevator(0, 1, 5, Direction.UP);
        elevator1.getRequestedAndSelectedFloorList().add(5);
        Elevator expectedElevator = new Elevator(0, 1, 5, Direction.UP);
        expectedElevator.getRequestedAndSelectedFloorList().add(5);
        expectedElevator.getRequestedAndSelectedFloorList().add(10);
        elevatorSystemService.getElevatorList().add(elevator1);
        //when
        boolean result = elevatorSystemService.addFloorToList( 0,  10, Direction.UP);
        //then
        assertEquals(elevatorSystemService.getElevatorList().get(0), expectedElevator);
        assertEquals(result,true);
    }

    @Test
    public void addToFloorListTestShouldReturnFalse(){
        //given
        Elevator elevator1 = new Elevator(0, 1, 5, Direction.UP);
        elevator1.getRequestedAndSelectedFloorList().add(5);
        elevatorSystemService.getElevatorList().add(elevator1);
        //when
        boolean result = elevatorSystemService.addFloorToList( 0,  5, Direction.UP);
        //then
        assertEquals(result,false);
    }
}
