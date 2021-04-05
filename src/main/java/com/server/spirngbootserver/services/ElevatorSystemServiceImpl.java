package com.server.spirngbootserver.services;

import com.server.spirngbootserver.data.DataManager;
import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.enums.NoTargetFloor;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.dto.StatusDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElevatorSystemServiceImpl implements ElevatorSystemService {

    private DataManager dataManager;
    private List<Elevator> elevatorList;
    private List<StatusDto> statusDtoList;

    public ElevatorSystemServiceImpl() {
        dataManager = new DataManager();
        elevatorList = dataManager.getElevatorList();
    }

    @Override
    public List<StatusDto> status() {
        statusDtoList = new ArrayList<>();
        for (Elevator i : elevatorList) {
            statusDtoList.add(new StatusDto(i.getElevatorId(), i.getCurrentFloor(), i.getNearestTargetFloor(), i.getDirection()));
        }
        return statusDtoList;
    }

    @Override
    public void step() {
        updateAllElevators();
    }

    @Override
    public void update(int elevatorId) {

        //boolean flag = false;
        var elevator = elevatorList.get(elevatorId);

        System.out.println("cur " + elevator.getCurrentFloor() + "target " + elevator.getNearestTargetFloor()
        + "direction  " + elevator.getDirection() + "list " + elevator.getRequestedAndSelectedFloorList());
        elevator.setCurrentFloor(elevator.getCurrentFloor() + elevator.getDirection().getDirectionAsInt());
        System.out.println("po");
        System.out.println("cur " + elevator.getCurrentFloor() + "target " + elevator.getNearestTargetFloor()
                + "direction  " + elevator.getDirection() + "list " + elevator.getRequestedAndSelectedFloorList());

        elevator.setNearestTargetFloor(elevator.getNearestFromList());

        if (elevator.getCurrentFloor() == elevator.getNearestTargetFloor()) {
            elevator.removeNearest();
            //  flag = true; //open elevator
            elevator.setIfReachedTargetFloor(true);
        } else {
            elevator.setIfReachedTargetFloor(false);
        }

        if (ifElevatorHasNotTargetFloor(elevator.getNearestTargetFloor(), elevator.getNearestFromList())) {
            elevator.setDirection(Direction.STANDING);
        } else if (elevator.getNearestFromList() - elevator.getCurrentFloor() > 0) {
            elevator.setDirection(Direction.UP);
        } else if (elevator.getNearestFromList() - elevator.getCurrentFloor() < 0) {
            elevator.setDirection(Direction.DOWN);
        }

        System.out.println("dir po po " + elevator.getDirection());


        //return flag;
    }



    @Override
    public boolean updateAllElevators() {
        boolean ifAnyElevatorMoving = false;
        for (int i = 0; i < elevatorList.size(); i++) {
            if (elevatorList.get(i).getDirection() != Direction.STANDING) {
                update(i);
                ifAnyElevatorMoving = true;
            }
        }
        return ifAnyElevatorMoving;
    }

    @Override
    public boolean select(int elevatorId, int selectedFloor) {
        Direction direction;
        if (selectedFloor - elevatorList.get(elevatorId).getCurrentFloor() > 0) {
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }
        return addFloorToList(elevatorId, selectedFloor, direction);
    }

    @Override
    public boolean pickup(int elevatorId, int requestedFloor, Direction direction) {
        return addFloorToList(elevatorId, requestedFloor, direction);
    }

    @Override
    public boolean addFloorToList(int elevatorId, int requestedOrSelectedFloor, Direction direction) {
        var list = elevatorList.get(elevatorId).getRequestedAndSelectedFloorList();
        int elevatorDirectionAsInt = elevatorList.get(elevatorId).getDirection().getDirectionAsInt();

//        if (requestedOrSelectedFloor == elevatorList.get(elevatorId).getCurrentFloor()) {
//            return false; // requested or chosen floor is the current floor
//        }

        //System.out.println(requestedOrSelectedFloor);
        //System.out.println(list);
        //System.out.println(list(requestedOrSelectedFloor));
        if(isFloorAlreadyRequestedOrSelected(requestedOrSelectedFloor, list) || isFloorEqualsCurrentFloor(elevatorId, requestedOrSelectedFloor))
            return false;

        if (list.isEmpty()) {
            list.add(requestedOrSelectedFloor);
            elevatorList.get(elevatorId).setDirection(direction);
            elevatorList.get(elevatorId).setNearestTargetFloor(requestedOrSelectedFloor);
        } else {
            if (elevatorDirectionAsInt == direction.getDirectionAsInt()) { // when selected or requested floor is in the same direction

                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i) * elevatorDirectionAsInt > requestedOrSelectedFloor * elevatorDirectionAsInt && list.get(i) * elevatorDirectionAsInt >= list.get(0) * elevatorDirectionAsInt) {
                        list.add(i, requestedOrSelectedFloor);
                        break;
                    } else if (list.get(i) * elevatorDirectionAsInt < requestedOrSelectedFloor * elevatorDirectionAsInt && list.get(i) * elevatorDirectionAsInt < list.get(0) * elevatorDirectionAsInt) {
                        list.add(i, requestedOrSelectedFloor);
                        break;
                    }
                    if (i == list.size() - 1) {
                        list.add(i + 1, requestedOrSelectedFloor);
                        break;
                    }
                }
            } else { // when selected or requested floor is in the opposite direction
                for (int i = list.size() - 1; i >= 0; i--) { // for requestedFloors in the opposite direction, it is faster to check the list from the back
                    if (list.get(i) * elevatorDirectionAsInt > requestedOrSelectedFloor * elevatorDirectionAsInt && list.get(i) * elevatorDirectionAsInt >= list.get(list.size() - 1) * elevatorDirectionAsInt) {
                        list.add(i + 1, requestedOrSelectedFloor);
                        break;
                    } else if (list.get(i) * elevatorDirectionAsInt < requestedOrSelectedFloor * elevatorDirectionAsInt && list.get(i) * elevatorDirectionAsInt < list.get(list.size() - 1) * elevatorDirectionAsInt) {
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

    private boolean isFloorEqualsCurrentFloor(int elevatorId, int requestedOrSelectedFloor) {
        return requestedOrSelectedFloor == elevatorList.get(elevatorId).getCurrentFloor();
    }

    private boolean isFloorAlreadyRequestedOrSelected(int requestedOrSelectedFloor, List<Integer> list) {
        return list.contains(requestedOrSelectedFloor);
    }

    private boolean ifElevatorHasNotTargetFloor(int nearestTargetFloor, int getNearestFromList) {
        if(nearestTargetFloor == NoTargetFloor.NO_TARGET_FLOOR.noTargetFloorAsInt || getNearestFromList == 0){
            return true;}
        else{
            return false;
        }
    }
}
