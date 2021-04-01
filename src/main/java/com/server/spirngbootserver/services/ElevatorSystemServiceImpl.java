package com.server.spirngbootserver.services;

import com.server.spirngbootserver.data.DataManager;
import com.server.spirngbootserver.enums.Direction;
import com.server.spirngbootserver.model.Elevator;
import com.server.spirngbootserver.model.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElevatorSystemServiceImpl implements ElevatorSystemService {

    private DataManager dataManager;
    private List<Elevator> elevatorList;
    private List<Status> statusList;

    public ElevatorSystemServiceImpl() {
        dataManager = new DataManager();
        elevatorList = dataManager.getElevatorList();
    }

    @Override
    public List<Status> status() {
        statusList = new ArrayList<>();
        for (int i = 0; i < elevatorList.size(); i++) {
            statusList.add(new Status(elevatorList.get(i).getElevatorId(), elevatorList.get(i).getCurrentFloor(), elevatorList.get(i).getNearestTargetFloor()));
        }
        return statusList;
    }


    @Override
    public void step() {
        updateAllElevators();
    }


    @Override
    public void update(int elevatorId) {

        //boolean flag = false;
        var elevator = elevatorList.get(elevatorId);
        elevator.setCurrentFloor(elevator.getCurrentFloor() + elevator.getDirection().getDirectionAsInt());

        if (elevator.getCurrentFloor() == elevator.getNearestTargetFloor()) {
            elevator.removeNearest();
          //  flag = true; //open elevator
            elevator.setNearestTargetFloor(elevator.getNearestFromList());
            elevator.setIfReachedTargetFloor(true);
        } else{
            elevator.setIfReachedTargetFloor(false);
        }

        if (ifElevatorHasNotTargetFloor(elevator.getNearestTargetFloor())) {
            elevator.setDirection(Direction.STANDING);
        } else if (elevator.getNearestTargetFloor() - elevator.getCurrentFloor() > 0)
            elevator.setDirection(Direction.UP);

        else if (elevator.getNearestTargetFloor() - elevator.getCurrentFloor() < 0)
            elevator.setDirection(Direction.DOWN);
        //return flag;
    }

    private boolean ifElevatorHasNotTargetFloor(int nearestTargetFloor) {
        return nearestTargetFloor == 0;
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

//    @Override
//    public boolean pickupOrSelect(int elevatorId, int requestedOrSelectedFloor, Direction direction) {
//        var list = elevatorList.get(elevatorId).getRequestedAndSelectedFloorList();
//
//        var elevatorDirection = elevatorList.get(elevatorId).getDirection();
//        int finalDirection;
//
//        if (requestedOrSelectedFloor == elevatorList.get(elevatorId).getCurrentFloor()) {
//            return false; // requested or chosen floor is the current floor
//        }
//
//        finalDirection = getFinalDirection(elevatorId, requestedOrSelectedFloor, direction).directionAsInt;
//
//        if (list.isEmpty()) {
//            list.add(requestedOrSelectedFloor);
//            elevatorList.get(elevatorId).setDirection(finalDirection);
//            elevatorList.get(elevatorId).setNearestTargetFloor(requestedOrSelectedFloor);
//        } else {
//
//            if (elevatorDirection == finalDirection) { // when selected or requested floor is in the same direction
//
//                for (int i = 0; i < list.size(); i++) {
//
//                    if (list.get(i) * elevatorDirection > requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection >= list.get(0) * elevatorDirection) {
//                        list.add(i, requestedOrSelectedFloor);
//                        break;
//                    } else if (list.get(i) * elevatorDirection < requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection < list.get(0) * elevatorDirection) {
//                        list.add(i, requestedOrSelectedFloor);
//                        break;
//                    }
//                    if (i == list.size() - 1) {
//                        list.add(i + 1, requestedOrSelectedFloor);
//                        break;
//                    }
//                }
//            } else { // when selected or requested floor is in the opposite direction
//                for (int i = list.size() - 1; i >= 0; i--) { // for requestedFloors in the opposite direction, it is faster to check the list from the back
//                    if (list.get(i) * elevatorDirection > requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection >= list.get(list.size() - 1) * elevatorDirection) {
//                        list.add(i + 1, requestedOrSelectedFloor);
//                        break;
//                    } else if (list.get(i) * elevatorDirection < requestedOrSelectedFloor * elevatorDirection && list.get(i) * elevatorDirection < list.get(list.size() - 1) * elevatorDirection) {
//                        list.add(i, requestedOrSelectedFloor);
//                        break;
//                    }
//                    if (i == list.size() - 1) {
//                        list.add(requestedOrSelectedFloor);
//                        break;
//                    }
//                }
//            }
//        }
//        return true;
//    }

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

        if (requestedOrSelectedFloor == elevatorList.get(elevatorId).getCurrentFloor()) {
            return false; // requested or chosen floor is the current floor
        }

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

//    private Direction getFinalDirection(int elevatorId, int requestedOrSelectedFloor, Direction direction) {
//        Direction finalDirection;
//        if (elevatorWasCalledFromInside(direction.getDirectionAsInt())) {
//            if (requestedOrSelectedFloor - elevatorList.get(elevatorId).getCurrentFloor() > 0) {
//                finalDirection = Direction.UP; // up direction
//            } else {
//                finalDirection = Direction.DOWN; // down direction
//            }
//        } else {
//            finalDirection = direction; // pickup elevator
//        }
//        return finalDirection;
//    }

//    private boolean elevatorWasCalledFromInside(int direction) {
//        return direction == 0;
//    }

//    enum Direction {
//        UP(1),
//        DOWN(-1),
//        BUTTON_FROM_INSIDE(0);
//        int directionAsInt;
//
//        Direction(int directionAsInt) {
//            this.directionAsInt = directionAsInt;
//        }
//    }
}
