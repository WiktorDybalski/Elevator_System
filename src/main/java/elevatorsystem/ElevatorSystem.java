package elevatorsystem;


import elevatorsystem.model.Elevator;
import elevatorsystem.model.ElevatorState;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ElevatorSystem {
    private final List<Elevator> elevators;
    private final Queue<int[]> requests;
    private boolean running;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
        requests = new LinkedList<>();
        running = false;
        for (int i = 0; i < 16; i++) {
            elevators.add(new Elevator(i, 0));
        }
    }

    public void pickup(int pickupFloor, int targetFloor, int direction) {
        requests.add(new int[]{pickupFloor, targetFloor, direction});
    }


    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
        processRequests();
    }

    private boolean isPossibleToPickup(Elevator elevator, int pickupFloor, int direction) {
        return (elevator.getState() == ElevatorState.MOVING_UP && direction == 1 && elevator.getCurrentFloor() < pickupFloor) ||
                (elevator.getState() == ElevatorState.MOVING_DOWN && direction == -1 && elevator.getCurrentFloor() > pickupFloor);
    }

    private Elevator findBestElevator(int pickupFloor, int direction) {
        Optional<Elevator> bestElevator = elevators.stream()
                .filter(elevator -> isPossibleToPickup(elevator, pickupFloor, direction))
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - pickupFloor)));

        return bestElevator.orElseGet(() -> elevators.stream()
                .filter(Elevator::isIdle)
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - pickupFloor)))
                .orElse(null));
    }

    public List<Elevator> status() {
        return new ArrayList<>(elevators);
    }

    private void processRequests() {
        Iterator<int[]> iterator = requests.iterator();
        while (iterator.hasNext()) {
            int[] request = iterator.next();
            int pickupFloor = request[0];
            int targetFloor = request[1];
            int direction = request[2];

            Elevator bestElevator = findBestElevator(pickupFloor, direction);
            if (bestElevator != null) {
                bestElevator.update(pickupFloor, targetFloor);
                iterator.remove();
            }
        }
    }

    public List<Elevator> sendStatusUpdate() {
        List<Elevator> currStatus = status();
        for (Elevator elevator : currStatus) {
            System.out.println("Current status: " + elevator.status());
        }
        return currStatus;
    }

    public void runSimulation() {
        System.out.println("Elevator Systems starting...");
        running = true;
        while (running) {
            step();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stopSimulation() {
        running = false;
    }
}