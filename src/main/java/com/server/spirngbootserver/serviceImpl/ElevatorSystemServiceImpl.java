package com.server.spirngbootserver.serviceImpl;

import com.server.spirngbootserver.command_.Command_;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.service.ElevatorSystemService;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemServiceImpl implements ElevatorSystemService {
    private List<Elevator> elevatorList;
    private List<int[]> statusList;

    public ElevatorSystemServiceImpl(List<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
    }

    @Override
    public List<int[]> status() {
        int[] arr;
        statusList = new ArrayList<>();
        for (int i = 0; i < elevatorList.size(); i++) {
            arr = new int[3];
            arr[0] = elevatorList.get(i).getElevatorId();
            arr[1] = elevatorList.get(i).getCurrentFloor();
            arr[2] = elevatorList.get(i).getNearestTargetFloor();
            statusList.add(arr);
        }
        return statusList;
    }

    @Override
    public void step(List<Command_> commandsList) {
        for (Command_ i : commandsList) {
            pickupOrSelect(i.getElevatorId(), i.getRequestedOrSelectedFloor(), i.getDirection());
        }
        updateAll();
    }

    @Override
    public void step() {
        updateAll();
    }

    @Override
    public void step(Command_ command_) {
        pickupOrSelect(command_.getElevatorId(), command_.getRequestedOrSelectedFloor(), command_.getDirection());
        updateAll();
    }

    @Override
    public boolean update(int elevatorId/*, int requestedOrSelectedFloor, int direction*/) {

        boolean flag = false;

        var elevator = elevatorList.get(elevatorId);
        elevator.setCurrentFloor(elevator.getCurrentFloor() + elevator.getDirection()); // changing current floor

        if (elevator.getCurrentFloor() == elevator.getNearestTargetFloor()) {
            elevator.removeNearest(); // remove last target
            flag = true; //open elevator
        }
        elevator.setNearestTargetFloor(elevator.getNearestFromList()); // update the nearest target floor

        if (elevator.getNearestTargetFloor() == 0) // if list is empty
            elevator.setDirection(0); //elevator stands

            // update direction
        else if (elevator.getNearestTargetFloor() - elevator.getCurrentFloor() > 0)
            elevator.setDirection(1);

        else if (elevator.getNearestTargetFloor() - elevator.getCurrentFloor() < 0)
            elevator.setDirection(-1);
        return flag;
    }


    @Override
    public void updateAll() { // update all elevators
        for (int i = 0; i < elevatorList.size(); i++) {
            if(elevatorList.get(i).getDirection() != 0)
                update(i);
        }
    }

    @Override
    public boolean pickupOrSelect(int elevatorId, int requestedOrSelectedFloor, int direction) {
        var list = elevatorList.get(elevatorId).getRequestedAndSelectedFloorList();

        var elevatorDirection = elevatorList.get(elevatorId).getDirection();
        int finalDirection;

        if (requestedOrSelectedFloor == elevatorList.get(elevatorId).getCurrentFloor()) {
            return false; // requested or chosen floor is the current floor
        }

        if (direction == 0) { // floor is choosing in the elevator
            if (requestedOrSelectedFloor - elevatorList.get(elevatorId).getCurrentFloor() > 0)
                finalDirection = 1; // up direction
            else
                finalDirection = -1; // down direction
        } else
            finalDirection = direction; // pickup elevator

        if (list.isEmpty()) {
            list.add(requestedOrSelectedFloor);
            elevatorList.get(elevatorId).setDirection(finalDirection);
            elevatorList.get(elevatorId).setNearestTargetFloor(requestedOrSelectedFloor);
        } else {

            if (elevatorDirection == finalDirection) { // when selected or requested floor is in the same direction

                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i) * elevatorDirection > requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection >= list.get(0) * elevatorDirection) {
                        list.add(i, requestedOrSelectedFloor);
                        break;
                    } else if (list.get(i) * elevatorDirection < requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection < list.get(0) * elevatorDirection) {
                        list.add(i, requestedOrSelectedFloor);
                        break;
                    }
                    if (i == list.size() - 1){
                        list.add(i + 1, requestedOrSelectedFloor);
                        break;
                    }

                }
            } else { // when selected or requested floor is in the opposite direction
                for (int i = list.size() - 1; i >= 0; i--) { // for requestedFloors in the opposite direction, it is faster to check the list from the back
                    if (list.get(i) * elevatorDirection > requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection >= list.get(list.size() - 1) * elevatorDirection) {
                        list.add(i + 1, requestedOrSelectedFloor);
                        break;
                    } else if (list.get(i) * elevatorDirection < requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection < list.get(list.size() - 1) * elevatorDirection) {
                        list.add(i, requestedOrSelectedFloor);
                        break;
                    }
                    if (i == list.size() - 1) {
                        list.add(requestedOrSelectedFloor);
                        break;
                    }
                }
            }
        }
        return true;
    }
}
