package elevatorsystem.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Elevator {
    int id;
    int currentFloor;
    int previousFloor;
    PriorityQueue<Integer> upRequests;
    PriorityQueue<Integer> downRequests;

    ElevatorState state;
    ElevatorState previousState;

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getPreviousFloor() {
        return previousFloor;
    }

    public PriorityQueue<Integer> getUpRequests() {
        return upRequests;
    }

    public PriorityQueue<Integer> getDownRequests() {
        return downRequests;
    }

    public ElevatorState getState() {
        return state;
    }

    public ElevatorState getPreviousState() {
        return previousState;
    }

    public Elevator(int id, int currentFloor) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.previousFloor = currentFloor;
        this.state = ElevatorState.IDLE;
        this.previousState = this.state;
        upRequests = new PriorityQueue<>();
        downRequests = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void update(int floorRequest, int targetFloorRequest) {
        if (floorRequest == currentFloor) {
            if (targetFloorRequest > currentFloor) {
                upRequests.add(targetFloorRequest);
                state = ElevatorState.MOVING_UP;
            } else if (targetFloorRequest < currentFloor) {
                downRequests.add(targetFloorRequest);
                state = ElevatorState.MOVING_DOWN;
            }
        } else {
            if (floorRequest > currentFloor) {
                upRequests.add(floorRequest);
                if (targetFloorRequest > floorRequest) {
                    upRequests.add(targetFloorRequest);
                } else {
                    downRequests.add(targetFloorRequest);
                }
                if (state == ElevatorState.IDLE) {
                    state = ElevatorState.MOVING_UP;
                }
            } else {
                downRequests.add(floorRequest);
                if (targetFloorRequest < floorRequest) {
                    downRequests.add(targetFloorRequest);
                } else {
                    upRequests.add(targetFloorRequest);
                }
                if (state == ElevatorState.IDLE) {
                    state = ElevatorState.MOVING_DOWN;
                }
            }
        }
    }

    public void printStatusIfChanged() {
        if (state != previousState || currentFloor != previousFloor) {
            System.out.println(status());
            previousFloor = currentFloor;
            if (state != ElevatorState.STOPPED) {
                previousState = state;
            }
        }
    }

    private void checkAndResume() {
        if (!upRequests.isEmpty()) {
            state = ElevatorState.MOVING_UP;
        } else if (!downRequests.isEmpty()) {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    public void step() {
        if (state == ElevatorState.STOPPED) {
            state = previousState;
        }
        if (state == ElevatorState.MOVING_UP) {
            moveUp();
        } else if (state == ElevatorState.MOVING_DOWN) {
            moveDown();
        } else if (state == ElevatorState.IDLE) {
            checkAndResume();
        }
        printStatusIfChanged();
    }


    private void moveUp() {
        if (!upRequests.isEmpty() && currentFloor == upRequests.peek()) {
            upRequests.poll();
            state = upRequests.isEmpty() ? ElevatorState.IDLE : ElevatorState.STOPPED;
        } else {
            currentFloor++;
        }
    }

    private void moveDown() {
        if (!downRequests.isEmpty() && currentFloor == downRequests.peek()) {
            downRequests.poll();
            state = downRequests.isEmpty() ? ElevatorState.IDLE : ElevatorState.STOPPED;
        } else {
            currentFloor--;
        }
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE;
    }

    public String status() {
        return "(ElevatorID: " + id + ", Current Floor: " + currentFloor + ", UpBreaks: " + upRequests + ", DownBreaks: " + downRequests + ", State: " + state + ")";
    }
}
